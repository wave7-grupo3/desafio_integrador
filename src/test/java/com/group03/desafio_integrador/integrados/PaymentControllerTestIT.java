package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.dto.PaymentPixDTO;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PaymentControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String cpf;

    private ShoppingCart shoppingCart;

    private PaymentCreditCardDTO paymentCreditCardDTO;

    private PaymentBankSlipDTO paymentBankSlipDTO;

    private PaymentPixDTO paymentPixDTO;


    @BeforeEach
    void setUp() {
        cpf = "75012981065";

        shoppingCart = ShoppingCart.builder()
                .shoppingCartId(1L)
//                .paymentCart(paymentCartCreditCard)
                .buyer(TestsMocks.mockBuyer())
                .date(LocalDate.now())
                .totalCartPrice(100.0)
                .discountPayment(5.0)
                .build();

        paymentCreditCardDTO = PaymentCreditCardDTO.builder()
                .cvv("123")
                .cpf(cpf)
                .numberInstallments(1)
                .ownerName("Tester")
                .dueDate("10/10/2022")
                .numberCard("1234432109877809")
                .paymentValue(BigDecimal.valueOf(300))
                .build();

        paymentBankSlipDTO = PaymentBankSlipDTO.builder()
                .barCode("8762374652837496529837456")
                .cpf(cpf)
                .dueDate("10/10/2023")
                .paymentValue(BigDecimal.valueOf(475))
                .build();

        paymentPixDTO = PaymentPixDTO.builder()
                .copyPaste("005324bcb.gov.br")
                .paymentValue(BigDecimal.valueOf(570))
                .cpf(cpf)
                .dueDate("10/10/2023")
                .build();
    }

    @Test
    void insertPaymentCreditCard_returnSuccess_whenCardIsValid() throws Exception {
         ResultActions response = mockMvc.perform(post("/api/v1/payment-shopping-cart/credit-card?cartId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentCreditCardDTO)));

        response.andExpect(status().isOk());
//                .andExpect(jsonPath("$.batchStock[0].productQuantity", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getProductQuantity())));
    }

    @Test
    void insertPaymentBankSlip_returnSuccess_whenBankSlipIsValid() throws Exception {
               ResultActions response = mockMvc.perform(post("/api/v1/payment-shopping-cart/bank-slip?cartId=2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentBankSlipDTO)));

        response.andExpect(status().isOk());
//                .andExpect(jsonPath("$.batchStock[0].productQuantity", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getProductQuantity())));
    }

    @Test
    void insertPaymentPix_returnSuccess_whenPixIsValid() throws Exception {
              ResultActions response = mockMvc.perform(post("/api/v1/payment-shopping-cart/pix?cartId=3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentPixDTO)));

        response.andExpect(status().isOk());
//                .andExpect(jsonPath("$.batchStock[0].productQuantity", CoreMatchers.is(mockCreateInboundOrder.getBatchList().get(0).getProductQuantity())));
    }


    @Test
    void getPaymentCreditCard_returnSuccess_whenValidData() throws Exception {
        ResultActions response = mockMvc.perform(
                get("/api/v1/payment-shopping-cart/credit-card/3")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    void getPaymentBankSlip_returnSuccess_whenValidData() throws Exception {
        ResultActions response = mockMvc.perform(
                get("/api/v1/payment-shopping-cart/bank-slip/4")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

    @Test
    void getPaymentPix_returnSuccess_whenValidData() throws Exception {
        ResultActions response = mockMvc.perform(
                get("/api/v1/payment-shopping-cart/pix/5")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }


}