package com.group03.desafio_integrador.advisor.exceptions;

import com.group03.desafio_integrador.advisor.ValidationErrorDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class PaymentInvalidException extends RuntimeException {

//    @Transient
    private List<ValidationErrorDetail> errors;

    public PaymentInvalidException(String message) {
        super(message);
    }

    public PaymentInvalidException(String message, List<ValidationErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }


}
