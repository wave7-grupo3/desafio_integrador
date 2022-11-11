package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.Buyer;
import com.group03.desafio_integrador.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyerService implements IBuyerService {

    private final BuyerRepository repository;

    @Override
    public Buyer getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Buyer not found!"));
    }

}