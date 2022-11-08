package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductAdvertising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private LocalDate fabricationDate;

    private LocalDateTime fabricationTime;

    private String productName;

    private String description;

    // TODO: revisar atributos ao aplicar regra de negócio para carrinho de compras
    // private BigDecimal productPrice;
    // private Integer productQuantity;

    @ManyToOne
    @JoinColumn(name = "product_advertising_id")
    @JsonIgnoreProperties("productAdvertisingList")
    private Seller seller;


}
