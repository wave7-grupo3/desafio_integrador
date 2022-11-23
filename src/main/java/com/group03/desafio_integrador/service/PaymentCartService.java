package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.advisor.exceptions.PaymentInvalidException;
import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.dto.PaymentPixDTO;
import com.group03.desafio_integrador.entities.PaymentCart;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentTypeEnum;
import com.group03.desafio_integrador.entities.mapper.DozerMapper;
import com.group03.desafio_integrador.repository.PaymentCartRepository;
import com.group03.desafio_integrador.service.interfaces.IPaymentCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PaymentCartService implements IPaymentCartService {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PaymentCartRepository paymentCartRepository;

    /**
     * Método responsável por realizar pagamento através do cartão de crédito
     * @param paymentCreditCardDTO
     * @param idCart
     * @return PaymentCart
     */
    @Override
    public PaymentCart insertPaymentCreditCard(PaymentCreditCardDTO paymentCreditCardDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        verifyPaymentExists(shoppingCart);

        PaymentCart paymentCreditCard = DozerMapper.parseObject(paymentCreditCardDTO, PaymentCart.class);

        verifyPaymentValue(shoppingCart, paymentCreditCard);

        maskCardNumber(paymentCreditCard);
        maskCpf(paymentCreditCard);

        setDataPayment(paymentCreditCard, PaymentTypeEnum.CREDIT_CARD, PaymentStatusEnum.PAID);

        setShoppingCart(shoppingCart, OrderStatusEnum.FINALIZADO, paymentCreditCard);

        shoppingCartService.save(shoppingCart);

        sendEmail(shoppingCart);

        return paymentCreditCard;
    }

    /**
     * Método responsável por realizar pagamento através de boleto bancário
     * @param paymentBankSlipDTO
     * @param idCart
     * @return PaymentCart
     */
    @Override
    public PaymentCart insertPaymentBankSlip(PaymentBankSlipDTO paymentBankSlipDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        verifyPaymentExists(shoppingCart);

        PaymentCart paymentCart = DozerMapper.parseObject(paymentBankSlipDTO, PaymentCart.class);

        verifyPaymentWithDiscount(shoppingCart, paymentCart);

        setDataPayment(paymentCart, PaymentTypeEnum.BANK_SLIP, PaymentStatusEnum.OPEN);

        maskCpf(paymentCart);

        setShoppingCart(shoppingCart, OrderStatusEnum.ABERTO, paymentCart);

        calculateDiscount(shoppingCart);

        shoppingCartService.save(shoppingCart);

//        sendEmail(shoppingCart);

        return paymentCart;
    }

    /**
     * Método responsável por verificar o pagamento aplicando o desconto
     * @param shoppingCart
     * @param paymentCart
     */
    private static void verifyPaymentWithDiscount(ShoppingCart shoppingCart, PaymentCart paymentCart) {
        Double paymentValue = paymentCart.getPaymentValue().doubleValue();

        if (!paymentValue.equals(shoppingCart.getTotalCartPrice() * 0.95)) {
            throw new NotFoundException("Value incorrect");
        }
    }

    /**
     * Método responsável por realizar pagamento via Pix QR Code
     * @param paymentPixDTO
     * @param idCart
     * @return PaymentCart
     */
    @Override
    public PaymentCart insertPaymentPix(PaymentPixDTO paymentPixDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        verifyPaymentExists(shoppingCart);

        PaymentCart paymentCart = DozerMapper.parseObject(paymentPixDTO, PaymentCart.class);

        verifyPaymentWithDiscount(shoppingCart, paymentCart);

        maskCpf(paymentCart);
        setDataPayment(paymentCart, PaymentTypeEnum.PIX, PaymentStatusEnum.PAID);

        setShoppingCart(shoppingCart, OrderStatusEnum.FINALIZADO, paymentCart);

        calculateDiscount(shoppingCart);

        shoppingCartService.save(shoppingCart);

        sendEmail(shoppingCart);

        return paymentCart;
    }

    /**
     * Método responsável por enviar email de notificação
     * @param shoppingCart
     */
    public void sendEmail(ShoppingCart shoppingCart) {
        JavaMailService.sendMail(shoppingCart.getBuyer().getBuyerName(), shoppingCart.getBuyer().getEmail());
    }

    /**
     * Método responável por setar informações do carrinho
     * @param shoppingCart
     * @param finalizado
     * @param paymentCreditCard
     */
    private static void setShoppingCart(ShoppingCart shoppingCart, OrderStatusEnum finalizado, PaymentCart paymentCreditCard) {
        shoppingCart.setOrderStatus(finalizado);
        shoppingCart.setPaymentCart(paymentCreditCard);
    }

    /**
     * Método responsável por setar informações do pagamento
     * @param paymentCart
     * @param paymentTypeEnum
     * @param statusEnum
     */
    private static void setDataPayment(PaymentCart paymentCart, PaymentTypeEnum paymentTypeEnum, PaymentStatusEnum statusEnum) {
        paymentCart.setPaymentType(paymentTypeEnum);
        paymentCart.setPaymentStatus(statusEnum);
        paymentCart.setTimestamp(LocalDateTime.now());
    }

    /**
     * Método responsável por verificar valor correspondente do pagamento
     * @param shoppingCart
     * @param paymentCreditCard
     */
    public void verifyPaymentValue(ShoppingCart shoppingCart, PaymentCart paymentCreditCard) {
        Double paymentValue = paymentCreditCard.getPaymentValue().doubleValue();

        if (!paymentValue.equals(shoppingCart.getTotalCartPrice())) {
            throw new NotFoundException("Value incorrect");
        }
    }

    /**
     * Método responsável por calcular valor do desconto
     * @param shoppingCart
     */
    public void calculateDiscount(ShoppingCart shoppingCart) {
        Double priceDiscount = shoppingCart.getTotalCartPrice() * 0.05;
        Double finalPrice = shoppingCart.getTotalCartPrice() * 0.95;

        shoppingCart.setTotalCartPrice(finalPrice);
        shoppingCart.setDiscountPayment(priceDiscount);
    }

    /**
     * Método responsável por verificar existência do pagamento no carrinho de compras
     * @param shoppingCart
     */
    public void verifyPaymentExists(ShoppingCart shoppingCart) {
        if (shoppingCart.getPaymentCart() != null) {
            throw new PaymentInvalidException("This cart is already paid");
        }
    }

    /**
     * Método responsável por mascarar CPF do cliente no banco de dados
     * @param paymentCreditCard
     */
    public void maskCpf(PaymentCart paymentCreditCard) {
        String cpf = paymentCreditCard.getCpf();
        cpf = cpf.substring(0, 3) + "*".repeat(8);
        paymentCreditCard.setCpf(cpf);
    }

    /**
     * Método responsável por mascarar número do cartão de crédito do cliente
     * @param paymentCreditCard
     */
    public void maskCardNumber(PaymentCart paymentCreditCard) {
        String creditNumber = paymentCreditCard.getNumberCard();
        creditNumber = creditNumber.substring(0, 4) + "*".repeat(8) + creditNumber.substring(12, 16);
        paymentCreditCard.setNumberCard(creditNumber);
    }

    /**
     * Método responsável por recuperar um pagamento de cartão de crédito
     * @param id
     * @return PaymentCreditCardDTO
     */
    @Override
    public PaymentCreditCardDTO getPaymentCreditCard(Long id) {
        PaymentCart paymentCart = paymentCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));

        if (!paymentCart.getPaymentType().equals(PaymentTypeEnum.CREDIT_CARD)) {
            throw new NotFoundException("Payment isn't of the type Credit card");
        }

        return DozerMapper.parseObject(paymentCart, PaymentCreditCardDTO.class);
    }

    /**
     * Método responsável por recuperar um pagamento feito por Pix QR Code
     * @param id
     * @return PaymentPixDTO
     */
    @Override
    public PaymentPixDTO getPaymentPix(Long id) {
        PaymentCart paymentCart = paymentCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));

        if (!paymentCart.getPaymentType().equals(PaymentTypeEnum.PIX)) {
            throw new NotFoundException("Payment isn't of the type Pix");
        }

        return DozerMapper.parseObject(paymentCart, PaymentPixDTO.class);
    }

    /**
     * Método responsável por recuperar um pagamento feito por boleto bancário
     * @param id
     * @return PaymentBankSlipDTO
     */
    @Override
    public PaymentBankSlipDTO getPaymentBankSlip(Long id) {
        PaymentCart paymentCart = paymentCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));

        if (!paymentCart.getPaymentType().equals(PaymentTypeEnum.BANK_SLIP)) {
            throw new NotFoundException("Payment isn't of the type Bank Slip");
        }

        return DozerMapper.parseObject(paymentCart, PaymentBankSlipDTO.class);
    }


}
