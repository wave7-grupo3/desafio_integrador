package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.repository.SellerRepository;
import com.group03.desafio_integrador.service.interfaces.ISellerService_;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService implements ISellerService_ {

    private SellerRepository repository;
}
