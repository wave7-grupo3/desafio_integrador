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

import java.util.List;

@Component
@Slf4j
public class PaymentScheduledService {

    @Autowired
    private PaymentCartRepository paymentCartRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Scheduled(cron = "0 * * ? * *")
    public void testecron() {
        log.info("verificando pagamentos");
        List<PaymentCart> paymentsBankSlip = paymentCartRepository.findAllByPaymentType(PaymentTypeEnum.BANK_SLIP);

        if (!paymentsBankSlip.isEmpty()) {
            log.info("processando pagamentos");
            paymentsBankSlip.forEach(paymentCart -> {
                if (paymentCart.getPaymentStatus().equals(PaymentStatusEnum.OPEN)) {
                    ShoppingCart shoppingCart = shoppingCartRepository.findByPaymentCartId(paymentCart.getId());

                    shoppingCart.setOrderStatus(OrderStatusEnum.FINALIZADO);
                    paymentCart.setPaymentStatus(PaymentStatusEnum.PAID);

                    paymentCartRepository.save(paymentCart);
                    shoppingCartRepository.save(shoppingCart);
                    sendEmail(shoppingCart);
                }
            });
        }

    }

    public void sendEmail(ShoppingCart shoppingCart) {
        JavaMailService.sendMail(shoppingCart.getBuyer().getBuyerName(), shoppingCart.getBuyer().getEmail());
    }

}
