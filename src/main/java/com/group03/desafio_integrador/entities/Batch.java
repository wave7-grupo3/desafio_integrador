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
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    private Long productId;

    private Float currentTemperature;

    private Integer productQuantity;

    private LocalDate fabricationDate;

    private LocalDateTime fabricationTime;

    private Float volume;

    private LocalDate expirationDate;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "inbound_order_id")
    @JsonIgnoreProperties("batchList")
    private InboundOrder inboundOrder;

}