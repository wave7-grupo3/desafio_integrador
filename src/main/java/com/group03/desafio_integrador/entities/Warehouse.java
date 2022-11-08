package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private Double capacity;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("warehouse")
    private List<Section> sectionList;

    @OneToOne
    @JoinColumn(name = "warehouse_manager_id")
    private Manager manager;

}
