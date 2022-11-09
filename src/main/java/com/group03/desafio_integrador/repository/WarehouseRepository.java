package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
