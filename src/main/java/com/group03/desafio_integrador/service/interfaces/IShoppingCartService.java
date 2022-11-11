package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.entities.ShoppingCart;

public interface IShoppingCartService {
    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart update(Long id);
}
