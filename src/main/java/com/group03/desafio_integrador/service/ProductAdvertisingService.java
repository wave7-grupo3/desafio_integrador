package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductDTO;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductAdvertisingService implements IProductAdvertisingService {

    private final ProductAdvertisingRepository repository;

    private final IShoppingCartService shoppingCartService;

    private final IBuyerService buyerService;

    private final IBatchService batchService;

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

    @Override
    public ShoppingCartTotalDTO registerOrder(PurchaseOrderDTO purchase) {

        Buyer buyer = buyerService.getById(purchase.getBuyerId());

        Set<ProductAdvertising> products = new HashSet<>();

        for(ProductDTO product : purchase.getProducts()) {
            products.add(getById(product.getProductId()));
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .productAdvertisingList(products)
                .buyer(buyer)
                .date(LocalDate.now())
                .orderStatus(OrderStatusEnum.ABERTO)
                .build();

        shoppingCartService.save(shoppingCart);

        return ShoppingCartTotalDTO.builder()
                .totalPrice(10.0)
                .build();

    }

//    public Batch verifyStock(Long id) {
//        Batch batch = batchService.findBatchByProductId(id);
//
//        if (batch == null) {
//            throw new NotFoundException("No batch found with this product!");
//        }
//    }
}
