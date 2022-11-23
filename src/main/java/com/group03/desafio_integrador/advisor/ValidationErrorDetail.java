package com.group03.desafio_integrador.advisor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorDetail {
    private String field;
    private String message;
    private String rejectedValue;
}
