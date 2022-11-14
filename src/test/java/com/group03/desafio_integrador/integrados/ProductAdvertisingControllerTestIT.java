package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import com.group03.desafio_integrador.repository.CartProductRepository;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
class ProductAdvertisingControllerTestIT {


     @Autowired
    private MockMvc mockMvc;

     @Autowired
     private ProductAdvertisingRepository productAdvertisingRepository;

     @Autowired
     private CartProductRepository cartProductRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ProductAdvertising> mockProductList;
    private PurchaseOrderDTO mockCreateCartRequest;

    private ShoppingCartTotalDTO mockCreateCartResponse;
    private List<CartProduct> mockCartProductOrderList;
    static ShoppingCart shoppingCartId = ShoppingCart.builder().shoppingCartId(1L).build();


    @BeforeEach
    void setUp() {
        mockProductList = TestsMocks.mockProductList();
        mockCreateCartRequest = TestsMocks.mockCreateCartRequest();
        mockCreateCartResponse = TestsMocks.mockCreateCartResponse();
        mockCartProductOrderList = TestsMocks.mockCartProductOrderList();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll_returnListProductAdvertising_whenNotEmpty() throws Exception {
        List<ProductAdvertising> products = productAdvertisingRepository.findAll();

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        assertThat(products).asList();
    }

    @Test
    void getAllByCategory_returnFilteredProductListByCategory_whenValidCategory() throws Exception {
        List<ProductAdvertising> productsFresh = productAdvertisingRepository.findAllByCategory(CategoryEnum.FS);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/list?category=FS")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        assertThat(productsFresh).asList();
    }

    @Test
    void registerOrder_returnTotalPrice_whenShoppingCartIsCriated() throws Exception {
        ResultActions response = mockMvc.perform(post("/api/v1/fresh-products/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockCreateCartRequest)));

        response.andExpect(status().isCreated())
               .andExpect(jsonPath("$.totalPrice", CoreMatchers.is(mockCreateCartResponse.getTotalPrice())));
    }

    @Test
    void getCartProducts() throws Exception {
        List<CartProduct> shoppingCart = cartProductRepository.findAllByShoppingCart(shoppingCartId);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/orders/1")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        //assertThat(shoppingCart).isEqualTo(mockCartProductOrderList);
    }

    @Test
    void updateCartStatus() {
    }
}