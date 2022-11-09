package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.Section;
import com.group03.desafio_integrador.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectionService implements ISectionService {

    private final SectionRepository repository;

    @Override
    public Section getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Section not found!"));
    }
}
