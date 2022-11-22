package com.group03.desafio_integrador.controller;

import com.group03.desafio_integrador.advisor.exceptions.NotAcceptableException;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductSellerDTO;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.service.interfaces.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    public ISellerService sellerService;

    /**
     * Rota responsável por retornar todos os vendedores.
     * @author Mariana Saraiva
     * @return Retorna uma lista de vendedores do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping
    public ResponseEntity<List<Seller>> getAll() {
        return new ResponseEntity<>(sellerService.getAll(), HttpStatus.OK);
    }

    /**
     * Rota responsável por retornar o vendedor de acordo com o Id.
     * @author Mariana Saraiva
     * @param id - Long
     * @return Retorna o vendedor do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getById(@PathVariable Long id) {
        return new ResponseEntity<>(sellerService.getById(id), HttpStatus.OK);
    }

    /**
     * Rota responsável por salvar/inserir um novo vendedor.
     * @author Mariana Saraiva
     * @param seller - Seller
     * @return Retorna o novo vendedor do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    @PostMapping
    public ResponseEntity<Seller> save(@Valid @RequestBody Seller seller) {
        return new ResponseEntity<>(sellerService.save(seller), HttpStatus.CREATED);
    }

    /**
     * Rota responsável por atualizar um  vendedor cadastrado.
     * @author Mariana Saraiva
     * @param seller - Seller
     * @return Retorna o vendedor do tipo Seller.
     */
    @PutMapping
    public ResponseEntity<Seller> update(@Valid @RequestBody Seller seller) {
        return new ResponseEntity<>(sellerService.update(seller), HttpStatus.CREATED);
    }

    /**
     * Rota responsável por deletar o vendedor de acordo com o Id.
     * @author Mariana Saraiva
     * @param id - Long
     * @throws NotFoundException - NotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        sellerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Rota responsável por ordenar os dados de acordo com a data de expiração do produto ou quantidade de produtos mais vendidos.
     * @author Mariana Saraiva
     * @param productId - Long
     * @param orderBy - String
     * @return Retorna uma lista do tipo ProductSellerDTO.
     * @throws NotAcceptableException - NotAcceptableException
     */
    @GetMapping(value = "/list", params = {"productId", "orderBy"})
    public ResponseEntity<List<ProductSellerDTO>> filterProductPerSeller(
            @RequestParam("productId") Long productId,
            @RequestParam("orderBy") String orderBy
    ) {
        return new ResponseEntity<>(sellerService.filterProductsPerSeller(productId, orderBy), HttpStatus.OK);
    }

    /**
     * Rota responsável por ordenar os vendedores de acordo a pontuação de vendas
     * @author Mariana Saraiva
     * @param order - String
     * @return Retorna um dto do tipo ProductSellerDTO.
     * @throws NotFoundException - NotFoundException
     * @throws  NotAcceptableException - NotAcceptableException
     */
    @GetMapping(value = "/rating", params = {"order"})
    public ResponseEntity<List<ProductSellerDTO>> filterTopRankedSeller(@RequestParam("order") String order) {
        return new ResponseEntity<>(sellerService.filterTopRankedSeller(order), HttpStatus.OK);
    }

}
