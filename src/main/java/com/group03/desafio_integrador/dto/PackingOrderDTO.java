package com.group03.desafio_integrador.dto;

import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import lombok.*;

@Getter
@Setter
@Builder
public class PackingOrderDTO {
    private Long cart_product_id;
    private Long product_id;
    private String order_status;
    private Long buyer_id;
    private CategoryEnum type;
}