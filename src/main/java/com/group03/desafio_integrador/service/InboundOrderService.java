package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InboundOrderService implements IInboundOrderService{

    private final InboundOrderRepository repository;

    @Override
    public BatchStockDTO save(InboundOrder inboundOrder) {
        InboundOrder order = repository.save(inboundOrder);

        BatchStockDTO dto = BatchStockDTO.builder().batchStock(order.getBatchList()).build();
        return dto;
    }

    @Override
    public List<InboundOrder> getAll() {
        return repository.findAll();
    }
}
