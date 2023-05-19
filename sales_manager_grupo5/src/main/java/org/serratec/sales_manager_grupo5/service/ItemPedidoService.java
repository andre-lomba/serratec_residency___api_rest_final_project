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
public class ItemPedidoService implements ICRUDService<ItemPedido, ItemPedido> {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public ItemPedido create(ItemPedido obj) {
        return itemPedidoRepository.save(obj);
    }

    @Override
    public Page<ItemPedido> findAll(Pageable page) {
        return itemPedidoRepository.findAll(page);
    }

    @Override
    public ItemPedido findById(Long id) {
        Optional<ItemPedido> opItemPedido = itemPedidoRepository.findById(id);
        if (!opItemPedido.isPresent())
            throw new EntidadeNaoEncontradaException("ItemPedido não encontrado. Verifique o id informado.");
        return itemPedidoRepository.findById(id).get();
    }

    @Override
    public ItemPedido update(Long id, ItemPedido obj) {
        Optional<ItemPedido> opItemPedido = itemPedidoRepository.findById(id);
        if (!opItemPedido.isPresent())
            throw new EntidadeNaoEncontradaException("ItemPedido não encontrado. Verifique o id informado.");
        obj.setId(id);
        return itemPedidoRepository.save(obj);
    }

    @Override
    public void deleteById(Long id) {
        Optional<ItemPedido> opItemPedido = itemPedidoRepository.findById(id);
        if (!opItemPedido.isPresent())
            throw new EntidadeNaoEncontradaException("ItemPedido não encontrado. Verifique o id informado.");
        itemPedidoRepository.deleteById(id);
    }

}
