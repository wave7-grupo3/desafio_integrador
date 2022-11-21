package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.advisor.exceptions.UnprocessableEntityException;
import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.repository.ManagerRepository;
import com.group03.desafio_integrador.service.interfaces.IManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerService implements IManagerService, UserDetailsService {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager managerByUsername =  managerRepository.findByUsername(username);

        if (managerByUsername == null) {
            throw new UsernameNotFoundException("Manager not found in database!");
        }

        return new org.springframework.security.core.userdetails
                .User(managerByUsername.getUsername(), managerByUsername.getPassword(), new ArrayList<>());
    }

    /**
     * Método responsável pela inclusão de um novo representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws UnprocessableEntityException - UnprocessableEntityException
     */
    @Override
    public void saveManager(Manager manager) {
        Manager managerName = managerRepository.findByName(manager.getName());
        if (managerName != null) {
            throw new UnprocessableEntityException("Manager already exists");
        }
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        managerRepository.save(manager);
    }

    /**
     * Método responsável pela atualização de um representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public Manager updateManager(Manager manager) {
        managerRepository.findById(manager.getManagerId())
                .orElseThrow(() -> new NotFoundException("Manager not found!"));

        return managerRepository.save(manager);
    }

    /**
     * Método responsável por listar todos os representantes cadastrados.
     * @author Rosalia Padoin
     * @return Retorna uma lista contendo entidades do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public List<Manager> getAll() {
        List<Manager> allManagers = managerRepository.findAll();
        if (allManagers.isEmpty()) {
            throw new NotFoundException("This search did not found any result!");
        }
        return allManagers;
    }

    /**
     * Método responsável por buscar um representante conforme Id informado.
     * @author Rosalia Padoin
     * @return Retorna uma entidade do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public Manager getManagerById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager by id " + id + " not found!"));
    }

    /**
     * Método responsável por excluir um representante conforme Id informado.
     * @author Rosalia Padoin
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public void deleteManager(Long id) {
        getManagerById(id);
        managerRepository.deleteById(id);
    }
}
