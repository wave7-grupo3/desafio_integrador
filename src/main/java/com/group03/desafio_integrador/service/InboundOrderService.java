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

    private final IProductAdvertisingService productService;

    private final IWarehouseService warehouseService;

    private final ISectionService sectionService;

    private final IBatchService batchService;

    /**
     * Método responsável por listar todos os pedidos de ordem do armazem.
     * @author Gabriel Morais
     * @return List<InboundOrder> - Retorna uma entidade do tipo InboundOrder.
     */
    @Override
    public List<InboundOrder> getAll() {
        return inboundOrderRepository.findAll();
    }

    /**
     * Método responsável por atualizar as informações do lote contido no armazem e validar se a capacidade do armazem suporta o recebimento da carga.
     * @author Gabriel Morais
     * @param Batch - batch
     * @return Batch - Retorna uma entidade do tipo Batch.
     */
    @Override
    public Batch update(Batch batch) {
        Batch oldBatch = batchService.getById(batch.getBatchId());

        Float updateVolume = oldBatch.getVolume() - batch.getVolume();

        Section section = sectionService.findByCategory(oldBatch.getProductId().getCategory());

        if ((updateVolume + section.getCapacity()) < 0) {
            throw new NotFoundException("Capacity not available");
        }

        section.setCapacity(updateVolume + section.getCapacity());

        sectionService.save(section);

        return batchService.save(batch);
    }

    /**
     * Método responsável por salvar um novo lote no pedido de ordem do armazem.
     * @author Gabriel Morais
     * @param InboundOrder - inboundOrder
     * @return BatchStockDTO - Retorna um dto do tipo BatchStockDTO.
     * @throws Exception
     */
    @Override
    public BatchStockDTO save(InboundOrder inboundOrder) throws Exception {
        validateOrder(inboundOrder);

        InboundOrder order = inboundOrderRepository.save(inboundOrder);

        BatchStockDTO dto = BatchStockDTO.builder().batchStock(order.getBatchList()).build();
        return dto;
    }

    /**
     * Método responsável por realizar a validação do pedido de ordem.
     * @author Gabriel Morais
     * @param InboundOrder - inboundOrder
     * @throws Exception
     */
    public void validateOrder(InboundOrder inboundOrder) throws Exception {
        List<Batch> batchList = inboundOrder.getBatchList();

        validateWarehouse(inboundOrder.getWarehouseId());
        validateProducts(batchList);
        validateSection(inboundOrder);
    }

    /**
     * Método responsável por realizar a verificação se o armazem existe.
     * @author Gabriel Morais
     * @param Warehouse - warehouseId
     * @throws Exception
     */
    private void validateWarehouse(Warehouse warehouseId) throws Exception {
        Warehouse warehouse = warehouseService.getById(warehouseId.getWarehouseId());

//        if (warehouse.getManager() == null) {
//            throw new NotFoundException("Manager not found for this Warehouse!");
//        }
    }

    /**
     * Método responsável por realizar a verificação das informações dos produtos no lote.
     * @author Gabriel Morais
     * @param List<Batch> - batchList
     * @throws NotFoundException
     */
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

    /**
     * Método responsável por realizar a verificação da sessão.
     * @author Gabriel Morais
     * @param InboundOrder - inboundOrder
     * @throws NotFoundException
     */
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
