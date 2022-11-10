package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.entities.CategoryEnum;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdvertisingService implements IProductAdvertisingService {

    private final ProductAdvertisingRepository repository;

    /**
     * Método responsável por retornar o produto de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return ProductAdvertising - Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    @Override
    public ProductAdvertising getById(Long id) throws NotFoundException {
        Optional<ProductAdvertising> product = repository.findById(id);
        return product.orElseThrow(() -> new NotFoundException("Product not found!"));
    }

    /**
     * Método responsável por retornar todos os produtos cadastrados.
     * @author Mariana Saraiva
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    @Override
    public List<ProductAdvertising> getAll() throws NotFoundException {
        List<ProductAdvertising> productAdvertisingList = repository.findAll();
        if (productAdvertisingList.isEmpty()) {
            throw new NotFoundException("Product Advertising not found");
        }

        return productAdvertisingList;
    }

    /**
     * Método responsável por retornar todos os produtos cadastrados por categoria.
     * @author Mariana Saraiva
     * @param category - String
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     */
    @Override
    public List<ProductAdvertising> getAllByCategory(String category) {
        CategoryEnum enumCategory = CategoryEnum.toEnum(category);
        List<ProductAdvertising> productAdvertisingList = repository.findAllByCategory(enumCategory);

        if (productAdvertisingList.isEmpty()) {
            throw new NotFoundException("Category not found");
        }

        return productAdvertisingList;
    }
}
