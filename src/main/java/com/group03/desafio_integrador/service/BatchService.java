package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.repository.BatchRepository;
import com.group03.desafio_integrador.service.interfaces.IBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatchService {

    private final BatchRepository repository;

    /**
     * Método responsável por retornar o lote de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Retorna uma entidade do tipo Batch.
     * @throws NotFoundException - NotFoundException
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
     * @return Retorna uma entidade do tipo Batch.
     */
    @Override
    public Batch save(Batch batch) {
        return repository.save(batch);
    }

    /**
     * Método responsável por listar os lotes de produto de acordo com o Id do Produto
     * @author Amanda Zotelli
     * @param id - do tipo ProductAdvertising
     * @return Retorna uma lista de entidades do tipo Batch.
     */
    @Override
    public List<Batch> findBatchByProductId(ProductAdvertising id) {
        return repository.findAllByProductId(id);
    }
}
