package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.PaymentCart;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentTypeEnum;
import com.group03.desafio_integrador.repository.PaymentCartRepository;
import com.group03.desafio_integrador.repository.ShoppingCartRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentScheduledServiceTest {

    @InjectMocks
    private PaymentScheduledService paymentScheduledService;

    @Mock
    private PaymentCartRepository paymentCartRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    private PaymentCart paymentCartBankSlip;


    private ShoppingCart shoppingCart;

    private String cpf;

    @BeforeEach
    void setUp() {
        cpf = "95465432176";

        paymentCartBankSlip = PaymentCart.builder()
                .id(2L)
                .barCode("899870987098709870987908709870987708")
                .paymentStatus(PaymentStatusEnum.OPEN)
                .dueDate("10/10/2022")
                .cpf(cpf)
                .paymentType(PaymentTypeEnum.BANK_SLIP)
                .paymentValue(BigDecimal.valueOf(100))
                .timestamp(LocalDateTime.now())
                .build();

        shoppingCart = ShoppingCart.builder()
                .shoppingCartId(1L)
                .buyer(TestsMocks.mockBuyer())
                .date(LocalDate.now())
                .totalCartPrice(100.0)
                .discountPayment(5.0)
                .build();

    }

    @Test
    void verifyPayments_whenExists() {
        when(paymentCartRepository.findAllByPaymentType(any()))
                .thenReturn(Collections.singletonList(paymentCartBankSlip));

        when(shoppingCartRepository.findByPaymentCartId(any()))
                .thenReturn(shoppingCart);

//        BDDMockito.doNothing().when(paymentCartRepository).save(any());
//        BDDMockito.doNothing().when(shoppingCartRepository).save(any());
//
        when(paymentCartRepository.save(any()))
                .thenReturn(paymentCartBankSlip);

        when(shoppingCartRepository.save(any()))
                .thenReturn(shoppingCart);

        assertDoesNotThrow(() -> paymentScheduledService.verifyPaymentBankSlip());


//        doNothing().when(paymentScheduledService.sendEmail(any()));
    }

}