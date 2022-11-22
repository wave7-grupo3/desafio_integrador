package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.repository.SellerRepository;
import com.group03.desafio_integrador.utils.mocks.SellerTestsMocks;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class SellerControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Seller mockSeller;
    private Seller mockCreateSeller;

    @BeforeEach
    void setUp() {

        mockSeller = SellerTestsMocks.mockSeller();
        mockCreateSeller = SellerTestsMocks.mockcreateSeller();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll_returnListSeller_whenSucess() throws Exception {
        List<Seller> sellerList = sellerRepository.findAll();

        ResultActions response = mockMvc.perform(
                get("/api/v1/seller")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        assertThat(sellerList).asList();
    }

    @Test
    void getById_returnSeller_whenSucess() throws Exception {

        sellerRepository.findById(mockSeller.getSellerId());

        ResultActions response = mockMvc.perform(
                get("/api/v1/seller/1")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.sellerName", CoreMatchers.is(mockSeller.getSellerName())));
    }

//    @Test
//    void save_returnNewSeller_whenSucess() throws Exception {
//
//        ResultActions response = mockMvc.perform(
//                post("/api/v1/seller")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(mockCreateSeller)));
//
//        response.andExpect(status().isCreated())
//                .andExpect(jsonPath("$.sellerName", CoreMatchers.is(mockCreateSeller.getSellerName())));
//
//    }
//
//    @Test
//    void update_returnUpdatedSeller_whenSucess() throws Exception {
//        sellerRepository.findById(mockSeller.getSellerId());
//
//        ResultActions response = mockMvc.perform(
//                put("/api/v1/seller/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(mockSeller)));
//
//        response.andExpect(status().isCreated())
//                .andExpect(jsonPath("$.sellerName", CoreMatchers.is(mockSeller.getSellerName())));
//
//    }
//
//    @Test
//    void deleteById_returnDeleteSellerById_whenSucess() throws Exception {
//        // verificar se precisa liberar algo para o banco deixar deletar.
//        // Error: could not execute statement; SQL [n/a]; constraint [null]
//        ResultActions response = mockMvc.perform(
//                delete("/api/v1/seller/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(status().isNoContent());
//    }


    @Test
    void filterProductPerSeller_returnProductSellerDTO_whenSuccess() throws Exception {

        ResultActions response = mockMvc.perform(
                get("/api/v1/seller/list?productId=1&orderBy=QT")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].seller", CoreMatchers.is(mockSeller.getSellerName())));
    }
}