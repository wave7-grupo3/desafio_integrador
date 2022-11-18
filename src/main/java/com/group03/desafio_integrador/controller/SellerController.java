package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.dto.ProductQuantitySellerDTO;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.service.interfaces.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    public ISellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> getAll() {
        return new ResponseEntity<>(sellerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getById(@PathVariable Long id) {
        return new ResponseEntity<>(sellerService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> save(@Valid @RequestBody Seller seller) {
        return new ResponseEntity<>(sellerService.save(seller), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> update(@Valid @RequestBody Seller seller) {
        return new ResponseEntity<>(sellerService.update(seller), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        sellerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/list", params = {"productId"})
    public ResponseEntity<List<ProductQuantitySellerDTO>> filterproductsMoreQuantityPerSeller(@RequestParam("productId") Long productId) {
        return new ResponseEntity<>(sellerService.filterproductsMoreQuantityPerSeller(productId), HttpStatus.OK);
    }
}
