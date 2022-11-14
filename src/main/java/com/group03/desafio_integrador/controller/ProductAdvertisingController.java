package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductWarehouseStockDTO;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.ShoppingCart;
import com.group03.desafio_integrador.service.interfaces.ICartProductService;
import com.group03.desafio_integrador.service.interfaces.IInboundOrderService;
import com.group03.desafio_integrador.service.interfaces.IProductAdvertisingService;
import com.group03.desafio_integrador.service.interfaces.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductAdvertisingController {

    @Autowired
    private IProductAdvertisingService service;

    @Autowired
    private ICartProductService cartService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IInboundOrderService inboundOrderService;

    /**
     * Rota responsável por retornar todos os produtos cadastrados .
     *
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     * @author Mariana Saraiva
     */
    @GetMapping
    public ResponseEntity<List<ProductAdvertising>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    /**
     * Rota responsável por retornar todos os produtos cadastrados por categoria.
     *
     * @param category - String
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     * @author Mariana Saraiva
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProductAdvertising>> getAllByCategory(@RequestParam String category) {
        return new ResponseEntity<>(service.getAllByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<ShoppingCartTotalDTO> registerOrder(@RequestBody PurchaseOrderDTO purchase) {
        return new ResponseEntity<>(service.registerOrder(purchase), HttpStatus.CREATED);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<CartProduct>> getCartProducts(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(cartService.getCartProducts(id), HttpStatus.OK);
    }

    @PutMapping("/orders")
    public ResponseEntity<ShoppingCart> updateCartStatus(@RequestParam Long orderId) {
        return new ResponseEntity<>(shoppingCartService.update(orderId), HttpStatus.OK);
    }

    @GetMapping("/orders/{productId}")
    public ResponseEntity<ProductWarehouseStockDTO> getAllProductWarehouseStock(@RequestParam Long productId) {
        return new ResponseEntity<>(inboundOrderService.getAllProductWarehouseStock(productId), HttpStatus.OK);
    }


}
