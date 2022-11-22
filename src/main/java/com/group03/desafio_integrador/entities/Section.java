package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @NotBlank
    private String name;

    @NotNull
    private Double capacity;

    @Valid
    private CategoryEnum category;


}
