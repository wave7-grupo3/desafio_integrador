package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.Seller;

import java.util.ArrayList;
import java.util.List;

public class SellerMock {

    public static List<Seller> sellerFromDatabase() {
        return List.of(new Seller(null, "SellerName 1", new ArrayList<>()),
                new Seller(null, "SellerName 2", new ArrayList<>()),
                new Seller(null, "SellerName 3", new ArrayList<>()),
                new Seller(null, "SellerName 4", new ArrayList<>()),
                new Seller(null, "SellerName 5", new ArrayList<>()));
    }

}
