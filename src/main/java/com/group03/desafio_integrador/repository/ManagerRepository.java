package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
