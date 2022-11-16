package com.group03.desafio_integrador.advisor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ValidationErrorDetail {
    private String field;
    private String message;
    private String rejectedValue;
}
