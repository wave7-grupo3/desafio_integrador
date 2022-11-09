package com.group03.desafio_integrador.advice;

import lombok.*;

@Data
@Builder
public class ValidationErrorDetail {
    private String field;
    private String message;
    private String rejectedValue;
}
