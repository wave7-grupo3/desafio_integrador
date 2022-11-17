package com.group03.desafio_integrador.integrados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductWarehouseStockDTO;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.repository.CartProductRepository;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
     private InboundOrderRepository inboundOrderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ProductAdvertising> mockProductList;

    private PurchaseOrderDTO mockCreateCartRequest;

    private PurchaseOrderDTO mockErrorCartRequest;

    private ShoppingCartTotalDTO mockCreateCartResponse;

    private ShoppingCart mockShoppingCartFinished;

    private InboundOrder mockCreateSortInboundOrder;

    private List<CartProduct> mockCartProductOrderList;
    
    static ShoppingCart shoppingCartId = ShoppingCart.builder().shoppingCartId(1L).build();

    @BeforeEach
    void setUp() {
        mockProductList = TestsMocks.mockProductList();
        mockCreateCartRequest = TestsMocks.mockCreateCartRequest();
        mockCreateCartResponse = TestsMocks.mockCreateCartResponse();
        mockErrorCartRequest = TestsMocks.mockErrorCartRequest();
        mockCartProductOrderList = TestsMocks.mockCartProductOrderList();
        mockShoppingCartFinished = TestsMocks.mockShoppingCartFinished();
        mockCreateSortInboundOrder = TestsMocks.mockCreateSortInboundOrder();
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
    void getAllByCategory_throwsNotFoundError_whenCategoryIsNotValid() throws Exception {
        List<ProductAdvertising> productsFresh = productAdvertisingRepository.findAllByCategory(CategoryEnum.FS);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/list?category=FT")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("This category is not valid")));
    }

    @Test
    void registerOrder_returnTotalPrice_whenShoppingCartIsCreated() throws Exception {
        ResultActions response = mockMvc.perform(post("/api/v1/fresh-products/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockCreateCartRequest)));

        response.andExpect(status().isCreated())
               .andExpect(jsonPath("$.totalPrice", CoreMatchers.is(mockCreateCartResponse.getTotalPrice())));
    }

    @Test
    void registerOrder_throwsNotFoundError_whenProductDoesNotExistInABatch() throws Exception {
        ResultActions response = mockMvc.perform(post("/api/v1/fresh-products/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockErrorCartRequest)));

        response.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", CoreMatchers.is("No batch found with this product!")));
    }

    @Test
    void getCartProducts_returnCartProduct_whenShoppingCartExist() throws Exception {
        List<CartProduct> shoppingCart = cartProductRepository.findAllByShoppingCart(shoppingCartId);

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/orders/1")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk());
        //assertThat(shoppingCart).isEqualTo(mockCartProductOrderList);
    }

    @Test
    void updateCartStatus_returnCartProduct_whenStatusIsUpdated() throws Exception {
        Integer shoppingCartId = Math.toIntExact(mockShoppingCartFinished.getShoppingCartId());
        ResultActions response = mockMvc.perform(put("/api/v1/fresh-products/orders?orderId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockShoppingCartFinished)));

        response.andExpect(status().isOk())
               .andExpect(jsonPath("$.shoppingCartId", CoreMatchers.is(shoppingCartId)));
               //.andExpect(jsonPath("$.orderStatus", CoreMatchers.is(mockShoppingCartFinished.getOrderStatus())));
    }

    @Test
    void getAllProductWarehouseStock_returnListProductWarehouseStockDTO_whenProductExists() throws Exception {
        Integer sectionId = Math.toIntExact(TestsMocks.mockCreateInboundOrder().getSectionId().getSectionId());

        mockMvc.perform(
                post("/api/v1/fresh-products/inboundorder/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestsMocks.mockCreateInboundOrder())));

       ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/list?productId=1")
                        .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(status().isOk())
                .andExpect(jsonPath("$..sectionDTO.sectionId", CoreMatchers.is(List.of(sectionId))));

    }

    @Test
    void getAllOrdinancesForBatches_returnOrderedProduckWarehouseStrockDTOListByExpirantionDate_whenOrderParameterIsValid() throws Exception {
        String dueDate = TestsMocks.mockCreateSortInboundOrder().getBatchList().get(0).getExpirationDate().toString();

        mockMvc.perform(
                post("/api/v1/fresh-products/inboundorder/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(TestsMocks.mockCreateSortInboundOrder())));

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/list?productId=1&sorting=V")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$..batchDTO[0].expirationDate", CoreMatchers.is(List.of(dueDate))));
        List<InboundOrder> productWarehouseStock = inboundOrderRepository.findAll();

        String expectedResponse = "[{\"sectionDTO\":{\"sectionId\":1,\"warehouseId\":2},\"productId\":5,\"batchDTO\":[{\"batchId\":1,\"quantity\":200,\"expirationDate\":\"2022-12-30\"},{\"batchId\":3,\"quantity\":15,\"expirationDate\":\"2022-12-30\"},{\"batchId\":9,\"quantity\":15,\"expirationDate\":\"2022-12-30\"}]},{\"sectionDTO\":{\"sectionId\":1,\"warehouseId\":1},\"productId\":5,\"batchDTO\":[{\"batchId\":4,\"quantity\":200,\"expirationDate\":\"2022-12-30\"},{\"batchId\":6,\"quantity\":15,\"expirationDate\":\"2022-12-30\"}]}]";

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/list?productId=5")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    void getAllOrdinancesForBatches_returnOrderedProduckWrehouseStrockDTOList_whenOrderParameterIsValid() throws Exception {
        List<InboundOrder> productWarehouseStock = inboundOrderRepository.findAll();

        String expectedResponse = "[{\"sectionDTO\":{\"sectionId\":1,\"warehouseId\":2},\"productId\":5,\"batchDTO\":[{\"batchId\":3,\"quantity\":15,\"expirationDate\":\"2022-11-30\"},{\"batchId\":9,\"quantity\":15,\"expirationDate\":\"2022-12-26\"},{\"batchId\":1,\"quantity\":200,\"expirationDate\":\"2022-12-28\"}]},{\"sectionDTO\":{\"sectionId\":1,\"warehouseId\":1},\"productId\":5,\"batchDTO\":[{\"batchId\":4,\"quantity\":200,\"expirationDate\":\"2022-12-20\"},{\"batchId\":6,\"quantity\":15,\"expirationDate\":\"2022-12-22\"}]}]";

        ResultActions response = mockMvc.perform(
                get("/api/v1/fresh-products/list?productId=5&sorting=V")
                        .contentType(MediaType.APPLICATION_JSON) );

        response.andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
