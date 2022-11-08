package com.group03.desafio_integrador.service;

import com.group03.desafio_integrador.entities.InboundOrder;
import com.group03.desafio_integrador.repository.InboundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InboundOrderService implements IInboundOrderService{

    private final InboundOrderRepository repository;

    @Override
    public InboundOrder save(InboundOrder inboundOrder) {
        return repository.save(inboundOrder);
    }
}
