package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.entities.CartProduct;

import java.util.List;

public interface ICartProductService {

    /**
     * Método responsável por listar todos as ordens de compra do sistema.
     * @author Amanda Zotelli
     * @return List<CartProduct> - Retorna uma lista da entidade do tipo CartProduct.
     */
    List<CartProduct> getAll();

    /**
     * Método responsável por salvar uma nova ordem de compra.
     * @author Amanda Zotelli
     * @param cartProduct - CartProduct
     * @return CartProduct - Retorna uma entidade do tipo CartProduct.
     */
    CartProduct save(CartProduct cartProduct);

    /**
     * Método responsável por salvar uma lista de ordens de compra.
     * @author Amanda Zotelli
     * @param listCart - List<CartProduct>
     * @return List<CartProduct>  - Retorna uma lista da entidade do tipo CartProduct.
     */
    List<CartProduct> saveAll(List<CartProduct> listCart);

    /**
     * Método responsável por listar as ordens de compra de acordo com o Id.
     * @author Amanda Zotelli
     * @param id - Long
     * @return List<CartProduct>  - Retorna uma lista da entidade do tipo CartProduct.
     * @throws NotFoundException
     */
    List<CartProduct> getCartProducts(Long id);
}
