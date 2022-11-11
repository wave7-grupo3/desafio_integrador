package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
