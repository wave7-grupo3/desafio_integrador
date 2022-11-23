package com.group03.desafio_integrador;

import com.group03.desafio_integrador.bean.JWTBean;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.entities.entities_enum.OrderStatusEnum;
import com.group03.desafio_integrador.repository.*;
import com.group03.desafio_integrador.service.ManagerService;
import com.group03.desafio_integrador.utils.mocks.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DesafioIntegradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioIntegradorApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWTBean jwt() {
        return new JWTBean();
    }

    @Bean
    CommandLineRunner run(BatchRepository batch,
                          BuyerRepository buyer,
                          CartProductRepository cart,
                          InboundOrderRepository inboundOrder,
                          ManagerService manager,
                          ProductAdvertisingRepository product,
                          SectionRepository section,
                          SellerRepository seller,
                          ShoppingCartRepository shoppingCart,
                          WarehouseRepository warehouse) {
        return args -> {

            for (Manager newManager : ManagerMock.managerFromDatabase()) {
               manager.saveManager(newManager);
            }

            List<Manager> managers = manager.getAll();

            List<Seller> sellers = seller.saveAll(SellerMock.sellerFromDatabase());

            List<Section> sections = section.saveAll(SectionMock.sectionFromDatabase());

            List<Warehouse> warehouses = warehouse.saveAll(WarehouseMock.warehouseFromDatabase(managers));

            List<Buyer> buyers = buyer.saveAll(BuyerMock.buyerFromDatabase());

            List<ProductAdvertising> products = product.saveAll(ProductAdvertisingMock.productsAdvertisingFromDatabase(sellers));

            List<Batch> batches = batch.saveAll(BatchMock.BatchFromDatabase());

            ShoppingCart newShoppingCart = shoppingCart.save(new ShoppingCart(null,
                    LocalDate.parse("2022-11-18"),
                    OrderStatusEnum.ABERTO,
                    buyers.get(0),
                    Double.valueOf("10.0")));

            List<InboundOrder> inboundOrders = inboundOrder.saveAll(InboundOrderMock.inboundOrderFromDatabase(sections, warehouses));

            inboundOrders.get(0).getBatchList().add(batches.get(0));
            inboundOrders.get(1).getBatchList().add(batches.get(1));

            cart.save(new CartProduct(null,
                    products.get(4),
                    newShoppingCart,
                    1));
        };
    }
}
