package com.group03.desafio_integrador.advice.exeptions;

import com.group03.desafio_integrador.advice.ExceptionDetails;
import com.group03.desafio_integrador.advice.ValidationErrorDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class NotFoundException extends RuntimeException {

    private List<ValidationErrorDetail> errors;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, List<ValidationErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }


}
