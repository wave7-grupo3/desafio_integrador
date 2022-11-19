package com.group03.desafio_integrador.utils.mocks;


import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SellerTestsMocks {
    private static final List<ProductAdvertising> productList = new ArrayList<>();

    private static final List<Seller> sellerList = new ArrayList<>();

    public static List<ProductAdvertising> mockProductList() {

        ProductAdvertising productFresh = new ProductAdvertising(1L,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022, 11, 9, 11, 43, 0),
                "Maçã",
                "teste",
                Seller.builder().sellerId(1L).build(),
                CategoryEnum.FS,
                BigDecimal.valueOf(2),
                5
        );

        ProductAdvertising productRefrigerated = new ProductAdvertising(2L,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022, 11, 9, 11, 43, 0),
                "Iogurte",
                "teste",
                Seller.builder().sellerId(1L).build(),
                CategoryEnum.RF,
                BigDecimal.valueOf(2),
                10
        );

        ProductAdvertising productFrozen = new ProductAdvertising(3L,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022, 11, 9, 11, 43, 0),
                "Polpa de Morango",
                "teste",
                Seller.builder().sellerId(1L).build(),
                CategoryEnum.FF,
                BigDecimal.valueOf(1),
                10
        );

        productList.add(productFresh);
        productList.add(productRefrigerated);
        productList.add(productFrozen);

        return productList;

    }

    public static Seller mockcreateSeller() {

        return new Seller(
                null,
                "Seller 1",
                productList,
                0);
    }

    public static Seller mockSeller() {

        return new Seller(
                1L,
                "Seller 1",
                mockProductList(),
                0);
    }

    public static List<Seller> mockSellerList() {

        Seller seller2 = new Seller(
                2L,
                "Seller 2",
                mockProductList(),
                0);

        sellerList.add(mockSeller());
        sellerList.add(seller2);

        return sellerList;
    }
}
