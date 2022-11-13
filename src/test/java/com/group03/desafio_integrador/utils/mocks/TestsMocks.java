package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestsMocks {
    static ProductAdvertising productId = ProductAdvertising.builder().productId(1L).build();
    private static final List<Batch> batchList = new ArrayList<>();

    public static Batch mockBatch() {

        return new Batch(1L,
                productId,
                10.0F,
                15,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022, 11, 9, 11, 43, 0),
                30.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(150.00));
    }

    public static Batch createBatch() {

        Batch batch = new Batch(null,
                productId,
                10.0F,
                15,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022, 11, 9, 11, 43, 0),
                30.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(150.00));

        batchList.add(batch);

        return batch;

    }



    public static Batch mockUpdateBatch() {

        return new Batch(1L,
                productId,
                10.0F,
                20,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022, 11, 9, 11, 43, 0),
                40.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(200.00));

    }

    public static InboundOrder mockInboundOrder() {

        return new InboundOrder(1L,
                LocalDate.parse("2022-11-09"),
                Section.builder().sectionId(1L).build(),
                Warehouse.builder().warehouseId(1L).build(),
                batchList);

    }

    public static InboundOrder mockCreateInboundOrder() {

        return new InboundOrder(null,
                LocalDate.parse("2022-11-09"),
                Section.builder().sectionId(1L).build(),
                Warehouse.builder().warehouseId(1L).build(),
                batchList);
    }

    public static ProductAdvertising mockProductAdvertising() {

        return new ProductAdvertising(1L,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022, 11, 9, 11, 43, 0),
                "Maçã",
                "teste",
                Seller.builder().sellerId(1L).build(),
                CategoryEnum.FS,
                BigDecimal.valueOf(2),
                5
        );

    }
}
