package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductWarehouseDTO;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.entities.Warehouse;

import java.util.List;

public interface IWarehouseService {

    /**
     * Método responsável por retornar o armazém conforme Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Retorna uma entidade do tipo Warehouse.
     * @throws NotFoundException - NotFoundException
     */
    Warehouse getById(Long id) throws NotFoundException;

    List<Warehouse> getAll();

    ProductWarehouseDTO getAllStockProductWarehouse(Long id);
}
