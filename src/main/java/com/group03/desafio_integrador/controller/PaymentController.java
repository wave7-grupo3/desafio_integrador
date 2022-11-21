package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.dto.PaymentPixDTO;
import com.group03.desafio_integrador.entities.PaymentCart;
import com.group03.desafio_integrador.service.interfaces.IPaymentCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/payment-shopping-cart")
public class PaymentController {

    @Autowired
    private IPaymentCartService paymentCartService;

    @PostMapping("/credit-card")
    public ResponseEntity<PaymentCart> paymentCreditCard(@RequestParam(value = "cartId") Long id, @Valid @RequestBody PaymentCreditCardDTO paymentCreditCardDTO) {
        return new ResponseEntity<>(paymentCartService.insertPaymentCreditCard(paymentCreditCardDTO, id), HttpStatus.OK);
    }

    @PostMapping("/bank-slip")
    public ResponseEntity<PaymentCart> paymentBankSlip(@RequestParam(value = "cartId") Long id, @Valid @RequestBody PaymentBankSlipDTO paymentBankSlipDTO) {
        return new ResponseEntity<>(paymentCartService.insertPaymentBankSlip(paymentBankSlipDTO, id), HttpStatus.OK);
    }

    @PostMapping("/pix")
    public ResponseEntity<PaymentCart> paymentPix(@RequestParam(value = "cartId") Long id, @Valid @RequestBody PaymentPixDTO paymentPixDTO) {
        return new ResponseEntity<>(paymentCartService.insertPaymentPix(paymentPixDTO, id), HttpStatus.OK);
    }

    @GetMapping("/credit-card/{id}")
    public ResponseEntity<PaymentCreditCardDTO> getPaymentCreditCard(@PathVariable Long id) {
        return new ResponseEntity<>(paymentCartService.getPaymentCreditCard(id), HttpStatus.OK);
    }

    @GetMapping("/bank-slip/{id}")
    public ResponseEntity<PaymentBankSlipDTO> getPaymentBankSlip(@PathVariable Long id) {
        return new ResponseEntity<>(paymentCartService.getPaymentBankSlip(id), HttpStatus.OK);
    }

    @GetMapping("/pix/{id}")
    public ResponseEntity<PaymentPixDTO> getPaymentPix(@PathVariable Long id) {
        return new ResponseEntity<>(paymentCartService.getPaymentPix(id), HttpStatus.OK);
    }
}
