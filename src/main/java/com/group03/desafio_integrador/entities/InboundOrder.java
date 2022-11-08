package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDate orderDate;

    private Long sectionId;

    private Long warehouseId;

    @OneToMany(mappedBy = "inboundOrder", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("inboundOrder")
    private List<Batch> batchList;

}
