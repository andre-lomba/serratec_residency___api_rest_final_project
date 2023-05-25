package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.pedidoDTO.PedidoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.pedidoDTO.PedidoResponseDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.model.ItemPedido;
import org.serratec.sales_manager_grupo5.model.Pedido;
import org.serratec.sales_manager_grupo5.repository.FornecedorRepository;
import org.serratec.sales_manager_grupo5.repository.ItemPedidoRepository;
import org.serratec.sales_manager_grupo5.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService implements ICRUDService<PedidoRequestDTO, PedidoResponseDTO> {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    String msgerror = "Pedido não encontrado. Verifique o id informado.";

    @Transactional
    @Override
    public PedidoResponseDTO create(PedidoRequestDTO request) {
        Optional<Fornecedor> optFornecedor = fornecedorRepository.findById(request.getId_fornecedor());
        if (!optFornecedor.isPresent()) {
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        }
        Pedido pedido = pedidoRepository.saveAndFlush(new Pedido());
        Long id = pedido.getId();
        pedido = new Pedido(request);
        pedido.setId(id);
        Double valorTotal = 0.0;
        for (ItemPedido itemPedido : pedido.getItens()) {
            itemPedido.setPedido(pedido);
            itemPedido = itemPedidoService.create(itemPedido);
            valorTotal += itemPedido.getValorTotalItem();
        }
        pedido.setValorTotal(valorTotal);
        return new PedidoResponseDTO(pedidoRepository.save(pedido));
    }

    @Override
    public Page<PedidoResponseDTO> findAll(Pageable page) {
        List<Pedido> pedidoStream = pedidoRepository.findAll(page).getContent();
        List<PedidoResponseDTO> pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidoStream) {
            pedidosDTO.add(new PedidoResponseDTO(pedido));
        }
        return ConversorDeLista.convertListPedidoDTOToPage(pedidosDTO, page);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        return new PedidoResponseDTO(opPedido.get());
    }

    @Transactional
    @Override
    public PedidoResponseDTO update(Long id, PedidoRequestDTO request) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        for (ItemPedido item : opPedido.get().getItens()) {
            itemPedidoRepository.deleteById(item.getId());
        }
        Optional<Fornecedor> optFornecedor = fornecedorRepository.findById(request.getId_fornecedor());
        if (!optFornecedor.isPresent()) {
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        }
        Pedido pedido = new Pedido(request);
        pedido.setId(id);
        Double valorTotal = 0.0;
        for (ItemPedido itemPedido : pedido.getItens()) {
            itemPedido = itemPedidoService.create(itemPedido);
            valorTotal += itemPedido.getValorTotalItem();
        }
        pedido.setValorTotal(valorTotal);
        return new PedidoResponseDTO(pedidoRepository.save(pedido));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        pedidoRepository.deleteById(id);
    }

}
