package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.dto.PaymentPixDTO;
import com.group03.desafio_integrador.entities.PaymentCart;

public interface IPaymentCartService {
    PaymentCart insertPaymentCreditCard(PaymentCreditCardDTO paymentCreditCardDTO, Long idCart);
    PaymentCart insertPaymentBankSlip(PaymentBankSlipDTO paymentCreditCardDTO, Long idCart);
    PaymentCart insertPaymentPix(PaymentPixDTO paymentCreditCardDTO, Long idCart);
    PaymentCreditCardDTO getPaymentCreditCard(Long id);
    PaymentPixDTO getPaymentPix(Long id);
    PaymentBankSlipDTO getPaymentBankSlip(Long id);
}
