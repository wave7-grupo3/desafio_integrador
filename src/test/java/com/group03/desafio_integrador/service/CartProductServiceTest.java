package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.repository.CartProductRepository;
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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CartProductServiceTest {

    @InjectMocks
    private CartProductService cartProductService;

    @Mock
    private CartProductRepository cartProductRepository;

    private List<CartProduct> mockCartProductList;

    private CartProduct mockCartProductCreate;

    @BeforeEach
    void setUp() {
        mockCartProductList = TestsMocks.mockCartProductOrderList();
        mockCartProductCreate = TestsMocks.mockCartProductCreate();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll_returnListShoppingCart_whenNotEmpty() {
        BDDMockito.when(cartProductRepository.findAll())
                .thenReturn(mockCartProductList);

        List<CartProduct> cartProductList = cartProductService.getAll();

        assertThat(cartProductList).isNotNull();
        assertThat(cartProductList).isNotEmpty();
        assertThat(cartProductList.get(0)).isEqualTo(mockCartProductList.get(0));

    }

    @Test
    void save_returnSucess_whenValidData() {
        BDDMockito.when(cartProductRepository.save(ArgumentMatchers.any(CartProduct.class)))
                .thenReturn(mockCartProductList.get(0));

        CartProduct newCartProduct = cartProductService.save(mockCartProductCreate);

        assertThat(newCartProduct).isNotNull();
    }

    @Test
    void saveAll() {
    }

    @Test
    void getCartProducts() {
    }
}