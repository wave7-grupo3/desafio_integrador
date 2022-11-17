package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    /**
     * Método responsável por listar todos os carrinhos de compra conforme Id.
     * @author Amanda Zotelli
     * @param shoppingCart - ShoppingCart
     * @return Retorna uma lista da entidade do tipo CartProduct.
     */
    List<CartProduct> findAllByShoppingCart(ShoppingCart shoppingCart);

}
