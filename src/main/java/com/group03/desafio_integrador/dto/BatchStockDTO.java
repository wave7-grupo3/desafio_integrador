package com.group03.desafio_integrador.dto;


import com.group03.desafio_integrador.entities.Batch;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchStockDTO {
    private List<Batch> batchStock;
}

