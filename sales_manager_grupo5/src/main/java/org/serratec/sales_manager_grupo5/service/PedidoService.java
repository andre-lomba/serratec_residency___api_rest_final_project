package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.PedidoDTO;
import org.serratec.sales_manager_grupo5.dto.PedidoInsereDTO;
import org.serratec.sales_manager_grupo5.dto.ProdutoInsereDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.model.ItemPedido;
import org.serratec.sales_manager_grupo5.model.Pedido;
import org.serratec.sales_manager_grupo5.model.Produto;
import org.serratec.sales_manager_grupo5.repository.FornecedorRepository;
import org.serratec.sales_manager_grupo5.repository.PedidoRepository;
import org.serratec.sales_manager_grupo5.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements ICRUDService<PedidoInsereDTO, PedidoDTO> {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Transactional
    @Override
    public PedidoDTO create(PedidoInsereDTO obj) {
        obj.setId(pedidoRepository.save(new Pedido()).getId());
        Pedido pedido = new Pedido();
        pedido.setId(obj.getId());
        pedido.setDataEmissao(obj.getDataEmissao());
        Optional<Fornecedor> fornecedorBanco = fornecedorRepository.findById(obj.getId_fornecedor());
        if (!fornecedorBanco.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor com id '{obj.getId_fornecedor()}' não encontrado");
        pedido.getFornecedor().setId(fornecedorBanco.get().getId());
        for (ProdutoInsereDTO produto : obj.getProdutos()) {
            Optional<Produto> produtoBanco = produtoRepository.findById(produto.getId_produto());
            if (!produtoBanco.isPresent())
                throw new EntidadeNaoEncontradaException("Produto com id '{produto.getId_produto()}' não encontrado");
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.getId().getPedido().setId(pedido.getId());
            itemPedido.getId().getProduto().setId(produtoBanco.get().getId());
            itemPedido.setQuantidade(produto.getQuantidade());
            if (produto.getQuantidade() > 3) {
                itemPedido.setDesconto(0.9);
            } else {
                itemPedido.setDesconto(1.0);
            }
            itemPedido.setValorUnitario(produtoBanco.get().getPreco());
            itemPedido.setValorTotal(
                    (itemPedido.getValorUnitario() * itemPedido.getQuantidade()) * itemPedido.getDesconto());
            pedido.getItens().add(itemPedido);
            pedido.setValorTotal(pedido.getValorTotal() + itemPedido.getValorTotal());
        }
        return new PedidoDTO(pedidoRepository.save(pedido));
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
    public PedidoDTO update(Long id, PedidoInsereDTO obj) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        obj.setId(id);
        Pedido pedido = new Pedido();
        pedido.setId(obj.getId());
        pedido.setDataEmissao(obj.getDataEmissao());
        Optional<Fornecedor> fornecedorBanco = fornecedorRepository.findById(obj.getId_fornecedor());
        if (!fornecedorBanco.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor com id '{obj.getId_fornecedor()}' não encontrado");
        pedido.getFornecedor().setId(fornecedorBanco.get().getId());
        for (ProdutoInsereDTO produto : obj.getProdutos()) {
            Optional<Produto> produtoBanco = produtoRepository.findById(produto.getId_produto());
            if (!produtoBanco.isPresent())
                throw new EntidadeNaoEncontradaException("Produto com id '{produto.getId_produto()}' não encontrado");
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.getId().getPedido().setId(pedido.getId());
            itemPedido.getId().getProduto().setId(produtoBanco.get().getId());
            itemPedido.setQuantidade(produto.getQuantidade());
            if (produto.getQuantidade() > 3) {
                itemPedido.setDesconto(0.9);
            } else {
                itemPedido.setDesconto(1.0);
            }
            itemPedido.setValorUnitario(produtoBanco.get().getPreco());
            itemPedido.setValorTotal(
                    (itemPedido.getValorUnitario() * itemPedido.getQuantidade()) * itemPedido.getDesconto());
            pedido.getItens().add(itemPedido);
            pedido.setValorTotal(pedido.getValorTotal() + itemPedido.getValorTotal());
        }
        return new PedidoDTO(pedidoRepository.save(pedido));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException("Pedido não encontrado. Verifique o id informado.");
        pedidoRepository.deleteById(id);
    }

}
