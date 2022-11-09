package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    public IInboundOrderService service;

    @PostMapping
    public ResponseEntity<BatchStockDTO> save(@Valid @RequestBody InboundOrder inboundOrder) throws Exception {
        return new ResponseEntity<>(service.save(inboundOrder), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InboundOrder>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Batch> update(@Valid @RequestBody Batch batch) {
        return new ResponseEntity<>(service.update(batch), HttpStatus.CREATED);
    }

}
