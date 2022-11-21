package com.group03.desafio_integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesafioIntegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioIntegradorApplication.class, args);
    }

}
