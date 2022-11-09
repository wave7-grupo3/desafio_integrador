package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
