package com.group03.desafio_integrador.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shoppingCartId;

//    @ManyToMany
//    @JoinTable(
//            name = "cart_product",
//            joinColumns = @JoinColumn(name = "shopping_cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    @Valid
//    private Set<ProductAdvertising> productAdvertisingList;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
    @NotNull
    private LocalDate date;

    @Valid
    private OrderStatusEnum orderStatus;

    @Valid
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    @JsonIgnoreProperties("orderList")
    @JsonIgnore
    private Buyer buyer;

    private Double totalCartPrice;

}
