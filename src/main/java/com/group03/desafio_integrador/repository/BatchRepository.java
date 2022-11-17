package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    /**
     * Método responsável por listar os lotes de produto conforme o Id do Produto
     * @author Amanda Zotelli
     * @param id - ProductAdvertising
     * @return Retorna uma Lista da entidade do tipo Batch.
     */
    List<Batch> findAllByProductId(ProductAdvertising id);

    /**
     * Método responsável por listar os lotes de produto que possuem data de
     * validade maior que o parâmetro informado.
     * @author Amanda Zotelli
     * @param expirationDate - LocalDate
     * @return Retorna uma Lista da entidade do tipo Batch.
     */
    List<Batch> findAllByExpirationDateGreaterThan(LocalDate expirationDate);

}
