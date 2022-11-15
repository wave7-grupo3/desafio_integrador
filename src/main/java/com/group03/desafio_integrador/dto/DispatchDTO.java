package com.group03.desafio_integrador.dto;

import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import com.group03.desafio_integrador.entities.entities_enum.DispatchStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DispatchDTO {
    private Long id_Packing;
    private Long buyer_id;
    private CategoryEnum category;
    private DispatchStatusEnum status;
}
