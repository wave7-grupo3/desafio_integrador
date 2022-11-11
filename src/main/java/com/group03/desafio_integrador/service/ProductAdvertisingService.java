package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.ValidationErrorDetail;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductDTO;
import com.group03.desafio_integrador.dto.PurchaseOrderDTO;
import com.group03.desafio_integrador.dto.ShoppingCartTotalDTO;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.entities.entities_enum.CategoryEnum;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import com.group03.desafio_integrador.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductAdvertisingService implements IProductAdvertisingService {
    private final ProductAdvertisingRepository repository;
    private final IShoppingCartService shoppingCartService;
    private final IBuyerService buyerService;
    private final IBatchService batchService;
    private final ICartProductService cartService;

    /**
     * Método responsável por retornar o produto de acordo com o Id informado.
     *
     * @param id - Long
     * @return ProductAdvertising - Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     * @author Gabriel Morais
     */
    @Override
    public ProductAdvertising getById(Long id) throws NotFoundException {
        Optional<ProductAdvertising> product = repository.findById(id);
        return product.orElseThrow(() -> new NotFoundException("Product not found!"));
    }

    /**
     * Método responsável por retornar todos os produtos cadastrados.
     *
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     * @author Mariana Saraiva
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
     *
     * @param category - String
     * @return List<ProductAdvertising>- Retorna uma entidade do tipo ProductAdvertising.
     * @throws NotFoundException
     * @author Mariana Saraiva
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
        verifyStock(purchase);
        
        Buyer buyer = buyerService.getById(purchase.getBuyerId());
        
        Set<ProductAdvertising> products = new HashSet<>();
        
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (ProductDTO product : purchase.getProducts()) {
            ProductAdvertising productAdvertising = getById(product.getProductId());
            productAdvertising.setQuantity(product.getQuantity());
            products.add(getById(product.getProductId()));
            BigDecimal productPrice = productAdvertising.getProductPrice().multiply(new BigDecimal(product.getQuantity()));
            totalPrice = totalPrice.add(productPrice);
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .buyer(buyer)
                .date(LocalDate.now())
                .orderStatus(OrderStatusEnum.ABERTO)
                .totalCartPrice(Double.valueOf(String.valueOf(totalPrice)))
                .build();

        saveShoppingCart(products, shoppingCart);

        return ShoppingCartTotalDTO.builder()
                .totalPrice(Double.valueOf(String.valueOf(totalPrice)))
                .build();
    }

    // Associa o produto ao carrinho
    private void saveShoppingCart(Set<ProductAdvertising> products, ShoppingCart shoppingCart) {
        ShoppingCart cartSaved = shoppingCartService.save(shoppingCart);
        List<CartProduct> cartProductList = new ArrayList<>();

        for (ProductAdvertising product : products) {
            CartProduct cartProduct = CartProduct.builder()
                    .productAdvertising(product)
                    .shoppingCart(cartSaved)
                    .quantity(product.getQuantity())
                    .build();

            cartProductList.add(cartProduct);
        }
        cartService.saveAll(cartProductList);
    }

    public void verifyStock(PurchaseOrderDTO purchase) {
        List<ValidationErrorDetail> errorDetails = new ArrayList<>();

        for (ProductDTO product : purchase.getProducts()) {
            ProductAdvertising productId = ProductAdvertising.builder().productId(product.getProductId()).build();
            Batch batch = batchService.findBatchByProductId(productId);

            if (batch == null) throw new NotFoundException("No batch found with this product!");

            Long idProduct = batch.getProductId().getProductId();
            verifyProductExists(errorDetails, idProduct);
            verifyProductStockQuantity(errorDetails, product, batch, idProduct);
            verifyProductExpirationDate(errorDetails, batch, idProduct);

            if (!errorDetails.isEmpty()) throw new NotFoundException("Products not found", errorDetails);
            
            batchService.save(batch);
        }
    }

    private static void verifyProductExpirationDate(List<ValidationErrorDetail> errorDetails, Batch batch, Long idProduct) {
        if (batch.getExpirationDate().isBefore(LocalDate.now().plusWeeks(3))) {
            errorDetails.add(
                    ValidationErrorDetail.builder()
                            .field("expirationDate").message("Product " + idProduct + " with validation date expired!")
                            .build());
        }
    }

    private static void verifyProductStockQuantity(List<ValidationErrorDetail> errorDetails, ProductDTO product, Batch batch, Long idProduct) {
        if (batch.getProductQuantity() >= product.getQuantity()) {
            Integer updatedQuantity = batch.getProductQuantity() - product.getQuantity();
            batch.setProductQuantity(updatedQuantity);
        } else {
            errorDetails.add(
                    ValidationErrorDetail.builder()
                            .field("productQuantity").message("Product " + idProduct + " quantity not available in stock!")
                            .build());
        }
    }

    private void verifyProductExists(List<ValidationErrorDetail> errorDetails, Long idProduct) {
        try {
            getById(idProduct);
        } catch (Exception ex) {
            errorDetails.add(
                    ValidationErrorDetail.builder()
                            .field("productId").message("Product " + idProduct + " not found!")
                            .build());
        }
    }
}
