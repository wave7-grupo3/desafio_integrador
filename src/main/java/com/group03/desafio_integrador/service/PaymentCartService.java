package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
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

    @Override
    public PaymentCart insertPaymentCreditCard(PaymentCreditCardDTO paymentCreditCardDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        verifyPaymentExists(shoppingCart);

        PaymentCart paymentCreditCard = DozerMapper.parseObject(paymentCreditCardDTO, PaymentCart.class);

        verifyPaymentValue(shoppingCart, paymentCreditCard);

        maskCardNumber(paymentCreditCard);
        maskCpf(paymentCreditCard);

        setData(paymentCreditCard, PaymentTypeEnum.CREDIT_CARD, PaymentStatusEnum.PAID);

        setShoppingCart(shoppingCart, OrderStatusEnum.FINALIZADO, paymentCreditCard);

        shoppingCartService.save(shoppingCart);

        sendEmail(shoppingCart);

        return paymentCreditCard;
    }

    @Override
    public PaymentCart insertPaymentBankSlip(PaymentBankSlipDTO paymentBankSlipDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        verifyPaymentExists(shoppingCart);

        PaymentCart paymentCart = DozerMapper.parseObject(paymentBankSlipDTO, PaymentCart.class);

        verifyPaymentWithDiscount(shoppingCart, paymentCart);

        setData(paymentCart, PaymentTypeEnum.BANK_SLIP, PaymentStatusEnum.OPEN);

        maskCpf(paymentCart);

        setShoppingCart(shoppingCart, OrderStatusEnum.ABERTO, paymentCart);

        shoppingCartService.save(shoppingCart);

        sendEmail(shoppingCart);

        return paymentCart;
    }

    private static void verifyPaymentWithDiscount(ShoppingCart shoppingCart, PaymentCart paymentCart) {
        Double paymentValue = paymentCart.getPaymentValue().doubleValue();

        if (!paymentValue.equals(shoppingCart.getTotalCartPrice() * 0.95)) {
            throw new NotFoundException("Value incorrect");
        }
    }

    @Override
    public PaymentCart insertPaymentPix(PaymentPixDTO paymentPixDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        verifyPaymentExists(shoppingCart);

        PaymentCart paymentCart = DozerMapper.parseObject(paymentPixDTO, PaymentCart.class);

        verifyPaymentWithDiscount(shoppingCart, paymentCart);

        maskCpf(paymentCart);
        setData(paymentCart, PaymentTypeEnum.PIX, PaymentStatusEnum.PAID);

        setShoppingCart(shoppingCart, OrderStatusEnum.FINALIZADO, paymentCart);

        calculateDiscount(shoppingCart);

        shoppingCartService.save(shoppingCart);

        sendEmail(shoppingCart);

        return paymentCart;
    }

    public void sendEmail(ShoppingCart shoppingCart) {
        JavaMailService.sendMail(shoppingCart.getBuyer().getBuyerName(), shoppingCart.getBuyer().getEmail());
    }

    private static void setShoppingCart(ShoppingCart shoppingCart, OrderStatusEnum finalizado, PaymentCart paymentCreditCard) {
        shoppingCart.setOrderStatus(finalizado);
        shoppingCart.setPaymentCart(paymentCreditCard);
    }

    private static void setData(PaymentCart paymentCart, PaymentTypeEnum paymentTypeEnum, PaymentStatusEnum statusEnum) {
        paymentCart.setPaymentType(paymentTypeEnum);
        paymentCart.setPaymentStatus(statusEnum);
        paymentCart.setTimestamp(LocalDateTime.now());
    }

    public void verifyPaymentValue(ShoppingCart shoppingCart, PaymentCart paymentCreditCard) {
        Double paymentValue = paymentCreditCard.getPaymentValue().doubleValue();

        if (!paymentValue.equals(shoppingCart.getTotalCartPrice())) {
            throw new NotFoundException("Value incorrect");
        }
    }

    public void calculateDiscount(ShoppingCart shoppingCart) {
        Double priceDiscount = shoppingCart.getTotalCartPrice() * 0.05;
        Double finalPrice = shoppingCart.getTotalCartPrice() * 0.95;

        shoppingCart.setTotalCartPrice(finalPrice);
        shoppingCart.setDiscountPayment(priceDiscount);
    }

    public void verifyPaymentExists(ShoppingCart shoppingCart) {
        if (shoppingCart.getPaymentCart() != null) {
            throw new NotFoundException("This cart is already paid");
        }
    }

    public void maskCpf(PaymentCart paymentCreditCard) {
        String cpf = paymentCreditCard.getCpf();
        cpf = cpf.substring(0, 3) + "*".repeat(8);
        paymentCreditCard.setCpf(cpf);
    }

    public void maskCardNumber(PaymentCart paymentCreditCard) {
        String creditNumber = paymentCreditCard.getNumberCard();
        creditNumber = creditNumber.substring(0, 4) + "*".repeat(8) + creditNumber.substring(12, 16);
        paymentCreditCard.setNumberCard(creditNumber);
    }

    @Override
    public PaymentCreditCardDTO getPaymentCreditCard(Long id) {
        PaymentCart paymentCart = paymentCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));

        if (!paymentCart.getPaymentType().equals(PaymentTypeEnum.CREDIT_CARD)) {
            throw new NotFoundException("Payment isn't of the type Credit card");
        }

        return DozerMapper.parseObject(paymentCart, PaymentCreditCardDTO.class);
    }

    @Override
    public PaymentPixDTO getPaymentPix(Long id) {
        PaymentCart paymentCart = paymentCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));

        if (!paymentCart.getPaymentType().equals(PaymentTypeEnum.PIX)) {
            throw new NotFoundException("Payment isn't of the type Pix");
        }

        return DozerMapper.parseObject(paymentCart, PaymentPixDTO.class);
    }

    @Override
    public PaymentBankSlipDTO getPaymentBankSlip(Long id) {
        PaymentCart paymentCart = paymentCartRepository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));

        if (!paymentCart.getPaymentType().equals(PaymentTypeEnum.BANK_SLIP)) {
            throw new NotFoundException("Payment isn't of the type Bank Slip");
        }

        return DozerMapper.parseObject(paymentCart, PaymentBankSlipDTO.class);
    }


}
