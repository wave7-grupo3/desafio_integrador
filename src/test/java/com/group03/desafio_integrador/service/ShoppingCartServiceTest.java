package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.repository.ShoppingCartRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @InjectMocks
    @Spy
    private ShoppingCartService shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    private ShoppingCart mockShoppingCartOpen;
    private ShoppingCart mockShoppingCartCreate;

    private ShoppingCart mockShoppingCartFinished;

    @BeforeEach
    void setUp() {

        mockShoppingCartOpen = TestsMocks.mockShoppingCartOpen();
        mockShoppingCartCreate = TestsMocks.mockShoppingCartCreate();
        mockShoppingCartFinished = TestsMocks.mockShoppingCartFinished();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save_returnSucess_whenValidData() {
        BDDMockito.when(shoppingCartRepository.save(ArgumentMatchers.any(ShoppingCart.class)))
                .thenReturn(mockShoppingCartOpen);

        ShoppingCart newShoppingCart = shoppingCartService.save(mockShoppingCartCreate);

        assertThat(newShoppingCart).isNotNull();

    }

    @Test
    void update_returnSucess_whenValidData() {
        BDDMockito.when(shoppingCartRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.ofNullable(mockShoppingCartOpen));

        shoppingCartRepository.findById(1L);

        BDDMockito.when(shoppingCartRepository.save(ArgumentMatchers.any(ShoppingCart.class)))
                .thenReturn(mockShoppingCartFinished);

        ShoppingCart updateShoppingCart = shoppingCartService.save(mockShoppingCartFinished);

        assertThat(updateShoppingCart).isNotNull();
        assertThat(updateShoppingCart.getOrderStatus()).isEqualTo(mockShoppingCartFinished.getOrderStatus());
    }
}