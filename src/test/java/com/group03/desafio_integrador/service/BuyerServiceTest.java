package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.Buyer;
import com.group03.desafio_integrador.repository.BuyerRepository;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BuyerServiceTest {

    @InjectMocks
    private BuyerService buyerService;

    @Mock
    private BuyerRepository buyerRepository;

    private Buyer mockBuyer;

    @BeforeEach
    void setUp() {
       mockBuyer = TestsMocks.mockBuyer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById_returnSuccess_whenBatchExists() {
        BDDMockito.when(buyerRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.ofNullable(mockBuyer));

        Buyer buyerById = buyerService.getById(1L);

        assertThat(buyerById).isNotNull();
        assertThat(buyerById).isEqualTo(mockBuyer);
    }
}