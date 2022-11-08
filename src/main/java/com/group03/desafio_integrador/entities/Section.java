package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    private String name;

    private CategoryEnum category;

    @ManyToOne
    @JoinColumn(name = "warehouse_section_id")
    @JsonIgnoreProperties("sectionList")
    private Warehouse warehouse;

}
