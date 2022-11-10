package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.CategoryEnum;
import com.group03.desafio_integrador.entities.Section;
import com.group03.desafio_integrador.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectionService implements ISectionService {

    private final SectionRepository repository;

    /**
     * Método responsável por retornar a sessão de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Section - Retorna uma entidade do tipo Section.
     */
    @Override
    public Section getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Section not found!"));
    }

    /**
     * Método responsável por retornar a categoria da sessão.
     * @author Gabriel Morais
     * @param category - CategoryEnum
     * @return Section - Retorna uma entidade do tipo Section.
     */
    @Override
    public Section findByCategory(CategoryEnum category) {
        return repository.findByCategory(category);
    }

    /**
     * Método responsável por salvar nova sessão.
     * @author Gabriel Morais
     * @param section - Section
     * @return Section - Retorna uma entidade do tipo Section.
     */
    @Override
    public Section save(Section section) {
        return repository.save(section);
    }
}
