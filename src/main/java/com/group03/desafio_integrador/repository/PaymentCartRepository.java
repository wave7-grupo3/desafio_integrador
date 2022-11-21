package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.PaymentCart;
import com.group03.desafio_integrador.entities.entities_enum.PaymentTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentCartRepository extends JpaRepository<PaymentCart, Long> {
    List<PaymentCart> findAllByPaymentType(PaymentTypeEnum paymentType);
}
