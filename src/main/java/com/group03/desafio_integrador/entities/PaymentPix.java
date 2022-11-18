package com.group03.desafio_integrador.entities;


import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PaymentPix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String copyPaste;

    private String dueDate;

    private String cpf;

    private BigDecimal paymentValue;

    private PaymentStatusEnum paymentStatus;

    @OneToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

}
