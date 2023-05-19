package org.serratec.sales_manager_grupo5.service;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Pedido;
import org.serratec.sales_manager_grupo5.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido create(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Page<Pedido> findAll(Pageable page) {
        return pedidoRepository.findAll(page);
    }

    public Pedido findById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        return pedidoRepository.findById(id).get();
    }

    public Pedido update(Long id, Pedido pedido) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    public void deleteById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        pedidoRepository.deleteById(id);
    }

}
