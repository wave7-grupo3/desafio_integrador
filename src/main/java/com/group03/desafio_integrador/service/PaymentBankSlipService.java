package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.entities.PaymentBankSlip;
import com.group03.desafio_integrador.entities.PaymentCreditCard;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import com.group03.desafio_integrador.entities.mapper.DozerMapper;
import com.group03.desafio_integrador.repository.PaymentBankSlipRepository;
import com.group03.desafio_integrador.repository.PaymentCreditCardRepository;
import com.group03.desafio_integrador.service.interfaces.IPaymentBankSlipService;
import com.group03.desafio_integrador.service.interfaces.IPaymentCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentBankSlipService implements IPaymentBankSlipService {

    @Autowired
    private PaymentBankSlipRepository repository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Método responsável por retornar a sessão de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Payment - Retorna uma entidade do tipo Payment.
     */
    @Override
    public PaymentBankSlip getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));
    }

    /**
     * Método responsável por salvar nova sessão.
     * @author Gabriel Morais
     * @param Payment - Payment
     * @return Payment - Retorna uma entidade do tipo Payment.
     */
    @Override
    public PaymentBankSlip save(PaymentBankSlip Payment) {
        return repository.save(Payment);
    }

    @Override
    public PaymentBankSlip insert(PaymentBankSlipDTO paymentBankSlipDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        PaymentBankSlip paymentBankSlip = DozerMapper.parseObject(paymentBankSlipDTO, PaymentBankSlip.class);

        paymentBankSlip.setTimestamp(LocalDateTime.now());
//      paymentCreditCard.setPaymentStatus(PaymentStatusEnum.PAID);

//        shoppingCart.setOrderStatus(OrderStatusEnum.FINALIZADO);

        Double finalPrice = shoppingCart.getTotalCartPrice() * 0.95;
        shoppingCart.setTotalCartPrice(finalPrice);

         paymentBankSlip.setShoppingCart(shoppingCart);

        return null;
    }
}
