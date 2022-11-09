package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    public IInboundOrderService service;

    @PostMapping
    public ResponseEntity<BatchStockDTO> save(@RequestBody InboundOrder inboundOrder) throws Exception {
        return new ResponseEntity<>(service.save(inboundOrder), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InboundOrder>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
