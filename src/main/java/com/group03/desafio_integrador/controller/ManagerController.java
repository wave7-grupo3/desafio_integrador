package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.service.ManagerService;
import com.group03.desafio_integrador.service.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/manager")
public class ManagerController {

    @Autowired
    private IManagerService managerService;

    /**
     * Rota responsável pela inclusão de um novo representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveManager(@RequestBody Manager manager) {
        managerService.saveManager(manager);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
