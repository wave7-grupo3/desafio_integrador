package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAdvertisingService implements IProductAdvertisingService {

    private ProductAdvertisingRepository repository;
}
