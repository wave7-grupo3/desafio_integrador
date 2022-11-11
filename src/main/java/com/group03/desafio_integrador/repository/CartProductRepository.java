package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}
