package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.dto.ProductQuantitySellerDTO;
import com.group03.desafio_integrador.entities.Seller;

import java.util.List;

public interface ISellerService {

    List<Seller> getAll();
    Seller getById(Long id);

    Seller save(Seller seller);

    Seller update(Seller seller);

    void deleteById(Long id);

    List<ProductQuantitySellerDTO>  filterproductsMoreQuantityPerSeller(Long id);
}
