package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.InboundOrder;

import java.util.List;

public interface IInboundOrderService {
    BatchStockDTO save(InboundOrder inboundOrder) throws Exception;

    List<InboundOrder> getAll();

    Batch update(Batch batch);
}
