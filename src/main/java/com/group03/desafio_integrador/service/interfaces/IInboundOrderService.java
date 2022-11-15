package com.group03.desafio_integrador.service.interfaces;

import com.group03.desafio_integrador.dto.BatchStockDTO;
import com.group03.desafio_integrador.dto.ProductWarehouseStockDTO;
import com.group03.desafio_integrador.entities.Batch;
import com.group03.desafio_integrador.entities.InboundOrder;

import java.util.List;

public interface IInboundOrderService {

    /**
     * Método responsável por salvar um novo lote no pedido de ordem do armazem.
     * @author Gabriel Morais
     * @param InboundOrder - inboundOrder
     * @return BatchStockDTO - Retorna um dto do tipo BatchStockDTO.
     * @throws Exception
     */

    // TODO: implementar Exception específica
    BatchStockDTO save(InboundOrder inboundOrder) throws Exception;

    /**
     * Método responsável por listar todos os pedidos de ordem do armazem.
     * @author Gabriel Morais
     * @return List<InboundOrder> - Retorna uma entidade do tipo InboundOrder.
     */
    List<InboundOrder> getAll();

    /**
     * Método responsável por atualizar as informações do lote contido no armazem.
     * @author Gabriel Morais
     * @param Batch - batch
     * @return Batch - Retorna uma entidade do tipo Batch.
     */
    Batch update(Batch batch);

    List<ProductWarehouseStockDTO> getAllProductWarehouseStock(Long productId) throws Exception;

    List<ProductWarehouseStockDTO> getAllFilters(List<ProductWarehouseStockDTO> productWarehouseStockDTOList, String filter);
}
