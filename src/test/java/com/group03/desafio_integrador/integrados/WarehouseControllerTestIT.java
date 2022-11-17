package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.repository.BatchRepository;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WarehouseControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // TODO: 17/11/22 Teste requisito 4 - Rosalia
    @Test
    void getAllStockProductWarehouse() throws Exception {
//       ResultActions resultActions = mockMvc.perform(post("/api/v1/fresh-products/inboundorder")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(TestsMocks.mockCreateInboundOrder())));
//
//       MockHttpServletResponse result = resultActions
//               .andReturn()
//               .getResponse();
//
//        List<Batch> batch = result.
//
//                ProductAdvertising productId = ProductAdvertising.builder()
//                .productId(batchList.get(0).getProductId().getProductId()).build();
//
//        ResultActions response = mockMvc.perform(
//                get("/api/v1/fresh-products/warehouse/" + productId)
//                        .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(status().isOk());
//                .andExpect(jsonPath("$.", CoreMatchers.is(currentTemperature));
    }
}