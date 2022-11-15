package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.dto.BatchDueDateDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByProductId(ProductAdvertising id);
    List<Batch> findAllByExpirationDateGreaterThan(LocalDate expirationDate);

}
