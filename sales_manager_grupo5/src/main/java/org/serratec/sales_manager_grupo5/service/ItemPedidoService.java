package org.serratec.sales_manager_grupo5.service;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.ItemPedido;
import org.serratec.sales_manager_grupo5.model.Produto;
import org.serratec.sales_manager_grupo5.repository.ItemPedidoRepository;
import org.serratec.sales_manager_grupo5.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ItemPedido create(ItemPedido itemPedido) {
        Optional<Produto> produto = produtoRepository.findById(itemPedido.getProduto().getId());
        if (!produto.isPresent()) {
            Long id = itemPedido.getProduto().getId();
            String errorMessage = String.format("Produto com o id %d não encontrado", id);
            throw new EntidadeNaoEncontradaException(errorMessage);
        }
        itemPedido.setValorUnitario(produto.get().getPreco());
        if (itemPedido.getQuantidade() >= 3) {
            itemPedido.setDesconto(0.9);
        } else {
            itemPedido.setDesconto(1.0);
        }
        itemPedido.setValorTotalItem(
                (itemPedido.getValorUnitario() * itemPedido.getQuantidade()) * itemPedido.getDesconto());

        return itemPedidoRepository.save(itemPedido);
    }

}
