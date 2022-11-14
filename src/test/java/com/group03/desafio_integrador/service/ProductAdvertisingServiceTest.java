package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductAdvertisingServiceTest {

    @InjectMocks
    private ProductAdvertisingService productAdvertisingService;

    @Mock
    private ProductAdvertisingRepository productAdvertisingRepository;

    private ProductAdvertising mockProductAdvertising;

    @BeforeEach
    void setUp() {
        mockProductAdvertising = TestsMocks.mockProductAdvertising();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById_returnSuccess_whenProductAdvertisingExists() {
        BDDMockito.when(productAdvertisingRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.ofNullable(mockProductAdvertising));

        ProductAdvertising productById = productAdvertisingService.getById(1L);

        assertThat(productById).isNotNull();
        assertThat(productById).isEqualTo(mockProductAdvertising);
    }
}