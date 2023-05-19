package org.serratec.sales_manager_grupo5.service;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.ItemPedido;
import org.serratec.sales_manager_grupo5.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public ItemPedido create(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public Page<ItemPedido> findAll(Pageable page) {
        return itemPedidoRepository.findAll(page);
    }

    public ItemPedido findById(Long id) {
        Optional<ItemPedido> opItemPedido = itemPedidoRepository.findById(id);
        if (!opItemPedido.isPresent())
            throw new EntidadeNaoEncontradaException("ItemPedido não encontrado. Verifique o id informado.");
        return itemPedidoRepository.findById(id).get();
    }

    public ItemPedido update(Long id, ItemPedido itemPedido) {
        Optional<ItemPedido> opItemPedido = itemPedidoRepository.findById(id);
        if (!opItemPedido.isPresent())
            throw new EntidadeNaoEncontradaException("ItemPedido não encontrado. Verifique o id informado.");
        itemPedido.setId(id);
        return itemPedidoRepository.save(itemPedido);
    }

    public void deleteById(Long id) {
        Optional<ItemPedido> opItemPedido = itemPedidoRepository.findById(id);
        if (!opItemPedido.isPresent())
            throw new EntidadeNaoEncontradaException("ItemPedido não encontrado. Verifique o id informado.");
        itemPedidoRepository.deleteById(id);
    }

}
