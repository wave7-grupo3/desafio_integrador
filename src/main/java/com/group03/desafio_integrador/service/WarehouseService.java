package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.entities.Warehouse;
import com.group03.desafio_integrador.repository.WarehouseRepository;
import com.group03.desafio_integrador.service.interfaces.IWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouseService {

    private final WarehouseRepository repository;

    /**
     * Método responsável por retornar o armazem de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Warehouse - Retorna uma entidade do tipo Warehouse.
     * @throws NotFoundException
     */
    @Override
    public Warehouse getById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Warehouse not found!"));
    }
}
