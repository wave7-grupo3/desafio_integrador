package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @DecimalMin(value = "100", message = "The minimum warehouse capacity value is 100m²")
    private Double capacity;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "manager_id")
    @Valid
    private Manager manager;
}
