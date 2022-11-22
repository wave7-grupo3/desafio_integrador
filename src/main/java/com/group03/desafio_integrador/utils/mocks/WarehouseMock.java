package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.entities.Warehouse;

import java.util.List;

public class WarehouseMock {

    public static List<Warehouse> warehouseFromDatabase(List<Manager> managers) {
        return List.of(new Warehouse(null, Double
                        .valueOf("10000.0"), managers.get(0)),
                new Warehouse(null, Double
                        .valueOf("15000.0"), managers.get(1)),
                new Warehouse(null, Double
                        .valueOf("30000.0"), managers.get(2)),
                new Warehouse(null, Double
                        .valueOf("5000.0"), managers.get(3)),
                new Warehouse(null, Double
                        .valueOf("50000.0"), managers.get(4))
        );
    }
}
