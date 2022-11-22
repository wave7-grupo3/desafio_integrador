package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductSellerDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Seller;
import com.group03.desafio_integrador.repository.SellerRepository;
import com.group03.desafio_integrador.service.interfaces.ISellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService implements ISellerService {

    private final SellerRepository repository;

    private final BatchService batchService;

    private final ProductAdvertisingService productService;

    @Override
    public List<Seller> getAll() {
        List<Seller> sellerList = repository.findAll();

        if (sellerList.isEmpty()) {
            throw new NotFoundException("No registered seller found!");
        }

        return sellerList;
    }

    @Override
    public Seller getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seller not found!"));
    }

    @Override
    public Seller save(Seller seller) {
        if (seller == null) {
            throw new NotFoundException("Seller cannot be null!");
        }

        return repository.save(seller);
    }

    @Override
    public Seller update(Seller seller) {
        this.getById(seller.getSellerId());

        return repository.save(seller);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);

        repository.deleteById(id);
    }

    @Override
    public List<ProductSellerDTO> filterProductsPerSeller(Long id, String orderBy) {
        if (orderBy.isEmpty()) {
            throw new NotFoundException("Param invalid");
        }

        List<ProductSellerDTO> productQuantitySellerDTOList = getSellerDTO(id);

        if (orderBy.equalsIgnoreCase("QT")) {
            return productQuantitySellerDTOList.stream()
                    .sorted(Comparator.comparing(ProductSellerDTO::getQuantity).reversed())
                    .collect(Collectors.toList());
        } else if (orderBy.equalsIgnoreCase("ED")){
            return productQuantitySellerDTOList.stream()
                    .sorted(Comparator.comparing(ProductSellerDTO::getExpirationDate))
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("Param not found!");
        }

    }


    public List<ProductSellerDTO> getSellerDTO(Long id) {
        ProductAdvertising productId = productService.getById(id);

        List<Seller> sellerList = getAll().stream()
                .filter(seller -> seller.getSellerId().equals(productId.getSeller().getSellerId()))
                .collect(Collectors.toList());

        List<Batch> batchList = batchService.findBatchByProductId(productId);

        List<ProductSellerDTO> productQuantitySellerDTOList = new ArrayList<>();

        for (Seller seller : sellerList) {

            for (Batch batch : batchList) {
                ProductSellerDTO productBuild = buildSellerDTO(id, seller, batch);
                productQuantitySellerDTOList.add(productBuild);
            }
        }

        if (productQuantitySellerDTOList.isEmpty()) throw new NotFoundException("There is no such product registered for this seller!");

        return productQuantitySellerDTOList;
    }

    private static ProductSellerDTO buildSellerDTO(Long id, Seller seller, Batch batch) {
        return ProductSellerDTO.builder()
                .productId(id)
                .quantity(batch.getProductQuantity())
                .fabrication(batch.getFabricationDate())
                .expirationDate((batch.getExpirationDate()))
                .sellerRating(seller.getSellerRating())
                .seller(seller.getSellerName())
                .build();
    }
}

