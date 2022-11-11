package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.repository.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatchService {

    private final BatchRepository repository;

    /**
     * Método responsável por retornar o lote de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Batch - Retorna uma entidade do tipo Batch.
     * @throws NotFoundException
     */
    @Override
    public Batch getById(Long id) throws NotFoundException {
        Optional<Batch> batch = repository.findById(id);
        return batch.orElseThrow(() -> new NotFoundException("Batch not found!"));
    }

    /**
     * Método responsável por salvar um novo lote.
     * @author Gabriel Morais
     * @param batch - Batch
     * @return Batch - Retorna uma entidade do tipo Batch.
     */
    @Override
    public Batch save(Batch batch) {
        return repository.save(batch);
    }

    @Override
    public Batch findBatchByProductId(ProductAdvertising id) {
        return repository.findByProductId(id);
    }
}
