package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.repository.CartProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartProductService implements ICartProductService {

    private final CartProductRepository repository;
    @Override
    public List<CartProduct> getAll() {
        return repository.findAll();
    }

    @Override
    public CartProduct save(CartProduct cartProduct) {
        return repository.save(cartProduct);
    }

    @Override
    public List<CartProduct> saveAll(List<CartProduct> listCart) {
        return repository.saveAll(listCart);
    }

    @Override
    public List<CartProduct> getCartProducts(Long id) {
        ShoppingCart shoppingCart = ShoppingCart
                .builder()
                .shoppingCartId(id)
                .build();

        List<CartProduct> cartProductList = repository.findAllByShoppingCart(shoppingCart);

        if (cartProductList.isEmpty()) {
            throw new NotFoundException("Shopping Cart not found!");
        }

        return cartProductList;
    }
}
