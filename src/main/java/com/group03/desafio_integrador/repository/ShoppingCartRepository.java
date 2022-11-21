package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByPaymentCartId(Long id);
}
