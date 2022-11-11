package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.repository.ManagerRepository;
import com.group03.desafio_integrador.service.interfaces.IManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService implements IManagerService {

    private ManagerRepository repository;
}
