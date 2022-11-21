package com.group03.desafio_integrador;

import com.group03.desafio_integrador.bean.JWTBean;
import com.group03.desafio_integrador.entities.Manager;
import com.group03.desafio_integrador.entities.Warehouse;
import com.group03.desafio_integrador.repository.WarehouseRepository;
import com.group03.desafio_integrador.service.ManagerService;
import com.group03.desafio_integrador.service.WarehouseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DesafioIntegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioIntegradorApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWTBean jwt() {
        return new JWTBean();
    }

    @Bean
    CommandLineRunner run(ManagerService managerService) { // WarehouseRepository warehouseRepository
        return args -> {
            managerService.saveManager(new Manager(null,
                    "John Travolta",
                    "john",
                    "12345"));

            managerService.saveManager(new Manager(null,
                    "Tom Hanks",
                    "tom",
                    "12345"));

            managerService.saveManager(new Manager(null,
                    "Jason Momoa",
                    "json",
                    "12345"));

            managerService.saveManager(new Manager(null,
                    "Orlando Blum",
                    "orlando",
                    "12345"));
        };
    }
}
