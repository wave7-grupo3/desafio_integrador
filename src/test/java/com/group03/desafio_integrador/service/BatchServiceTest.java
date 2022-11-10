package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.repository.BatchRepository;
import static org.assertj.core.api.Assertions.*;
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

@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

    @InjectMocks
    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;

    private Batch mockBatch;
    private Batch mockCreateBatch;
    ProductAdvertising productId = ProductAdvertising.builder().productId(1L).build();

    @BeforeEach
    void setUp() {
        mockBatch = new Batch(1L,
                productId,
                10.0F,
                15,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022,11, 9, 11,43,0),
                30.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(150.00));

        mockCreateBatch = new Batch(null,
                productId,
                10.0F,
                15,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022,11, 9, 11,43,0),
                30.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(150.00));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById_returnSuccess_whenBatchExists() {
        BDDMockito.when(batchRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.ofNullable(mockBatch));

        Batch batchById = batchService.getById(1L);

        assertThat(batchById).isNotNull();
        assertThat(batchById).isEqualTo(mockBatch);
    }

    @Test
    void save_returnNewBatch_whenValidData() {
        BDDMockito.when(batchRepository.save(ArgumentMatchers.any(Batch.class))).thenReturn(mockBatch);
        Batch newBatch = batchService.save(mockCreateBatch);

        assertThat(newBatch).isNotNull();
        assertThat(newBatch.getBatchId()).isEqualTo(mockBatch.getBatchId());
        assertThat(newBatch.getProductQuantity()).isPositive();
        assertThat(newBatch.getVolume()).isPositive();
        assertThat(newBatch.getPrice()).isPositive();
        assertThat(newBatch).isEqualTo(mockBatch);
    }
}