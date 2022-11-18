package com.group03.desafio_integrador.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCreditCardDTO {

    @NotBlank
    @Size(min = 16, max = 16, message= "The card must have 16 digits")
    private String numberCard;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    private Integer numberInstallments;

    @NotNull
    private BigDecimal paymentValue;

    @NotBlank
    @Size(min = 3, max = 40, message= "The owner name must have at least 3 characters and cannot exceed 40 characters")
    private String ownerName;

    @NotBlank
    @Size(min = 3, max = 3, message= "The cvv must have 3 characters")
    private String cvv;

    @JsonFormat(pattern = "MM/yy")
    @NotBlank
    private String dueDate;
}
