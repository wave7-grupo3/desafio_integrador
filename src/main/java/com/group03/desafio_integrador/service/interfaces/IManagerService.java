package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.advisor.exceptions.UnprocessableEntityException;
import com.group03.desafio_integrador.entities.Manager;

import java.util.List;

public interface IManagerService {
    /**
     * Método responsável pela inclusão de um novo representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws UnprocessableEntityException - UnprocessableEntityException
     */
    void saveManager(Manager manager);

    /**
     * Método responsável pela atualização de um representante no banco de dados.
     * @author Rosalia Padoin
     * @param manager - Manager
     * @throws NotFoundException - NotFoundException
     */
    Manager updateManager(Manager manager);

    /**
     * Método responsável por listar todos os representantes cadastrados.
     * @author Rosalia Padoin
     * @return Retorna uma lista contendo entidades do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    List<Manager> getAll();

    /**
     * Método responsável por buscar um representante conforme Id informado.
     * @author Rosalia Padoin
     * @return Retorna uma entidade do tipo Manager.
     * @throws NotFoundException - NotFoundException
     */
    Manager getManagerById(Long id);

    /**
     * Método responsável por excluir um representante conforme Id informado.
     * @author Rosalia Padoin
     * @throws NotFoundException - NotFoundException
     */
    void deleteManager(Long id);
}
