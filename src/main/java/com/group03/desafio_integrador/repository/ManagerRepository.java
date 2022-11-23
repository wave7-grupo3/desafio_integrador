package com.group03.desafio_integrador.repository;

import com.group03.desafio_integrador.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByName(String name);
    Manager findByUsername(String username);
}
