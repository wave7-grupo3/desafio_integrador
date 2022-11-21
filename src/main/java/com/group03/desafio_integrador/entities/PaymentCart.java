package com.group03.desafio_integrador.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentTypeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String copyPaste;

    private String dueDate;

    private String cpf;

    private BigDecimal paymentValue;

    private PaymentStatusEnum paymentStatus;

    @Size(min = 16, max = 16, message= "The card must have 16 digits")
    private String numberCard;

    private Integer numberInstallments;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
    private LocalDateTime timestamp;

    private String barCode;

    private PaymentTypeEnum paymentType;

}
