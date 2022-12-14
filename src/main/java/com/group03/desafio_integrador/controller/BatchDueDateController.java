package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.BatchDueDateStockDTO;
import com.group03.desafio_integrador.service.interfaces.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class BatchDueDateController {

    @Autowired
    private IBatchService batchService;

    /**
     * Rota responsável por retornar os lotes armazenados em um setor de um armazém ordenados por sua data de vencimento.
     * @author Gabriel Morais, Mariana Saraiva, Carolina Hakamada
     * @param numberOfDays - Integer
     * @param section - String
     * @return Retorna uma entidade do tipo BatchDueDateStockDTO.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping
    public ResponseEntity<BatchDueDateStockDTO> getAllDueDate(@RequestParam Integer numberOfDays,
                                                              @RequestParam String section){
        return new ResponseEntity<>(batchService.getAllDueDate(numberOfDays, section), HttpStatus.OK);
    }

    /**
     * Rota responsável por retornar os lotes dentro de uma data de validade e pertençam a uma categoria de produto
     * ordenados de forma crescente.
     * @author Gabriel Morais, Mariana Saraiva, Carolina Hakamada
     * @param numberOfDays - Integer
     * @param category - String
     * @param sorting - String
     * @return Retorna uma entidade do tipo BatchDueDateStockDTO.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping("/list")
    public ResponseEntity<BatchDueDateStockDTO> getAllDueDateCategory(@RequestParam Integer numberOfDays,
                                                                      @RequestParam String category,
                                                                      @RequestParam String sorting){
        return new ResponseEntity<>(batchService.getAllDueDateCategory(numberOfDays, category, sorting), HttpStatus.OK);
    }

}
