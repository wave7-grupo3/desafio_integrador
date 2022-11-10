package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.ProductAdvertising;

public interface IProductAdvertisingService {

    /**
     * Método responsável por retornar o produto de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return ProductAdvertising - Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    ProductAdvertising getById(Long id) throws NotFoundException;
}
