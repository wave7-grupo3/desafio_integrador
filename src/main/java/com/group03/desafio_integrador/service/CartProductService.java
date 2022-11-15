package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.repository.CartProductRepository;
import com.group03.desafio_integrador.service.interfaces.ICartProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartProductService implements ICartProductService {

    private final CartProductRepository repository;

    /**
     * Método responsável por listar todos as ordens de compra do sistema.
     * @author Amanda Zotelli
     * @return List<CartProduct> - Retorna uma lista da entidade do tipo CartProduct.
     */
    @Override
    public List<CartProduct> getAll() {
        return repository.findAll();
    }

    /**
     * Método responsável por salvar uma nova ordem de compra.
     * @author Amanda Zotelli
     * @param cartProduct - CartProduct
     * @return CartProduct - Retorna uma entidade do tipo BatchStockDTO.
     */
    @Override
    public CartProduct save(CartProduct cartProduct) {
        return repository.save(cartProduct);
    }

    /**
     * Método responsável por salvar uma lista de ordens de compra.
     * @author Amanda Zotelli
     * @param listCart - List<CartProduct>
     * @return List<CartProduct>  - Retorna uma lista da entidade do tipo CartProduct.
     */
    @Override
    public List<CartProduct> saveAll(List<CartProduct> listCart) {
        return repository.saveAll(listCart);
    }

    /**
     * Método responsável por listar as ordens de compra de acordo com o Id.
     * @author Amanda Zotelli
     * @param id - Long
     * @return List<CartProduct>  - Retorna uma lista da entidade do tipo CartProduct.
     * @throws NotFoundException
     */
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
