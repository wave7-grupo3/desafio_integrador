package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/manager")
public class ManagerController {

    @Autowired
    private final ManagerService managerService;

    @PostMapping("/save")
    public ResponseEntity<?> saveManager(Manager manager) {
        return new ResponseEntity<>(managerService.saveManager(manager), HttpStatus.CREATED);

    }

}
