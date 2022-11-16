package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
import com.group03.desafio_integrador.dto.BatchDueDateDTO;
import com.group03.desafio_integrador.dto.BatchDueDateStockDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.entities.ProductAdvertising;
import com.group03.desafio_integrador.entities.Section;
import com.group03.desafio_integrador.repository.BatchRepository;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import com.group03.desafio_integrador.service.interfaces.IBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatchService {

    private final BatchRepository repository;

    private final InboundOrderRepository inboundOrderRepository;

    /**
     * Método responsável por retornar o lote de acordo com o Id informado.
     * @author Gabriel Morais
     * @param id - Long
     * @return Batch - Retorna uma entidade do tipo Batch.
     * @throws NotFoundException
     */
    @Override
    public Batch getById(Long id) throws NotFoundException {
        Optional<Batch> batch = repository.findById(id);
        return batch.orElseThrow(() -> new NotFoundException("Batch not found!"));
    }

    /**
     * Método responsável por salvar um novo lote.
     * @author Gabriel Morais
     * @param batch - Batch
     * @return Batch - Retorna uma entidade do tipo Batch.
     */
    @Override
    public Batch save(Batch batch) {
        return repository.save(batch);
    }

    /**
     * Método responsável por listar os lotes de produto de acordo com o Id do Produto
     * @author Amanda Zotelli
     * @param id - ProductAdvertising
     * @return List<Batch>  - Retorna uma Lista da entidade do tipo Batch.
     */
    @Override
    public List<Batch> findBatchByProductId(ProductAdvertising id) {
        return repository.findAllByProductId(id);
    }

    @Override
    public BatchDueDateStockDTO getAllDueDate(Integer numberOfDays, String section) {

        LocalDate currentDate = LocalDate.now().plusDays(numberOfDays);

        List<Batch> batchList = repository.findAllByExpirationDateGreaterThan(currentDate);

        List<BatchDueDateDTO> listBatchDTO = new ArrayList<>();

        List<InboundOrder> inboundOrderList = inboundOrderRepository.findAll();

        List<InboundOrder> filterOrderSectionList = inboundOrderList.stream()
                .filter(orderBatch -> section.equals(String.valueOf(orderBatch.getSectionId().getSectionId())))
                .collect(Collectors.toList());

        for (InboundOrder inboundOrder : filterOrderSectionList) {
            List<Batch> batchFilterList = inboundOrder.getBatchList();

            for (Batch batch : batchList) {

                List<Batch> compareBatchWithId = batchFilterList.stream().filter(item -> item.getBatchId().equals(batch.getBatchId())).collect(Collectors.toList());

                if(!compareBatchWithId.isEmpty()) {
                    BatchDueDateDTO batchDueDateDTO = buildBatchDueDateDTO(section, batch);
                    listBatchDTO.add(batchDueDateDTO);
                }
            }
        }

        if (listBatchDTO.isEmpty()) {
            throw new NotFoundException("Not Found batch for expiration date and section");
        }

        return BatchDueDateStockDTO.builder()
                .batchDueDateStock(listBatchDTO)
                .build();
    }

    private static BatchDueDateDTO buildBatchDueDateDTO(String section, Batch batch) {
        return BatchDueDateDTO.builder()
                .batchNumber(batch.getBatchId())
                .productId(batch.getProductId().getProductId())
                .category(Long.valueOf(section))
                .dueDate(batch.getExpirationDate())
                .quantity(batch.getProductQuantity())
                .build();
    }
}
