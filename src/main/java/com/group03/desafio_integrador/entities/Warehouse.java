package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private Double capacity;

//    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("warehouse")
//    private List<Section> sectionList;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
