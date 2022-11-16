package com.group03.desafio_integrador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class BatchDueDateDTO {

    private Long batchNumber;
    private Long productId;
    private Long category;
    private LocalDate dueDate;
    private Integer quantity;
}
