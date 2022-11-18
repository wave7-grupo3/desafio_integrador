package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISellerRepository extends JpaRepository<Seller, Long> {
}
