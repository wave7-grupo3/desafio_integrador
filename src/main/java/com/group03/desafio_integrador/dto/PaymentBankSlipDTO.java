package com.group03.desafio_integrador.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentBankSlipDTO {
    private String dueDate;

    private String barCode;

    private String cpf;

    private BigDecimal paymentValue;
}
