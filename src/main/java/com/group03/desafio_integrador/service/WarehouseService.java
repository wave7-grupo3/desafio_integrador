package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.ProductWarehouseDTO;
import com.group03.desafio_integrador.dto.WarehouseStockDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.entities.Warehouse;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import com.group03.desafio_integrador.repository.WarehouseRepository;
import com.group03.desafio_integrador.service.interfaces.IProductAdvertisingService;
import com.group03.desafio_integrador.service.interfaces.IWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouseService {

    private final WarehouseRepository repository;

    private final InboundOrderRepository inboundOrderRepository;

    private final IProductAdvertisingService productService;

    /**
     * Método responsável por retornar o armazem de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Warehouse - Retorna uma entidade do tipo Warehouse.
     * @throws NotFoundException
     */
    @Override
    public Warehouse getById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Warehouse not found!"));
    }

    @Override
    public List<Warehouse> getAll() {
        return repository.findAll();
    }

    @Override
    public ProductWarehouseDTO getAllWarehouseQuantityStockContainingProductId(Long id) {
        productService.getById(id);

        List<InboundOrder> inboundOrderList = inboundOrderRepository.findAll();

        ProductWarehouseDTO productWarehouseDTO = ProductWarehouseDTO.builder()
                .productId(id)
                .warehouses(new ArrayList<>())
                .build();

        for(InboundOrder inboundOrder: inboundOrderList) {
            Integer quantitySum = inboundOrder.getBatchList().stream()
                    .filter(batch -> batch.getProductId().getProductId().equals(id))
                    .map(Batch::getProductQuantity)
                    .reduce(0, Integer::sum);

            WarehouseStockDTO warehouseStockDTO = WarehouseStockDTO.builder()
                    .warehouseCode(inboundOrder.getWarehouseId().getWarehouseId())
                    .totalQuantity(quantitySum.toString())
                    .build();

            if (quantitySum > 0) {
                List<WarehouseStockDTO> warehouseStockDTOList = productWarehouseDTO.getWarehouses().stream()
                        .filter(warehouse -> warehouseStockDTO.getWarehouseCode().equals(warehouse.getWarehouseCode()))
                        .collect(Collectors.toList());

                if (warehouseStockDTOList.isEmpty()) {
                    productWarehouseDTO.getWarehouses().add(warehouseStockDTO);
                } else {
                    productWarehouseDTO.getWarehouses().forEach(warehouse -> {
                        if(warehouse.getWarehouseCode().equals(warehouseStockDTO.getWarehouseCode())) {
                            Integer sum = Integer.valueOf(warehouse.getTotalQuantity()) + quantitySum;
                            warehouse.setTotalQuantity(String.valueOf(sum));
                        }
                    });
                }
            }
        }

        if (productWarehouseDTO.getWarehouses().isEmpty()) {
            throw new NotFoundException("Product not registered in any warehouse");
        }

        return productWarehouseDTO;
    }
}
