package com.group03.desafio_integrador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ProductQuantitySellerDTO {
    private Long productId;
    private Integer quantity;
    private LocalDate fabrication;
    private LocalDate expirationDate;
    private Integer customerEvaluator;
    private String seller;
}
