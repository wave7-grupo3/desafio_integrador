package com.group03.desafio_integrador.advice.exeptions;

import com.group03.desafio_integrador.advice.ValidationErrorDetail;

import java.util.List;

public class NotAcceptableException extends RuntimeException{
    public NotAcceptableException(String message) {
        super(message);
    }
}
