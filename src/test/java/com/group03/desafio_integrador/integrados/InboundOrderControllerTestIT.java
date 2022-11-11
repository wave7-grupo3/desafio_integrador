package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
class InboundOrderControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    List<Batch> batchList = new ArrayList<>();
    BatchStockDTO batchDTO;
    private InboundOrder mockCreateInboundOrder;
    private InboundOrder mockInboundOrder;

    private Batch mockBatch;



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

        mockCreateInboundOrder = new InboundOrder(null,
                LocalDate.parse("2022-11-09"),
                Section.builder().sectionId(1L).build(),
                Warehouse.builder().warehouseId(1L).build(),
                batchList);

        mockInboundOrder = new InboundOrder(1L,
                LocalDate.parse("2022-11-09"),
                Section.builder().sectionId(1L).build(),
                Warehouse.builder().warehouseId(1L).build(),
                batchList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() throws Exception {
        Double currentTemperature = Double.valueOf(mockCreateInboundOrder.getBatchList().get(0).getCurrentTemperature());
        ResultActions response = mockMvc.perform(post("/api/v1/fresh-products/inboundorder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockCreateInboundOrder)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock[0].currentTemperature", CoreMatchers.is(currentTemperature)))
                .andExpect(jsonPath("$.batchStock[0].productQuantity", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getProductQuantity())))
                .andExpect(jsonPath("$.batchStock[0].fabricationDate", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getFabricationDate())))
                .andExpect(jsonPath("$.batchStock[0].fabricationTime", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getFabricationTime())))
                .andExpect(jsonPath("$.batchStock[0].volume", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getVolume())))
                .andExpect(jsonPath("$.batchStock[0].expirationDate", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getExpirationDate())))
                .andExpect(jsonPath("$.batchStock[0].price", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getPrice())));
    }

    @Test
    void getAll_returnSuccess_whenReturningListInboundOrder() throws Exception {
        List<InboundOrder> inboudOrders = inboundOrderRepository.findAll();

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        assertThat(inboudOrders).asList();
    }

    @Test
    void update() throws Exception {
        Integer batchId = Math.toIntExact(mockBatch.getBatchId());
        ResultActions response = mockMvc.perform(put("/api/v1/fresh-products/inboundorder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockBatch)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchId", CoreMatchers.is(batchId)));
    }
}