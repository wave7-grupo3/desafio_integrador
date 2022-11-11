package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.CartProduct;

import java.util.List;

public interface ICartProductService {

    List<CartProduct> getAll();

    CartProduct save(CartProduct cartProduct);

    List<CartProduct> saveAll(List<CartProduct> listCart);
}
