package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.dto.ProductWarehouseStockDTO;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.repository.*;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
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
    private WarehouseService warehouseService;

    @Mock
    private BatchService batchService;
    
    public final List<Batch> batchList = new ArrayList<>();
    private Batch mockBatch;
    private Batch mockUpdateBatch;
    private InboundOrder mockInboundOrder;
    private InboundOrder mockCreateInboundOrder;
    private List<InboundOrder> mockCreateInboundOrderList;
    private Warehouse mockWarehouse;
    private List<ProductWarehouseStockDTO> mockProductWarehouseStockDTOList;

    @BeforeEach
    void setUp() {

        mockBatch = TestsMocks.mockBatch();

        Batch mockBatchList = TestsMocks.createBatch();

        batchList.add(mockBatchList);

        mockUpdateBatch = TestsMocks.mockUpdateBatch();

        mockInboundOrder = TestsMocks.mockInboundOrder();

        mockCreateInboundOrder = TestsMocks.mockCreateInboundOrder();

        mockCreateInboundOrderList = TestsMocks.mockCreateInboundOrderList();

        mockProductWarehouseStockDTOList = TestsMocks.mockProductWarehouseStockDTOList();

        mockWarehouse = TestsMocks.mockWarehouse();
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

    @Test
    void getAllProductWarehouseStock() throws Exception {
        BDDMockito.when(inboundOrderService.getAll())
                .thenReturn(mockCreateInboundOrderList);

        BDDMockito.doNothing().when(inboundOrderService)
              .validateOrder(ArgumentMatchers.any(InboundOrder.class));

        List<ProductWarehouseStockDTO> productWarehouseStockDTOList = inboundOrderService.getAllProductWarehouseStock(5L);

        BDDMockito.verify(inboundOrderService, BDDMockito.times(1))
                .validateOrder(ArgumentMatchers.any(InboundOrder.class));

        assertThat(productWarehouseStockDTOList).isNotNull();
    }

    @Test
    void getAllOrdinancesForBatches() {
        List<ProductWarehouseStockDTO> productWarehouseStockDTOList = inboundOrderService.getAllOrdinancesForBatches(mockProductWarehouseStockDTOList, "L");

        assertThat(productWarehouseStockDTOList).isNotNull();
        assertThat(productWarehouseStockDTOList).asList();
    }
}