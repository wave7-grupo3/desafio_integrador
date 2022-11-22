package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.entities.Section;
import com.group03.desafio_integrador.entities.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InboundOrderMock {

    public static List<InboundOrder> inboundOrderFromDatabase(List<Section> sections, List<Warehouse> warehouses) {
        return List.of(
                new InboundOrder(
                        null,
                        LocalDate.parse("2022-11-09"),
                        sections.get(0),
                        warehouses.get(0),
                        new ArrayList<>()),
                new InboundOrder(
                        null,
                        LocalDate.parse("2022-11-09"),
                        sections.get(0),
                        warehouses.get(0),
                        new ArrayList<>()));
    }

}
