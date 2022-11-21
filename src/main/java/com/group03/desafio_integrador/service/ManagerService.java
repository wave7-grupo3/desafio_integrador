package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.UnprocessableEntityException;
import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.repository.ManagerRepository;
import com.group03.desafio_integrador.service.interfaces.IManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

    @Override
    public void saveManager(Manager manager) {
        Manager managerName = managerRepository.findByName(manager.getName());
        if (managerName != null) {
            throw new UnprocessableEntityException("Manager already exists");
        }
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        managerRepository.save(manager);
    }
}
