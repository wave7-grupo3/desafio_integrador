package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.PaymentBankSlipDTO;
import com.group03.desafio_integrador.dto.PaymentCreditCardDTO;
import com.group03.desafio_integrador.dto.PaymentPixDTO;
import com.group03.desafio_integrador.entities.PaymentCart;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentTypeEnum;
import com.group03.desafio_integrador.repository.PaymentCartRepository;
import com.group03.desafio_integrador.utils.mocks.TestsMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentCartServiceTest {

    @InjectMocks
    @Spy
    private PaymentCartService paymentCartService;

    @Mock
    private PaymentCartRepository paymentCartRepository;

    @Mock
    private ShoppingCartService shoppingCartService;

    private PaymentCart paymentCartCreditCard;

    private PaymentCart paymentCartBankSlip;

    private PaymentCart paymentCartPix;

    private String cpf;
    private ShoppingCart shoppingCart;
    private PaymentCreditCardDTO paymentCartCreditCardDTO;

    private PaymentBankSlipDTO paymentBankSlipDTO;

    private PaymentPixDTO paymentPixDTO;

    @BeforeEach
    void setUp() {
        cpf = "95465432176";

        paymentCartCreditCard = PaymentCart.builder()
                .barCode("")
                .id(1L)
                .numberCard("1321431212344312")
                .paymentStatus(PaymentStatusEnum.PAID)
                .dueDate("10/10/2022")
                .cpf(cpf)
                .paymentType(PaymentTypeEnum.CREDIT_CARD)
                .paymentValue(BigDecimal.valueOf(100))
                .timestamp(LocalDateTime.now())
                .build();

        paymentCartBankSlip = PaymentCart.builder()
                .id(2L)
                .barCode("899870987098709870987908709870987708")
                .paymentStatus(PaymentStatusEnum.PAID)
                .dueDate("10/10/2022")
                .cpf(cpf)
                .paymentType(PaymentTypeEnum.BANK_SLIP)
                .paymentValue(BigDecimal.valueOf(100))
                .timestamp(LocalDateTime.now())
                .build();

        paymentCartPix = PaymentCart.builder()
                .id(3L)
                .paymentStatus(PaymentStatusEnum.PAID)
                .dueDate("10/10/2022")
                .cpf(cpf)
                .paymentType(PaymentTypeEnum.PIX)
                .copyPaste("0000bcb.gov.br")
                .paymentValue(BigDecimal.valueOf(100))
                .timestamp(LocalDateTime.now())
                .build();

        shoppingCart = ShoppingCart.builder()
                .shoppingCartId(1L)
//                .paymentCart(paymentCartCreditCard)
                .buyer(TestsMocks.mockBuyer())
                .date(LocalDate.now())
                .totalCartPrice(100.0)
                .discountPayment(5.0)
                .build();

        paymentCartCreditCardDTO = PaymentCreditCardDTO.builder()
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

    @AfterEach
    void tearDown() {
    }

    @Test
    void insertPaymentCreditCard_whenValidData() {
        when(shoppingCartService.getById(any())).thenReturn(shoppingCart);
        when(shoppingCartService.save(any())).thenReturn(shoppingCart);

        PaymentCart paymentCart = paymentCartService.insertPaymentCreditCard(paymentCartCreditCardDTO, 1L);

        assertThat(paymentCart).isNotNull();
    }

    @Test
    void insertPaymentBankSlip_whenValidData() {
        when(shoppingCartService.getById(any())).thenReturn(shoppingCart);
        when(shoppingCartService.save(any())).thenReturn(shoppingCart);

        PaymentCart paymentCart = paymentCartService.insertPaymentBankSlip(paymentBankSlipDTO, 1L);

        assertThat(paymentCart).isNotNull();
    }

    @Test
    void insertPaymentPix_whenValidData() {
        when(shoppingCartService.getById(any())).thenReturn(shoppingCart);
        when(shoppingCartService.save(any())).thenReturn(shoppingCart);

        PaymentCart paymentCart = paymentCartService.insertPaymentPix(paymentPixDTO, 1L);

        assertThat(paymentCart).isNotNull();
    }

    @Test
    void getPaymentCreditCard_returnSuccess() {
        when(paymentCartRepository.findById(any()))
                .thenReturn(Optional.ofNullable(paymentCartCreditCard));

        PaymentCreditCardDTO paymentCreditCardDTO = paymentCartService.getPaymentCreditCard(1L);

        assertThat(paymentCreditCardDTO).isNotNull();
        assertThat(paymentCreditCardDTO.getCpf()).isEqualTo(cpf);
    }

    @Test
    void getPaymentBankSlip_returnSuccess() {
        when(paymentCartRepository.findById(any()))
                .thenReturn(Optional.ofNullable(paymentCartBankSlip));

        PaymentBankSlipDTO paymentBankSlip = paymentCartService.getPaymentBankSlip(1L);

        assertThat(paymentBankSlip).isNotNull();
        assertThat(paymentBankSlip.getCpf()).isEqualTo(cpf);
    }

    @Test
    void getPaymentPix_returnSuccess() {
        when(paymentCartRepository.findById(any()))
                .thenReturn(Optional.ofNullable(paymentCartPix));

        PaymentPixDTO paymentPixDTO = paymentCartService.getPaymentPix(1L);

        assertThat(paymentPixDTO).isNotNull();
        assertThat(paymentPixDTO.getCpf()).isEqualTo(cpf);
    }

    @Test
    void getPaymentCreditCard_throwError_whenInvalidData() {
        doThrow(new NotFoundException("")).when(paymentCartService)
                .getPaymentCreditCard(any());

        assertThrows(NotFoundException.class, () -> paymentCartService.getPaymentCreditCard(1L));
    }

    @Test
    void getPaymentBankSlip_throwError_whenInvalidData() {
        doThrow(new NotFoundException("")).when(paymentCartService)
                .getPaymentBankSlip(any());

        assertThrows(NotFoundException.class, () -> paymentCartService.getPaymentBankSlip(1L));
    }

    @Test
    void getPaymentPix_throwError_whenInvalidData() {
        doThrow(new NotFoundException("")).when(paymentCartService)
                .getPaymentPix(any());

        assertThrows(NotFoundException.class, () -> paymentCartService.getPaymentPix(1L));
    }


}