package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.Manager;

import java.util.List;

public class ManagerMock {
    public static List<Manager> managerFromDatabase() {
        return List.of(new Manager(null, "Jason Momoa", "khal", "12345"),
                new Manager(null, "Orlando Blum", "legolas", "12345"),
                new Manager(null, "Zoe Saldanha", "gamora", "12345"),
                new Manager(null, "Dave Bautista", "drax", "12345"),
                new Manager(null, "Catie Blanchett", "galadriel", "12345"),
                new Manager(null, "Tatiana Maslany", "sheHulk", "12345"));
    }
}
