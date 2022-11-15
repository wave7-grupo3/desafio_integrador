package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.dto.DispatchDTO;
import com.group03.desafio_integrador.dto.PackingOrderDTO;
import com.group03.desafio_integrador.entities.CartProduct;
import com.group03.desafio_integrador.entities.entities_enum.DispatchStatusEnum;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.repository.CartProductRepository;
import com.group03.desafio_integrador.service.interfaces.IPackingAndDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PackingAndDispatchService implements IPackingAndDispatchService {
    @Autowired
    private CartProductRepository repository;

    private List<CartProduct> cartBuyerOfProducts;
    private Integer id = 0;


    /**
     * @return
     */
    @Override
    public List<PackingOrderDTO> getAllCartProductFinished() {
        List<PackingOrderDTO> ordersFinishidDTOS = new ArrayList<>();
        cartBuyerOfProducts = repository.findAll();

        return cartBuyerOfProducts.stream().map(p -> {
            if (p.getShoppingCart().getOrderStatus().equals(OrderStatusEnum.FINALIZADO)) {
                PackingOrderDTO packing = PackingOrderDTO.builder()
                        .cart_product_id(p.getCartProductId())
                        .product_id(p.getCartProductId())
                        .order_status(String.valueOf(p.getShoppingCart().getOrderStatus()))
                        .buyer_id(p.getShoppingCart().getBuyer().getBuyerId())
                        .type(p.getProductAdvertising().getCategory())
                        .build();

                ordersFinishidDTOS.add(packing);
            }
            return ordersFinishidDTOS;
        }).collect(Collectors.toList()).get(0);
    }

    private Integer createId() {return id += 1;}

    /**
     * @return
     */
    @Override
    public Set<DispatchDTO> getAllPackingForDispatch() {
        List<PackingOrderDTO> cartProductOrderFinished = getAllCartProductFinished();
        HashSet<DispatchDTO> data = new LinkedHashSet<>();

        cartProductOrderFinished.stream().map(f -> {
            DispatchDTO dispatchDTO = DispatchDTO.builder()
                    //.id_Packing(Long.valueOf(createId()))
                    .buyer_id(f.getBuyer_id())
                    .category(f.getType())
                    .status(DispatchStatusEnum.ABERTO)
                    .build();

            data.add(dispatchDTO );

            return dispatchDTO ;
        }).collect(Collectors.toList());

        return data;
    }
}
