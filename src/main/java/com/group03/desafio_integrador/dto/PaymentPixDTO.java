package com.group03.desafio_integrador.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentPixDTO {

    @NotBlank
    private String copyPaste;

    @NotBlank
    private String dueDate;

    @NotBlank
    private String cpf;

    @NotNull
    private BigDecimal paymentValue;
}
