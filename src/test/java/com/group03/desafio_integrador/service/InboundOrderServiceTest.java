package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.repository.*;
import com.sun.xml.bind.v2.TODO;
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
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InboundOrderServiceTest {

    @InjectMocks
    private InboundOrderService inboundOrderService;

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @Mock
    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;

    List<Batch> batchList = new ArrayList<>();
    BatchStockDTO batchDTO;
    Batch mockBatch;
    private InboundOrder mockInboundOrder;
    private InboundOrder mockCreateInboundOrder;

    @BeforeEach
    void setUp() {
        ProductAdvertising productId = ProductAdvertising.builder().productId(1L).build();

        Batch mockBatchList = new Batch(null,
                productId,
                10.0F,
                15,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022,11, 9, 11,43,0),
                30.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(150.00));

        mockBatch = new Batch(1L,
                productId,
                10.0F,
                15,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022,11, 9, 11,43,0),
                30.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(150.00));

        batchList.add(mockBatchList);
        batchDTO = new BatchStockDTO(batchList);

        mockInboundOrder = new InboundOrder(1L,
                LocalDate.parse("2022-11-09"),
                Section.builder().sectionId(1L).build(),
                Warehouse.builder().warehouseId(1L).build(),
                batchList);

        mockCreateInboundOrder = new InboundOrder(null,
                LocalDate.parse("2022-11-09"),
                Section.builder().sectionId(1L).build(),
                Warehouse.builder().warehouseId(1L).build(),
                batchList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll_returnListInboundOrders_whenNotEmpty() {
        BDDMockito.when(inboundOrderRepository.findAll())
                .thenReturn(Collections.singletonList(mockInboundOrder));

        List<InboundOrder> inboundOrderList = inboundOrderService.getAll();

        assertThat(inboundOrderList).isNotNull();
        assertThat(inboundOrderList).isNotEmpty();
        assertThat(inboundOrderList.get(0)).isEqualTo(mockInboundOrder);
    }

    @Test
    void validateWarehouse() throws Exception {

}

    @Test
    void validateProducts() {
    }

    @Test
    void validateSection() {
    }

    @Test
    void validateOrder() {
    }

    @Test
    void save_returnSuccess_whenValidData() throws Exception {

    }

    // TODO: 10/11/22 completar testes update - save 
    @Test
    void update() {
        BDDMockito.when(batchRepository.save(ArgumentMatchers.any(Batch.class)))
                .thenReturn(mockBatch);

        Batch updateBatch = inboundOrderService.update(mockBatch);

        batchService.save(mockBatch);

        assertThat(updateBatch).isNotNull();
    }
}