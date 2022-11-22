package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.Buyer;

import java.util.List;

public class BuyerMock {

    public static List<Buyer> BuyerFromDatabase() {
        return List.of(new Buyer(null, "Buyer 1", "buyer1@email.com"),
                new Buyer(null, "Buyer 2", "buyer2@email.com"),
                new Buyer(null, "Buyer 3", "buyer3@email.com"),
                new Buyer(null, "Buyer 4", "buyer4@email.com"),
                new Buyer(null, "Buyer 5", "buyer5@email.com"));
    }
}
