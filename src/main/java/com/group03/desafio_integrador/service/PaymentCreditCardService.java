package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.entities.PaymentCreditCard;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import com.group03.desafio_integrador.entities.mapper.DozerMapper;
import com.group03.desafio_integrador.repository.PaymentCreditCardRepository;
import com.group03.desafio_integrador.service.interfaces.IPaymentCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentCreditCardService implements IPaymentCreditCardService {

    @Autowired
    private  PaymentCreditCardRepository repository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * Método responsável por retornar a sessão de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Payment - Retorna uma entidade do tipo Payment.
     */
    @Override
    public PaymentCreditCard getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Payment not found!"));
    }

    /**
     * Método responsável por salvar nova sessão.
     * @author Gabriel Morais
     * @param Payment - Payment
     * @return Payment - Retorna uma entidade do tipo Payment.
     */
    @Override
    public PaymentCreditCard save(PaymentCreditCard Payment) {
        return repository.save(Payment);
    }

    @Override
    public PaymentCreditCard insert(PaymentCreditCardDTO paymentCreditCardDTO, Long idCart) {
        ShoppingCart shoppingCart = shoppingCartService.getById(idCart);

        PaymentCreditCard paymentCreditCard = DozerMapper.parseObject(paymentCreditCardDTO, PaymentCreditCard.class);

        paymentCreditCard.setPaymentStatus(PaymentStatusEnum.PAID);
        paymentCreditCard.setTimestamp(LocalDateTime.now());

        shoppingCart.setOrderStatus(OrderStatusEnum.FINALIZADO);

        paymentCreditCard.setShoppingCart(shoppingCart);

        return save(paymentCreditCard);
    }
}
