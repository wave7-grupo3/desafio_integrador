package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.repository.BatchRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

@ExtendWith(MockitoExtension.class)
class BatchServiceTest {

    @InjectMocks
    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;

    private Batch mockBatch;
    private Batch mockCreateBatch;

    @BeforeEach
    void setUp() {

        mockBatch = TestsMocks.mockBatch();

        mockCreateBatch = TestsMocks.createBatch();
        
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

    @Test
    void getById_returnRunTimeException_whenBatchIdNotFound() {
        BDDMockito.when(batchRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> batchService.getById(1L));

        assertThat(notFoundException.getMessage()).isEqualTo("Batch not found!");
    }
}