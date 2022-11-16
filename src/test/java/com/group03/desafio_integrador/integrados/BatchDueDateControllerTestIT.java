package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.repository.BatchRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
class BatchDueDateControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ObjectMapper objectMapper;

    List<Batch> batchList = new ArrayList<>();

    private Batch mockBatch;

    @BeforeEach
    void setUp() {

        Batch mockBatchList = TestsMocks.createBatch();

        mockBatch = TestsMocks.mockBatch();

        batchList.add(mockBatchList);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getAllDueDate_returnBatchDueDateStockDTO_whenNotEmpty() throws Exception {
        List<Batch> batchList = batchRepository.findAllByExpirationDateGreaterThan(mockBatch.getExpirationDate());

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/due-date?numberOfDays=30&section=1")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        assertThat(batchList).asList();
    }

    @Test
    void getAllDueDateCategory_returnBatchDueDateStockDTO_whenNotEmpty() throws Exception {
        List<Batch> batchListDate = batchRepository.findAllByExpirationDateGreaterThan(mockBatch.getExpirationDate());

        List<Batch> batchListCategory = batchRepository.findAllByProductId(mockBatch.getProductId());

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/due-date/list?numberOfDays=30&category=FS&sorting=asc")

                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        assertThat(batchListDate).asList();
        assertThat(batchListCategory).asList();
    }

}
