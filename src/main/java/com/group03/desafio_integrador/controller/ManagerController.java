package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.advisor.exceptions.UnprocessableEntityException;
import com.group03.desafio_integrador.dto.ManagerDTO;
import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.service.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v6/fresh-products/manager")
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
    public ResponseEntity<ManagerDTO> saveManager(@RequestBody Manager manager) {
        return new ResponseEntity<>(managerService.saveManager(manager), HttpStatus.CREATED);
    }

    /**
     * Rota responsável pela atualização de um representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws NotFoundException - NotFoundException
     */
    @PutMapping("/update/all")
    public ResponseEntity<ManagerDTO> updateManager(@RequestBody Manager manager) {
        return ResponseEntity.ok(managerService.updateManager(manager));
    }

    /**
     * Rota responsável pela atualização do nome de um representante no banco de dados.
     * @author Rosalia Padoin
     * @param name - String
     * @throws NotFoundException - NotFoundException
     */
    @PatchMapping("/update/name/{id}")
    public ResponseEntity<ManagerDTO> updateNameManager(@RequestBody Manager name, @PathVariable String id) {
        return ResponseEntity.ok(managerService.updateNameManager(name.getName(), id));
    }

    /**
     * Rota responsável pela atualização do username de um representante no banco de dados.
     * @author Rosalia Padoin
     * @param username - String
     * @throws NotFoundException - NotFoundException
     */
    @PatchMapping("/update/username/{id}")
    public ResponseEntity<ManagerDTO> updateUsernameManager(@RequestBody Manager username, @PathVariable String id) {
        return ResponseEntity.ok(managerService.updateUsernameManager(username.getUsername(), id));
    }

    /**
     * Rota responsável pela atualização da senha de um representante no banco de dados.
     * @author Rosalia Padoin
     * @param password - String
     * @throws NotFoundException - NotFoundException
     */
    @PatchMapping("/update/password/{id}")
    public ResponseEntity<String> updatePasswordManager(@RequestBody Manager password, @PathVariable String id) {
        managerService.updatePasswordManager(password.getPassword(), id);
        return ResponseEntity.ok("Successfully changed password!");
    }

    /**
     * Rota responsável por listar todos os representantes cadastrados.
     * @author Rosalia Padoin
     * @return Retorna uma lista contendo entidades do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping
    public ResponseEntity<List<ManagerDTO>> getAll() {
        List<ManagerDTO> managers = new ArrayList<>();

        for (Manager manager : managerService.getAll()) {
            ManagerDTO managerDTO = ManagerDTO.builder().managerId(manager.getManagerId())
                    .name(manager.getName())
                    .username(manager.getUsername())
                    .build();

            managers.add(managerDTO);
        }

        return ResponseEntity.ok(managers);
    }

    /**
     * Rota responsável por buscar um representante conforme Id informado.
     * @author Rosalia Padoin
     * @return Retorna uma entidade do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<ManagerDTO> getManagerById(@PathVariable Long id) {
        Manager managerById = managerService.getManagerById(id);

        return ResponseEntity.ok(ManagerDTO.builder().managerId(managerById.getManagerId())
                .name(managerById.getName())
                .username(managerById.getUsername())
                .build());
    }

    /**
     * Rota responsável por excluir um representante conforme Id informado.
     * @author Rosalia Padoin
     * @throws NotFoundException - NotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManagerById(@PathVariable Long id) {
        managerService.deleteManagerById(id);
        return ResponseEntity.noContent().build();
    }
}
