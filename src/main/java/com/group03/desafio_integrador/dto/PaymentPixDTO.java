package com.group03.desafio_integrador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentPixDTO {
    private String copyPaste;

    private String dueDate;

    private String cpf;

    private BigDecimal paymentValue;
}
