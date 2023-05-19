package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.PedidoDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Pedido;
import org.serratec.sales_manager_grupo5.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements ICRUDService<Pedido, PedidoDTO> {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Override
    public PedidoDTO create(Pedido obj) {
        return new PedidoDTO(pedidoRepository.save(obj));
    }

    @Override
    public Page<PedidoDTO> findAll(Pageable page) {
        List<Pedido> pedidoStream = pedidoRepository.findAll(page).getContent();
        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidoStream) {
            PedidoDTO pedidoDTO = new PedidoDTO(pedido);
            pedidosDTO.add(pedidoDTO);
        }
        return ConversorDeLista.convertListPedidoDTOToPage(pedidosDTO, page);
    }

    @Override
    public PedidoDTO findById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        return new PedidoDTO(opPedido.get());
    }

    @Override
    public PedidoDTO update(Long id, Pedido obj) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        obj.setId(id);
        return new PedidoDTO(pedidoRepository.save(obj));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        pedidoRepository.deleteById(id);
    }

}
