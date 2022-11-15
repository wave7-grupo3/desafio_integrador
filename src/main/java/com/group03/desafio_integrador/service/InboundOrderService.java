package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.ValidationErrorDetail;
import com.group03.desafio_integrador.advisor.exceptions.NotAcceptableException;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.*;
import com.group03.desafio_integrador.entities.*;
import com.group03.desafio_integrador.entities.entities_enum.SortingEnum;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import com.group03.desafio_integrador.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InboundOrderService implements IInboundOrderService {
    private final InboundOrderRepository inboundOrderRepository;
    private final IProductAdvertisingService productService;
    private final IWarehouseService warehouseService;
    private final ISectionService sectionService;
    private final IBatchService batchService;

    /**
     * Método responsável por listar todos os pedidos de ordem do armazem.
     *
     * @return List<InboundOrder> - Retorna uma entidade do tipo InboundOrder.
     * @author Gabriel Morais
     */
    @Override
    public List<InboundOrder> getAll() {
        return inboundOrderRepository.findAll();
    }

    /**
     * Método responsável por atualizar as informações do lote contido no armazem e validar se a capacidade do armazem suporta o recebimento da carga.
     *
     * @param Batch - batch
     * @return Batch - Retorna uma entidade do tipo Batch.
     * @author Gabriel Morais
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
     *
     * @param InboundOrder - inboundOrder
     * @return BatchStockDTO - Retorna um dto do tipo BatchStockDTO.
     * @throws Exception
     * @author Gabriel Morais
     */
    @Override
    public BatchStockDTO save(InboundOrder inboundOrder) throws Exception {
        validateOrder(inboundOrder);

        InboundOrder order = inboundOrderRepository.save(inboundOrder);

        return BatchStockDTO.builder().batchStock(order.getBatchList()).build();
    }

    /**
     * Método responsável por realizar a validação do pedido de ordem.
     *
     * @param InboundOrder - inboundOrder
     * @throws Exception
     * @author Gabriel Morais
     */
    public void validateOrder(InboundOrder inboundOrder) throws Exception {
        List<Batch> batchList = inboundOrder.getBatchList();

        validateWarehouse(inboundOrder.getWarehouseId());
        validateProducts(batchList);
        validateSection(inboundOrder);
    }

    /**
     * Método responsável por realizar a verificação se o armazem existe.
     *
     * @param Warehouse - warehouseId
     * @throws Exception
     * @author Gabriel Morais
     */
    // TODO: fazer exception específico
    private void validateWarehouse(Warehouse warehouseId) throws Exception {
        Warehouse warehouse = warehouseService.getById(warehouseId.getWarehouseId());

        if (warehouse.getManager() == null) {
            throw new NotFoundException("Manager not found for this Warehouse!");
        }
    }

    /**
     * Método responsável por realizar a verificação das informações dos produtos no lote.
     *
     * @param List<Batch> - batchList
     * @throws NotFoundException
     * @author Gabriel Morais
     */
    private void validateProducts(List<Batch> batchList) {
        List<ValidationErrorDetail> errorDetails = new ArrayList<>();

        for (Batch batch : batchList) {
            Long idProduct = batch.getProductId().getProductId();
            try {
                productService.getById(idProduct);
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
     *
     * @param InboundOrder - inboundOrder
     * @throws NotFoundException
     * @author Gabriel Morais
     */
    private void validateSection(InboundOrder inboundOrder) {
        Section section = inboundOrder.getSectionId();

        Section sectionExists = sectionService.getById(section.getSectionId());

        float totalVolumeBatch = (float) 0.0;

        for (Batch batch : inboundOrder.getBatchList()) {
            Long idProduct = batch.getProductId().getProductId();
            ProductAdvertising productAdvertising = productService.getById(idProduct);

            if (!productAdvertising.getCategory().equals(sectionExists.getCategory())) {
                throw new NotAcceptableException("Product " + productAdvertising.getProductName() + " not belongs to this section");
            }

            totalVolumeBatch += batch.getVolume();
        }

        if (sectionExists.getCapacity() < totalVolumeBatch) {
            throw new NotFoundException("Capacity not available");
        }

        sectionExists.setCapacity(sectionExists.getCapacity() - totalVolumeBatch);
        inboundOrder.setSectionId(sectionExists);
    }

    private static List<ProductWarehouseStockDTO> getFilterWarehouseById(SectionDTO sectionDto, List<ProductWarehouseStockDTO> productWarehouse) {
        return productWarehouse.stream()
                .filter(productDTO -> productDTO.getSectionDTO().getSectionId().equals(sectionDto.getSectionId()) && productDTO.getSectionDTO().getWarehouseId().equals(sectionDto.getWarehouseId()))
                .collect(Collectors.toList());
    }

    public List<ProductWarehouseStockDTO> getAllProductWarehouseStock(Long productId) throws Exception {

        List<InboundOrder> inboundOrderList = getAll();
        List<Batch> batchStream;
        List<ProductWarehouseStockDTO> productWarehouseStockDTOList = new ArrayList<>();
        List<ValidationErrorDetail> errorDetails = new ArrayList<>();

        for (InboundOrder inbound : inboundOrderList) {
            validateOrder(inbound);
            batchStream = getBatchStreamFilteredByProductId(productId, inbound);
            SectionDTO sectionDto = buildSectionDTO(inbound);
            verifyBatchDueDate(productId, batchStream, errorDetails);

            List<ProductWarehouseStockDTO> warehouseByIdList = getFilterWarehouseById(sectionDto, productWarehouseStockDTOList);

            List<BatchDTO> batchStockDTOS = getBatchStockDTOS(batchStream);

            if (!batchStream.isEmpty()) {
                fillProductWarehouseStockList(productId, productWarehouseStockDTOList, sectionDto, warehouseByIdList, batchStockDTOS);
            }
        }

        return productWarehouseStockDTOList;

    }


    private static void verifyBatchDueDate(
            Long productId,
            List<Batch> batchStream,
            List<ValidationErrorDetail> errorDetails
    ) {
        for (Batch batch : batchStream) {
            ProductAdvertisingService.verifyProductExpirationDate(errorDetails, batch, productId);
        }
    }

    private static void fillProductWarehouseStockList(
            Long productId,
            List<ProductWarehouseStockDTO> productWarehouseStockDTOList,
            SectionDTO sectionDto,
            List<ProductWarehouseStockDTO> warehouseByIdList,
            List<BatchDTO> batchStockDTOS
    ) {
        if (warehouseByIdList.isEmpty()) {
            productWarehouseStockDTOList.add(
                    buildProductWarehouseStockDTO(productId, sectionDto, batchStockDTOS)
            );
        } else {
            for (ProductWarehouseStockDTO productWarehouseStockDTO : productWarehouseStockDTOList) {
                Boolean compareSectionId = productWarehouseStockDTO.getSectionDTO().getSectionId().equals(sectionDto.getSectionId());
                Boolean compareSectionWarehouseId = productWarehouseStockDTO.getSectionDTO().getWarehouseId().equals(sectionDto.getWarehouseId());
                if (compareSectionId && compareSectionWarehouseId) {
                    productWarehouseStockDTO.getBatchDTO().addAll(batchStockDTOS);
                }
            }
        }
    }

    private static ProductWarehouseStockDTO buildProductWarehouseStockDTO(
            Long productId,
            SectionDTO sectionDto,
            List<BatchDTO> batchStockDTOS
    ) {
        return ProductWarehouseStockDTO.builder()
                .productId(productId)
                .sectionDTO(sectionDto)
                .batchDTO(batchStockDTOS)
                .build();
    }

    private static List<BatchDTO> getBatchStockDTOS(List<Batch> batchStream) {
        return batchStream.stream().map(batch -> BatchDTO.builder()
                        .batchId(batch.getBatchId())
                        .quantity(batch.getProductQuantity())
                        .expirationDate(batch.getExpirationDate())
                        .build())
                .collect(Collectors.toList());
    }

    private static SectionDTO buildSectionDTO(InboundOrder inbound) {
        return SectionDTO.builder()
                .sectionId(inbound.getSectionId().getSectionId())
                .warehouseId(inbound.getWarehouseId().getWarehouseId())
                .build();
    }

    private static List<Batch> getBatchStreamFilteredByProductId(Long productId, InboundOrder inbound) {
        return inbound.getBatchList().stream()
                .filter(batchProduct -> batchProduct.getProductId().getProductId().equals(productId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductWarehouseStockDTO> getAllFilters(List<ProductWarehouseStockDTO> productWarehouseStockDTOList, String filter) {

        List<BatchDTO> batchSortedDTO = new ArrayList<>();

        if (filter.equalsIgnoreCase(String.valueOf(SortingEnum.V))) {
            for (ProductWarehouseStockDTO product : productWarehouseStockDTOList) {
                batchSortedDTO = SortingBatch.ordenatedByExpirationDate(product.getBatchDTO());
                product.setBatchDTO(batchSortedDTO);
            }
        } else if (filter.equalsIgnoreCase(String.valueOf(SortingEnum.Q))) {
            for (ProductWarehouseStockDTO product : productWarehouseStockDTOList) {
                batchSortedDTO = SortingBatch.ordenatedByQuantity(product.getBatchDTO());
                product.setBatchDTO(batchSortedDTO);
            }
        } else {
            for (ProductWarehouseStockDTO product : productWarehouseStockDTOList) {
                batchSortedDTO = SortingBatch.ordenatedByBatch(product.getBatchDTO());
                product.setBatchDTO(batchSortedDTO);
            }
        }

        return productWarehouseStockDTOList;
    }

}

