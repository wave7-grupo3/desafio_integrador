package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;

public interface IBatchService {
    Batch getById(Long id) throws NotFoundException;
    Batch save(Batch batch);
}
