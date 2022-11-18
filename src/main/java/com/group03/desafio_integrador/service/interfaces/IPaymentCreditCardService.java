package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.entities.PaymentCreditCard;

public interface IPaymentCreditCardService {
    PaymentCreditCard getById(Long id);
    PaymentCreditCard save(PaymentCreditCard Payment);
    PaymentCreditCard insert(PaymentCreditCardDTO paymentCreditCardDTO, Long idCart);
}
