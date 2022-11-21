package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.advisor.exceptions.UnprocessableEntityException;
import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.service.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/manager")
public class ManagerController {

    @Autowired
    private IManagerService managerService;

    /**
     * Rota responsável pela inclusão de um novo representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws UnprocessableEntityException - UnprocessableEntityException
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveManager(@RequestBody Manager manager) {
        managerService.saveManager(manager);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Rota responsável pela atualização de um representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws NotFoundException - NotFoundException
     */
    @PutMapping("/update")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) {
        return ResponseEntity.ok(managerService.updateManager(manager));
    }

    /**
     * Rota responsável por listar todos os representantes cadastrados.
     * @author Rosalia Padoin
     * @return Retorna uma lista contendo entidades do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping
    public ResponseEntity<List<Manager>> getAll() {
        return ResponseEntity.ok(managerService.getAll());
    }

    /**
     * Rota responsável por buscar um representante conforme Id informado.
     * @author Rosalia Padoin
     * @return Retorna uma entidade do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.getManagerById(id));
    }

    /**
     * Rota responsável por excluir um representante conforme Id informado.
     * @author Rosalia Padoin
     * @throws NotFoundException - NotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.ok().build();
    }

}
