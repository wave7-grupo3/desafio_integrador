package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAdvertising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    // TODO: 09/11/22 verificar campos redundantes
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
    private LocalDate fabricationDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
    private LocalDateTime fabricationTime;

    private String productName;

    private String description;

    // TODO: revisar atributos ao aplicar regra de negócio para carrinho de compras
    // private BigDecimal productPrice;
    // private Integer productQuantity;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("productAdvertisingList")
    private Seller seller;


}
