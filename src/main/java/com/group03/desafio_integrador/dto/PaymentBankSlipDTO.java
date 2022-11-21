package com.group03.desafio_integrador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentBankSlipDTO {
    private String dueDate;

    private String barCode;

    private String cpf;

    private BigDecimal paymentValue;

    private PaymentStatusEnum status;
}
