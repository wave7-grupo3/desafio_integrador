package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {
}
