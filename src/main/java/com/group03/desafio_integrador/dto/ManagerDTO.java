package com.group03.desafio_integrador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ManagerDTO {
    private Long managerId;
    private String name;
    private String username;
}
