package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.CategoryEnum;
import com.group03.desafio_integrador.entities.Section;

public interface ISectionService {

    /**
     * Método responsável por retornar a sessão de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Section - Retorna uma entidade do tipo Section.
     */
    Section getById(Long id);

    /**
     * Método responsável por retornar a categoria da sessão.
     * @author Gabriel Morais
     * @param category - CategoryEnum
     * @return Section - Retorna uma entidade do tipo Section.
     */
    Section findByCategory(CategoryEnum category);

    /**
     * Método responsável por salvar nova sessão.
     * @author Gabriel Morais
     * @param section - Section
     * @return Section - Retorna uma entidade do tipo Section.
     */
    Section save(Section section);
}
