package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    public IInboundOrderService service;

    @PostMapping
    public ResponseEntity<InboundOrder> save(@RequestBody InboundOrder inboundOrder) {
        return new ResponseEntity<>(service.save(inboundOrder), HttpStatus.OK);
    }
}
