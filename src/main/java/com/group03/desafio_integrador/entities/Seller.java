package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @NotBlank
    @Size(min = 3, max = 40, message = "The name must have at least 3 characters and cannot exceed 40 characters")
    private String sellerName;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("seller")
    @Size(min = 1)
    private List<ProductAdvertising> productAdvertisingList;

    private Integer sellerRating;
}
