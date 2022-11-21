package com.group03.desafio_integrador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ProductSellerDTO {
    private Long productId;
    private Integer quantity;
    private LocalDate fabrication;
    private LocalDate expirationDate;
    private Integer sellerRating;
    private String seller;
}