package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.PaymentCart;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.PaymentTypeEnum;
import com.group03.desafio_integrador.repository.PaymentCartRepository;
import com.group03.desafio_integrador.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PaymentScheduledService {

    @Autowired
    private PaymentCartRepository paymentCartRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**
     * Rotina para processamento de pagamentos feitos através de boleto bancário
     */
    @Scheduled(cron = "0 * * ? * *")
    public void verifyPaymentBankSlip() {
        log.info("verificando pagamentos");
        List<PaymentCart> paymentsBankSlip = paymentCartRepository.findAllByPaymentType(PaymentTypeEnum.BANK_SLIP);
        List<PaymentCart> paymentCartList = new ArrayList<>();

        if (!paymentsBankSlip.isEmpty()) {
            log.info("processando pagamentos");
            paymentsBankSlip.forEach(paymentCart -> {
                if (paymentCart.getPaymentStatus().equals(PaymentStatusEnum.OPEN)) {
                    ShoppingCart shoppingCart = shoppingCartRepository.findByPaymentCartId(paymentCart.getId());

                    paymentCart.setPaymentStatus(PaymentStatusEnum.PAID);
                    shoppingCart.setOrderStatus(OrderStatusEnum.FINALIZADO);
                    shoppingCart.setPaymentCart(paymentCart);

                    shoppingCartRepository.save(shoppingCart);
                    sendEmail(shoppingCart);
                }
            });
        }

    }

    /**
     * Método responsável por notificar pagamento
     * @param shoppingCart
     */
    public void sendEmail(ShoppingCart shoppingCart) {
        JavaMailService.sendMail(shoppingCart.getBuyer().getBuyerName(), shoppingCart.getBuyer().getEmail());
    }

}
