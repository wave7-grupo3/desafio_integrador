package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.BatchDueDateDTO;
import com.group03.desafio_integrador.dto.ProductQuantitySellerDTO;
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
    public List<ProductQuantitySellerDTO> filterproductsMoreQuantityPerSeller(Long id) {
        ProductAdvertising productId = productService.getById(id);

        List<Seller> sellerList = getAll().stream()
                .filter(seller -> seller.getSellerId().equals(productId.getSeller().getSellerId()))
                .collect(Collectors.toList());

        List<Batch> batchList = batchService.findBatchByProductId(productId);

        List<ProductQuantitySellerDTO> productQuantitySellerDTOList = new ArrayList<>();

        for (Seller seller : sellerList) {

            for (Batch batch : batchList) {

                ProductQuantitySellerDTO productBuild = ProductQuantitySellerDTO.builder()
                        .productId(id)
                        .quantity(batch.getProductQuantity())
                        .fabrication(batch.getFabricationDate())
                        .expirationDate((batch.getExpirationDate()))
                        .customerEvaluator(seller.getCustomerEvaluator())
                        .seller(seller.getSellerName())
                        .build();


                productQuantitySellerDTOList.add(productBuild);
            }
        }

//        return productQuantitySellerDTOList.stream()
//                .sorted(Comparator.comparing(ProductQuantitySellerDTO::getExpirationDate))
//                .collect(Collectors.toList());

        return productQuantitySellerDTOList.stream()
                .sorted(Comparator.comparing(ProductQuantitySellerDTO::getQuantity).reversed())
                .collect(Collectors.toList());
    }
}
