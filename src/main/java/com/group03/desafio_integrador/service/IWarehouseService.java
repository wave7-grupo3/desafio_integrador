package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Warehouse;

public interface IWarehouseService {
    Warehouse getById(Long id) throws NotFoundException;
}
