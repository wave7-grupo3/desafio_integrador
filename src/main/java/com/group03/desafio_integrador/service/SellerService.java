package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService implements ISellerService {

    private SellerRepository repository;
}