package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.ProductAdvertising;

public interface IProductAdvertisingService {
    ProductAdvertising getById(Long id) throws NotFoundException;
}
