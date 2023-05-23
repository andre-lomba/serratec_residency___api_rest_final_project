package org.serratec.sales_manager_grupo5.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sales_manager_grupo5.dto.itemPedidoDTO.ItemPedidoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.itemPedidoDTO.ItemPedidoResponseDTO;
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

    @Autowired
    private ModelMapper mapper;

    public ItemPedidoResponseDTO create(ItemPedido obj) {
        ItemPedido itemPedido = mapper.map(obj, ItemPedido.class);
        Optional<Produto> produto = produtoRepository.findById(itemPedido.getProduto().getId());
        if(!produto.isPresent()){
            Long id = obj.getProduto().getId();
            throw new EntidadeNaoEncontradaException("Produto com o id '${id}' não encontrado");
        }
        itemPedido.setValorUnitario(produto.get().getPreco());
        if (itemPedido.getQuantidade() >= 3) {
            itemPedido.setDesconto(0.9);
        } else {
            itemPedido.setDesconto(1.0);
        }
        itemPedido.setValorTotal(
                (itemPedido.getValorUnitario() * itemPedido.getQuantidade()) * itemPedido.getDesconto());
        itemPedidoRepository.saveAndFlush(itemPedido);
        return mapper.map(itemPedido, ItemPedidoResponseDTO.class);
    }

}
