package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.dto.PaymentPixDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
        cpf = "95465432176";

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
                .dueDate("10/10/2022")
                .numberCard("1234432109877809")
                .paymentValue(BigDecimal.valueOf(100))
                .build();

        paymentBankSlipDTO = PaymentBankSlipDTO.builder()
                .barCode("8762374652837496529837456")
                .cpf(cpf)
                .dueDate("10/10/2023")
                .paymentValue(BigDecimal.valueOf(95))
                .build();

        paymentPixDTO = PaymentPixDTO.builder()
                .copyPaste("005324bcb.gov.br")
                .paymentValue(BigDecimal.valueOf(95))
                .cpf(cpf)
                .dueDate("10/10/2023")
                .build();
    }


    @Test
    void insertPaymentCreditCard_returnSuccess_whenValidData() throws Exception {
//        List<CartProduct> shoppingCart = cartProductRepository.findAllByShoppingCart(shoppingCartId);

        ResultActions response = mockMvc.perform(
                get("/api/v1/payment-shopping-cart/credit-card/1")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }


}