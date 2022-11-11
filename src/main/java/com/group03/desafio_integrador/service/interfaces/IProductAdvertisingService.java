package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.ProductAdvertising;

import java.util.List;

public interface IProductAdvertisingService {

    /**
     * Método responsável por retornar o produto de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return ProductAdvertising - Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    ProductAdvertising getById(Long id) throws NotFoundException;

    /**
     * Método responsável por retornar todos os produtos cadastrados.
     * @author Mariana Saraiva
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    List<ProductAdvertising> getAll() throws NotFoundException;

    /**
     * Método responsável por retornar todos os produtos cadastrados.
     * @author Mariana Saraiva
     * @param category - String
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    List<ProductAdvertising> getAllByCategory(String category);

    ShoppingCartTotalDTO registerOrder(PurchaseOrderDTO purchase);
}
