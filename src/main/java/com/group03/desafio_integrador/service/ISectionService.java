package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.CategoryEnum;
import com.group03.desafio_integrador.entities.Section;

public interface ISectionService {
    Section getById(Long id);
    Section findByCategory(CategoryEnum category);
    Section save(Section section);
}
