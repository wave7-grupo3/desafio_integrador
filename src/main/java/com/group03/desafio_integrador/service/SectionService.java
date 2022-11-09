package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectionService implements ISectionService {

    public SectionRepository repository;
}
