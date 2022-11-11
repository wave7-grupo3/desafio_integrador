package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.service.ICartProductService;
import com.group03.desafio_integrador.service.IProductAdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductAdvertisingController {

    @Autowired
    private IProductAdvertisingService service;

    @Autowired
    private ICartProductService cartService;

    /**
     * Rota responsável por retornar todos os produtos cadastrados .
     * @author Mariana Saraiva
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    @GetMapping
    public ResponseEntity<List<ProductAdvertising>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    /**
     * Rota responsável por retornar todos os produtos cadastrados por categoria.
     * @author Mariana Saraiva
     * @param category - String
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
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

}
