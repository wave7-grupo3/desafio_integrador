package com.group03.desafio_integrador.utils.mocks;

import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ProductAdvertisingMock {

    public static List<ProductAdvertising> productsAdvertisingFromDatabase(List<Seller> sellers) {
        return List.of(new ProductAdvertising(null,
                        LocalDate.parse("2022-11-20"),
                        LocalDateTime.parse("2022-11-20 13:55:00"),
                        "Maçã",
                        "testes",
                        sellers.get(0),
                        CategoryEnum.FS,
                        BigDecimal.valueOf(2.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-11-20"),
                        LocalDateTime.parse("2022-11-20 13:55:00"),
                        "Banana",
                        "testes",
                        sellers.get(1),
                        CategoryEnum.FS,
                        BigDecimal.valueOf(3.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-10-20"),
                        LocalDateTime.parse("2022-10-20 13:55:00"),
                        "Morango",
                        "testes",
                        sellers.get(3),
                        CategoryEnum.FS,
                        BigDecimal.valueOf(5.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-10-20"),
                        LocalDateTime.parse("2022-10-20 13:55:00"),
                        "Uva",
                        "testes",
                        sellers.get(4),
                        CategoryEnum.FS,
                        BigDecimal.valueOf(5.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-09-20"),
                        LocalDateTime.parse("2022-09-20 13:55:00"),
                        "Queijo",
                        "testes",
                        sellers.get(0),
                        CategoryEnum.RF,
                        BigDecimal.valueOf(20.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-09-20"),
                        LocalDateTime.parse("2022-09-20 13:55:00"),
                        "Leite Pasteurizado",
                        "testes",
                        sellers.get(2),
                        CategoryEnum.RF,
                        BigDecimal.valueOf(4.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-09-20"),
                        LocalDateTime.parse("2022-09-20 13:55:00"),
                        "Iogurte Natural",
                        "testes",
                        sellers.get(2),
                        CategoryEnum.RF,
                        BigDecimal.valueOf(2.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-09-20"),
                        LocalDateTime.parse("2022-09-20 13:55:00"),
                        "Manteiga",
                        "testes",
                        sellers.get(3),
                        CategoryEnum.RF,
                        BigDecimal.valueOf(8.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2022-09-20"),
                        LocalDateTime.parse("2022-09-20 13:55:00"),
                        "Queijo Prato",
                        "testes",
                        sellers.get(4),
                        CategoryEnum.RF,
                        BigDecimal.valueOf(4.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2021-10-20"),
                        LocalDateTime.parse("2021-10-09 13:55:00"),
                        "Polpa de Morango",
                        "testes",
                        sellers.get(0),
                        CategoryEnum.FF,
                        BigDecimal.valueOf(2.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2021-10-20"),
                        LocalDateTime.parse("2021-10-09 13:55:00"),
                        "Polpa de Acerola",
                        "testes",
                        sellers.get(1),
                        CategoryEnum.FF,
                        BigDecimal.valueOf(2.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2021-10-20"),
                        LocalDateTime.parse("2021-10-09 13:55:00"),
                        "Polpa de Maracujá",
                        "testes",
                        sellers.get(2),
                        CategoryEnum.FF,
                        BigDecimal.valueOf(2.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2021-10-20"),
                        LocalDateTime.parse("2021-10-09 13:55:00"),
                        "Polpa de Graviola",
                        "testes",
                        sellers.get(3),
                        CategoryEnum.FF,
                        BigDecimal.valueOf(2.00),
                        15),
                new ProductAdvertising(null,
                        LocalDate.parse("2021-10-20"),
                        LocalDateTime.parse("2021-10-09 13:55:00"),
                        "Polpa de Abacaxi",
                        "testes",
                        sellers.get(4),
                        CategoryEnum.FF,
                        BigDecimal.valueOf(2.00),
                        15));
    }
}
