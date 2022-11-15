package com.group03.desafio_integrador.entities;

import com.group03.desafio_integrador.dto.BatchDTO;
import com.group03.desafio_integrador.entities.entities_enum.SortingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortingBatch {
    private SortingEnum filter;
    private List<Batch> batchList;

    public static List<BatchDTO> ordenatedByBatch(List<BatchDTO> batches) {
       return batches.stream().sorted(Comparator.comparing(BatchDTO::getBatchId))
                .collect(Collectors.toList());
    }

    public static List<BatchDTO> ordenatedByQuantity(List<BatchDTO> batches) {
        return batches.stream().sorted(Comparator.comparing(BatchDTO::getQuantity))
                .collect(Collectors.toList());
    }

    public static List<BatchDTO> ordenatedByExpirationDate(List<BatchDTO> batches) {
        return batches.stream().sorted(Comparator.comparing(BatchDTO::getExpirationDate))
                .collect(Collectors.toList());
    }
}

