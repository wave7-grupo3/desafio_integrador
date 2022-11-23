package com.group03.desafio_integrador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentBankSlipDTO {

    @NotBlank
    private String dueDate;

    @NotBlank
    private String barCode;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    private BigDecimal paymentValue;

    private PaymentStatusEnum status;
}
