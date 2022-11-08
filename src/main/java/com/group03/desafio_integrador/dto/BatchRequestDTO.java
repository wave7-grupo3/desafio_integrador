package com.group03.desafio_integrador.dto;


import com.group03.desafio_integrador.entities.InboundOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatchRequestDTO {
    private InboundOrder inboundOrder;
}