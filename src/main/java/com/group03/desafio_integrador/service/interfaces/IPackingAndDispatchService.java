package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.dto.DispatchDTO;
import com.group03.desafio_integrador.dto.PackingOrderDTO;

import java.util.List;
import java.util.Set;

public interface IPackingAndDispatchService {
    List<PackingOrderDTO> getAllCartProductFinished();
    Set<DispatchDTO> getAllPackingForDispatch();
}
