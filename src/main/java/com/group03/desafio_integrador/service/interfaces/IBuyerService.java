package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.entities.Buyer;

public interface IBuyerService {
    /**
     * Método responsável por retornar o comprador de acordo com o Id informado.
     * @author Amanda Zotelli
     * @param id - Long
     * @return Buyer - Retorna uma entidade do tipo Buyer.
     */
    Buyer getById(Long id);
}
