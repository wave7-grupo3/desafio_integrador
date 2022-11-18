package com.group03.desafio_integrador.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
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
public class PaymentCreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 16, max = 16, message= "The card must have 16 digits")
    private String numberCard;

    @NotBlank
    private String cpf;

    private Integer numberInstallments;

    private BigDecimal paymentValue;

    private PaymentStatusEnum paymentStatus;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
    private LocalDateTime timestamp;

    @NotNull
    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

}
