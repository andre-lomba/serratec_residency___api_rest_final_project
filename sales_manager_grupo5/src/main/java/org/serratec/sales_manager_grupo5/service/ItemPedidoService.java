package org.serratec.sales_manager_grupo5.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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
        if (!produto.isPresent()) {
            Long id = obj.getProduto().getId();
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
        itemPedidoRepository.save(itemPedido);
        return mapper.map(itemPedido, ItemPedidoResponseDTO.class);
    }

    public void deleteIfExists(Long idPedido) {
        List<ItemPedido> itensRegistrados = itemPedidoRepository.findAll();
        for (ItemPedido item : itensRegistrados) {
            if (item.getPedido().getId().equals(idPedido)) {
                itemPedidoRepository.deleteById(item.getId());
            }
        }
    }

}
