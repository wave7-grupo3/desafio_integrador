package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.dto.PackingOrderDTO;
import com.group03.desafio_integrador.service.PackingAndDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class PackingAndDispatchController {
    @Autowired
    public PackingAndDispatchService packingAndDispatchService;

    @GetMapping("/packing")
    public ResponseEntity<List<PackingOrderDTO>> getAllCartProductFinished() {
        return new ResponseEntity<>(packingAndDispatchService.getAllCartProductFinished(), HttpStatus.OK);
    }

}
