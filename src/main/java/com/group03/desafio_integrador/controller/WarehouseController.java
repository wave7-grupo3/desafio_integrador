package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.dto.ProductWarehouseDTO;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.service.interfaces.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductWarehouseDTO> getAllStockProductWarehouse(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(warehouseService.getAllStockProductWarehouse(id), HttpStatus.OK);
    }
}
