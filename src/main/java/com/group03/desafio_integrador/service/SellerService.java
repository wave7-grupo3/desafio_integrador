package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotAcceptableException;
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

    /**
     * Método responsável por retornar o todos os vendedores.
     * @author Mariana Saraiva
     * @return Retorna uma lista de vendedores do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public List<Seller> getAll() {
        List<Seller> sellerList = repository.findAll();

        if (sellerList.isEmpty()) {
            throw new NotFoundException("No registered seller found!");
        }

        return sellerList;
    }

    /**
     * Método responsável por retornar o vendedor de acordo com o Id infomado.
     * @author Mariana Saraiva
     * @param id - Long
     * @return Retorna o vendedor do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public Seller getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seller not found!"));
    }

    /**
     * Método responsável por salvar um novo vendedor.
     * @author Mariana Saraiva
     * @param seller - Seller
     * @return Retorna o novo vendedor do tipo Seller.
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public Seller save(Seller seller) {
        if (seller == null) {
            throw new NotFoundException("Seller cannot be null!");
        }

        return repository.save(seller);
    }

    /**
     * Método responsável por atualizar o vendedor de acordo com o Id.
     * @author Mariana Saraiva
     * @param seller - Seller
     * @return Retorna o vendedor do tipo Seller.
     */
    @Override
    public Seller update(Seller seller) {
        this.getById(seller.getSellerId());

        return repository.save(seller);
    }

    /**
     * Método responsável por deletar o vendedor de acordo com o Id.
     * @author Mariana Saraiva
     * @param id - Long
     * @throws NotFoundException - NotFoundException
     */
    @Override
    public void deleteById(Long id) {
        getById(id);

        repository.deleteById(id);
    }

    /**
     * Método responsável por ordenar os dados de acordo com a data de expiração do produto ou quantidade de produtos mais vendidos.
     * @author Mariana Saraiva
     * @param id - Long
     * @param orderBy - String
     * @return Retorna uma lista do tipo ProductSellerDTO.
     * @throws NotAcceptableException - NotAcceptableException
     */
    @Override
    public List<ProductSellerDTO> filterProductsPerSeller(Long id, String orderBy) {
        if (orderBy.isEmpty()) {
            throw new NotAcceptableException("Param invalid");
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
            throw new NotAcceptableException("Param not found!");
        }

    }

    /**
     * Método responsável por retornar todos os produtos de acordo com a data de expiração do produto ou quantidade de produtos mais vendidos, por vendedor.
     * @author Mariana Saraiva
     * @param id - Long
     * @return Retorna uma lista do tipo ProductSellerDTO.
     * @throws NotFoundException - NotFoundException
     */
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

    /**
     * Método responsável por buildar o retorno do ProductSellerDTO
     * @author Mariana Saraiva
     * @param id - Long
     * @param seller - Seller
     * @param batch - Batch
     * @return Retorna um dto do tipo ProductSellerDTO.
     * @throws NotFoundException - NotFoundException
     */
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

    /**
     * Método responsável por ordenar os vendedores de acordo a pontuação de vendas
     * @author Mariana Saraiva
     * @param order - String
     * @return Retorna um dto do tipo ProductSellerDTO.
     * @throws NotFoundException - NotFoundException
     * @throws  NotAcceptableException - NotAcceptableException
     */
    @Override
    public List<ProductSellerDTO> filterTopRankedSeller(String order) {
        List<Seller> sellerList = getAll();

        if (sellerList.isEmpty()) throw new NotFoundException("Not found seller register");
        if (order.isEmpty()) throw new NotAcceptableException("Param invalid");

        List<ProductSellerDTO> productSellerDTOList = new ArrayList<>();

        for (Seller seller : sellerList) {

            ProductSellerDTO productSellerDTO = ProductSellerDTO.builder()
                    .seller(seller.getSellerName())
                    .sellerRating(seller.getSellerRating())
                    .build();

            productSellerDTOList.add(productSellerDTO);
        }

        if ("DESC".equalsIgnoreCase(order)) {
            productSellerDTOList.stream()
                    .sorted(Comparator.comparing(ProductSellerDTO::getSellerRating).reversed())
                    .collect(Collectors.toList());
        } else {
            productSellerDTOList.stream()
                    .sorted(Comparator.comparing(ProductSellerDTO::getSellerRating))
                    .collect(Collectors.toList());
        }

        return productSellerDTOList;
    }
}
