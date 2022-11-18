package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.entities.PaymentCreditCard;
import com.group03.desafio_integrador.service.interfaces.IPaymentCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/payment-shopping-cart")
public class PaymentController {

    @Autowired
    private IPaymentCreditCardService creditCardService;

    @PostMapping("/credit-card")
    public ResponseEntity<PaymentCreditCard> paymentCreditCard(@RequestParam(value = "cartId") Long id, @Valid @RequestBody PaymentCreditCardDTO paymentCreditCardDTO) {
        return new ResponseEntity<>(creditCardService.insert(paymentCreditCardDTO, id), HttpStatus.OK);
    }

    @PostMapping("/bank-slip")
    public ResponseEntity<PaymentCreditCard> paymentBankSlip(@RequestParam(value = "cartId") Long id, @Valid @RequestBody PaymentCreditCardDTO paymentCreditCardDTO) {
        return new ResponseEntity<>(creditCardService.insert(paymentCreditCardDTO, id), HttpStatus.OK);
    }
//
//    @PostMapping("/credit-card")
//    public ResponseEntity<PaymentCreditCard> paymentCreditCard(@RequestParam(value = "cartId") Long id, @Valid @RequestBody PaymentCreditCardDTO paymentCreditCardDTO) {
//        return new ResponseEntity<>(creditCardService.insert(paymentCreditCardDTO, id), HttpStatus.OK);
//    }
}
