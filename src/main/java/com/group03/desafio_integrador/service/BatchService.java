package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.repository.BatchRepository;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatchService {

    private final BatchRepository repository;

    @Override
    public Batch getById(Long id) throws NotFoundException {
        Optional<Batch> batch = repository.findById(id);
        return batch.orElseThrow(() -> new NotFoundException("Batch not found!"));
    }

    @Override
    public Batch save(Batch batch) {
        return repository.save(batch);
    }


}
