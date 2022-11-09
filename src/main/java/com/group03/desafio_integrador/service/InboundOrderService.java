package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advice.ExceptionDetails;
import com.group03.desafio_integrador.advice.ValidationErrorDetail;
import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import com.group03.desafio_integrador.repository.ProductAdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InboundOrderService implements IInboundOrderService{

    private final InboundOrderRepository inboundOrderRepository;

    private final ProductAdvertisingService productService;

    private final WarehouseService warehouseService;

    private final SectionService sectionService;

    @Override
    public List<InboundOrder> getAll() {
        return inboundOrderRepository.findAll();
    }

    @Override
    public BatchStockDTO save(InboundOrder inboundOrder) throws Exception {
        validateOrder(inboundOrder);

        InboundOrder order = inboundOrderRepository.save(inboundOrder);

        BatchStockDTO dto = BatchStockDTO.builder().batchStock(order.getBatchList()).build();
        return dto;
    }

    public void validateOrder(InboundOrder inboundOrder) throws Exception {
        List<Batch> batchList = inboundOrder.getBatchList();

        validateWarehouse(inboundOrder.getWarehouseId());
        validateProducts(batchList);
        validateSection(inboundOrder);
    }

    private void validateWarehouse(Warehouse warehouseId) throws Exception {
        Warehouse warehouse = warehouseService.getById(warehouseId.getWarehouseId());

//        if (warehouse.getManager() == null) {
//            throw new NotFoundException("Manager not found for this Warehouse!");
//        }
    }

    public void validateProducts(List<Batch> batchList) {
        List<ValidationErrorDetail> errorDetails = new ArrayList<>();

        for (Batch batch: batchList) {
            Long idProduct = batch.getProductId().getProductId();
            try {
                ProductAdvertising productAdvertising = productService.getById(idProduct);
            } catch (Exception ex) {
                errorDetails.add(
                        ValidationErrorDetail.builder()
                                .field("productId").message("Product " + idProduct + " not found!")
                                .build());
            }
        }

        if (!errorDetails.isEmpty()) {
            throw new NotFoundException("Products not found", errorDetails);
        }
    }

    private void validateSection(InboundOrder inboundOrder) {
        Section section = inboundOrder.getSectionId();

        Section sectionExists = sectionService.getById(section.getSectionId());

        float totalVolumeBatch = (float) 0.0;

        for (Batch batch : inboundOrder.getBatchList()) {
            Long idProduct = batch.getProductId().getProductId();
            ProductAdvertising productAdvertising = productService.getById(idProduct);

            if (!productAdvertising.getCategory().equals(sectionExists.getCategory())) {
                // TODO: 09/11/22 create new exception
                throw new NotFoundException("Product not belongs to this section");
            }

            totalVolumeBatch += batch.getVolume();
        }

        if (sectionExists.getCapacity() < totalVolumeBatch) {
            throw new NotFoundException("Capacity not available");
        }

        sectionExists.setCapacity(sectionExists.getCapacity() - totalVolumeBatch);
        inboundOrder.setSectionId(sectionExists);
    }


}
