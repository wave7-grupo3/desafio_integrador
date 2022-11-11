package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.repository.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InboundOrderServiceTest {

    @InjectMocks
    @Spy
    private InboundOrderService inboundOrderService;

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @Mock
    private BatchService batchService;
    
    private final List<Batch> batchList = new ArrayList<>();
    private Batch mockBatch;
    private Batch mockUpdateBatch;

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
//        BatchStockDTO batchDTO = new BatchStockDTO(batchList);

        mockUpdateBatch = new Batch(1L,
                productId,
                10.0F,
                20,
                LocalDate.parse("2022-11-30"),
                LocalDateTime.of(2022,11, 9, 11,43,0),
                40.0F,
                LocalDate.parse("2022-11-30"),
                BigDecimal.valueOf(200.00));

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
    void save_returnSuccess_whenValidData() throws Exception {
        BDDMockito.doNothing().when(inboundOrderService)
                .validateOrder(ArgumentMatchers.eq(mockCreateInboundOrder));

        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InboundOrder.class)))
                .thenReturn(mockInboundOrder);

        BatchStockDTO newInboundOrder = inboundOrderService.save(mockCreateInboundOrder);

        BDDMockito.verify(inboundOrderService, BDDMockito.times(1))
                .validateOrder(ArgumentMatchers.eq(mockCreateInboundOrder));

        assertThat(newInboundOrder).isNotNull();
    }

    @Test
    void update_returnSuccess_whenValidData() {
        BDDMockito.when(batchService.getById(ArgumentMatchers.any(Long.class)))
                .thenReturn(mockBatch);

        batchService.getById(1L);

        BDDMockito.when(batchService.save(ArgumentMatchers.any(Batch.class)))
                .thenReturn(mockUpdateBatch);

        Batch updatedBatch = batchService.save(mockUpdateBatch);

        assertThat(updatedBatch).isNotNull();
        assertThat(updatedBatch.getVolume()).isEqualTo(mockUpdateBatch.getVolume());
        assertThat(updatedBatch.getProductQuantity()).isEqualTo(mockUpdateBatch.getProductQuantity());
        assertThat(updatedBatch.getPrice()).isEqualTo(mockUpdateBatch.getPrice());
    }
}