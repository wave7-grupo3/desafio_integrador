package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;

import java.util.List;

public interface IBatchService {

    /**
     * Método responsável por retornar o lote de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Batch - Retorna uma entidade do tipo Batch.
     * @throws NotFoundException
     */
    Batch getById(Long id) throws NotFoundException;

    /**
     * Método responsável por salvar um novo lote.
     * @author Gabriel Morais
     * @param batch - Batch
     * @return Batch - Retorna uma entidade do tipo Batch.
     */
    Batch save(Batch batch);

    List<Batch> findBatchByProductId(ProductAdvertising id);

}
