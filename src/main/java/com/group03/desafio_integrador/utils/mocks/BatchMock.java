package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BatchMock {

    public static List<Batch> BatchFromDatabase() {
        return List.of(
                new Batch(null,
                        ProductAdvertising.builder().productId(1L).build(),
                        Float.valueOf("10.0"),
                        10,
                        LocalDate.parse("2022-11-30"),
                        LocalDateTime.of(2022, 11, 30, 11, 43, 0),
                        Float.valueOf("30.0"),
                        LocalDate.parse("2023-01-15"),
                        BigDecimal.valueOf(150.00)),
                new Batch(null,
                        ProductAdvertising.builder().productId(1L).build(),
                        Float.valueOf("10.0"),
                        10,
                        LocalDate.parse("2022-11-30"),
                        LocalDateTime.of(2022, 11, 30, 11, 43, 0),
                        Float.valueOf("50.0"),
                        LocalDate.parse("2023-02-25"),
                        BigDecimal.valueOf(150.00)));
    }

}
