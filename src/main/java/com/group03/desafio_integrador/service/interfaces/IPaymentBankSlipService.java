package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.entities.PaymentBankSlip;
import com.group03.desafio_integrador.entities.PaymentCreditCard;

public interface IPaymentBankSlipService {
    PaymentBankSlip getById(Long id);
    PaymentBankSlip save(PaymentBankSlip Payment);
    PaymentBankSlip insert(PaymentBankSlipDTO paymentCreditCardDTO, Long idCart);
}
