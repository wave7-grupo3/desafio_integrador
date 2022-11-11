package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.OrderStatusEnum;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartService implements IShoppingCartService {

    private final ShoppingCartRepository repository;

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    @Override
    public ShoppingCart update(Long id) {
       ShoppingCart shoppingCart = repository.findById(id).orElseThrow(() -> new NotFoundException("Shopping Cart not found!"));
       shoppingCart.setOrderStatus(OrderStatusEnum.FINALIZADO);
       return save(shoppingCart);
    }
}
