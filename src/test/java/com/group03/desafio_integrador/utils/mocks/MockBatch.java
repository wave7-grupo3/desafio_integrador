package com.group03.desafio_integrador.utils.mocks;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockBatch {
    public Long batchId;
    public Long productId;
    public Float currentTemperature;
    public Integer productQuantity;
    public LocalDate fabricationDate ;
    public LocalDateTime fabricationTime;
    public double volume;
    public LocalDate expirationDate;
    public BigDecimal price;

    public MockBatch() {
        this.batchId = Long.valueOf("1");
        this.productId = Long.valueOf("1");
        this.currentTemperature = Float.valueOf("10.0");
        this.productQuantity = Integer.valueOf("15");
        this.fabricationDate = LocalDate.parse("2022-11-30");
        this.fabricationTime = LocalDateTime.parse("2022-11-09 11:43:00");
        this.volume = 30.0;
        this.expirationDate = LocalDate.parse("2022-11-30");
        this.price = BigDecimal.valueOf(150.00);
    }
}

