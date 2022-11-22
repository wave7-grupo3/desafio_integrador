package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.advisor.exceptions.NotAcceptableException;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductSellerDTO;
import com.group03.desafio_integrador.entities.Seller;

import java.util.List;

public interface ISellerService {

    /**
     * Método responsável por retornar o todos os vendedores.
     * @author Mariana Saraiva
     * @return Retorna uma lista de vendedores do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    List<Seller> getAll();

    /**
     * Método responsável por retornar o vendedor de acordo com o Id infomado.
     * @author Mariana Saraiva
     * @param id - Long
     * @return Retorna o vendedor do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    Seller getById(Long id);

    /**
     * Método responsável por salvar um novo vendedor.
     * @author Mariana Saraiva
     * @param seller - Seller
     * @return Retorna o novo vendedor do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    Seller save(Seller seller);

    /**
     * Método responsável por atualizar o vendedor de acordo com o Id.
     * @author Mariana Saraiva
     * @param seller - Seller
     * @return Retorna o vendedor do tipo Seller.
     */
    Seller update(Seller seller);

    /**
     * Método responsável por deletar o vendedor de acordo com o Id.
     * @author Mariana Saraiva
     * @param id - Long
     * @throws NotFoundException - NotFoundException
     */
    void deleteById(Long id);

    /**
     * Método responsável ordenar os dados de acordo com a data de expiração do produto ou quantidade de produtos mais vendidos.
     * @author Mariana Saraiva
     * @param id - Long
     * @param orderBy - String
     * @return Retorna uma lista do tipo ProductSellerDTO.
     * @throws NotFoundException - NotFoundException
     */
    List<ProductSellerDTO>  filterProductsPerSeller(Long id, String orderBy);

    /**
     * Método responsável por ordenar os vendedores de acordo a pontuação de vendas
     * @author Mariana Saraiva
     * @param order - String
     * @return Retorna um dto do tipo ProductSellerDTO.
     * @throws NotFoundException - NotFoundException
     * @throws NotAcceptableException - NotAcceptableException
     */
    List<ProductSellerDTO> filterTopRankedSeller(String order);
}
