package com.group03.desafio_integrador.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PaymentBankSlip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dueDate;

    private String barCode;

    @NotBlank
    private String cpf;

    private BigDecimal paymentValue;

    private PaymentStatusEnum paymentStatus;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

}
