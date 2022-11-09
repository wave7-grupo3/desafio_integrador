package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.CategoryEnum;
import com.group03.desafio_integrador.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findByCategory(CategoryEnum category);
}
