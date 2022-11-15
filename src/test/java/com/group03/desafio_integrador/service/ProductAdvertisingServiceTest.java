package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.Buyer;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProductAdvertisingServiceTest {

    @InjectMocks
    private ProductAdvertisingService productAdvertisingService;

    @InjectMocks
    private BuyerService buyerService;

    @Mock
    private ProductAdvertisingRepository productAdvertisingRepository;

    private ProductAdvertising mockProductAdvertising;

    private List<ProductAdvertising> mockProductList;

    private List<ProductAdvertising> mockProductListEmpty;

    private List<ProductAdvertising> mockProductListFresh;

    private List<ProductAdvertising> mockProductListFreshEmpty;

    private Buyer buyer;

    private PurchaseOrderDTO mockCreateCartRequest;



    @BeforeEach
    void setUp() {
        mockProductAdvertising = TestsMocks.mockProductAdvertising();
        mockProductList = TestsMocks.mockProductList();
        mockProductListEmpty = TestsMocks.mockProductListEmpty();
        mockProductListFresh = TestsMocks.mockProductListFresh();
        mockProductListFreshEmpty = TestsMocks.mockProductListFreshEmpty();
        buyer = TestsMocks.buyer();
        mockCreateCartRequest = TestsMocks.mockCreateCartRequest();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById_returnSuccess_whenProductAdvertisingExists() {
        BDDMockito.when(productAdvertisingRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.ofNullable(mockProductAdvertising));
        ProductAdvertising productById = productAdvertisingService.getById(1L);

        assertThat(productById).isNotNull();
        assertThat(productById).isEqualTo(mockProductAdvertising);
    }

    @Test
    void getById_returnNotFoundException_whenProductAdvertisingIsEmpty() throws NotFoundException{
        BDDMockito.when(productAdvertisingRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> productAdvertisingService.getById(1L));
        assertThat(notFoundException.getMessage()).isEqualTo("Product not found!");
    }

    @Test
    void getAll_returnSuccess_whenProductAdvertisingListExists() {
        BDDMockito.when(productAdvertisingRepository.findAll()).thenReturn(mockProductList);
        List<ProductAdvertising> listProductAdvertising = productAdvertisingService.getAll();

        assertThat(listProductAdvertising).asList();
        assertThat(listProductAdvertising).isNotNull();
        assertThat(listProductAdvertising).isNotEmpty();
        assertThat(listProductAdvertising.get(0)).isEqualTo(mockProductList.get(0));
    }

    @Test
    void getAll_returnNotFoundException_whenProductAdvertisingListIsEmpty() throws NotFoundException{
        BDDMockito.when(productAdvertisingRepository.findAll()).thenReturn(mockProductListEmpty);

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> productAdvertisingService.getAll());
        assertThat(notFoundException.getMessage()).isEqualTo("Product Advertising not found");
    }

    @Test
    void getAllByCategory_returnSuccess_whenProductAdvertisingCategoryListExists() {
        BDDMockito.when(productAdvertisingRepository.findAllByCategory(ArgumentMatchers.any(CategoryEnum.class))).thenReturn(mockProductListFresh);
        List<ProductAdvertising> listProductAdvertisingFresh = productAdvertisingService.getAllByCategory(String.valueOf(CategoryEnum.FS));

        assertThat(listProductAdvertisingFresh).isNotNull();
        assertThat(listProductAdvertisingFresh).isNotEmpty();
        assertThat(listProductAdvertisingFresh.get(0)).isEqualTo(mockProductListFresh.get(0));
    }

    @Test
    void getAllByCategory_returnNotFoundException_whenProductAdvertisingCategoryListIsEmpty() throws NotFoundException{
        BDDMockito.when(productAdvertisingRepository.findAllByCategory(ArgumentMatchers.any(CategoryEnum.class))).thenReturn(mockProductListFreshEmpty);

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> productAdvertisingService.getAllByCategory(String.valueOf(CategoryEnum.FS)));
        assertThat(notFoundException.getMessage()).isEqualTo("Category not found");
    }

    @Test
    void registerOrder() {
    }

    @Test
    void verifyStock() {
    }
}