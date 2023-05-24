package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.itemPedidoDTO.ItemPedidoResponseDTO;
import org.serratec.sales_manager_grupo5.dto.pedidoDTO.PedidoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.pedidoDTO.PedidoResponseDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.model.ItemPedido;
import org.serratec.sales_manager_grupo5.model.Pedido;
import org.serratec.sales_manager_grupo5.repository.FornecedorRepository;
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
    private ModelMapper mapper;

    String msgerror = "Pedido não encontrado. Verifique o id informado.";

    @Transactional
    @Override
    public PedidoResponseDTO create(PedidoRequestDTO obj) {
        Pedido pedido = pedidoRepository.saveAndFlush(new Pedido());
        Optional<Fornecedor> optFornecedor = fornecedorRepository.findById(obj.getFornecedor().getId());
        if (!optFornecedor.isPresent()) {
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        }
        List<ItemPedidoResponseDTO> itensResponseDTO = new ArrayList<>();
        Double valorTotal = 0.0;
        for (ItemPedido itemPedido : obj.getItens()) {
            itemPedido.getPedido().setId(pedido.getId());
            ItemPedidoResponseDTO itemResponse = itemPedidoService.create(itemPedido);
            itensResponseDTO.add(itemResponse);
            valorTotal += itemResponse.getValorTotalItem();
        }
        PedidoResponseDTO response = mapper.map(obj, PedidoResponseDTO.class);
        response.setItens(itensResponseDTO);
        response.setValorTotal(valorTotal);
        response.setId(pedido.getId());
        pedido = mapper.map(response, Pedido.class);
        for (ItemPedido item : pedido.getItens()) {
            item.getPedido().setId(pedido.getId());
        }
        pedidoRepository.save(pedido);
        return response;
    }

    @Override
    public Page<PedidoResponseDTO> findAll(Pageable page) {
        List<Pedido> pedidoStream = pedidoRepository.findAll(page).getContent();
        List<PedidoResponseDTO> pedidosDTO = new ArrayList<>();
        for (Pedido pedido : pedidoStream) {
            PedidoResponseDTO pedidoDTO = mapper.map(pedido, PedidoResponseDTO.class);
            pedidosDTO.add(pedidoDTO);
        }
        return ConversorDeLista.convertListPedidoDTOToPage(pedidosDTO, page);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        return mapper.map(opPedido.get(), PedidoResponseDTO.class);
    }

    @Override
    public PedidoResponseDTO update(Long id, PedidoRequestDTO obj) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        Pedido pedido = opPedido.get();
        Optional<Fornecedor> optFornecedor = fornecedorRepository.findById(obj.getFornecedor().getId());
        if (!optFornecedor.isPresent()) {
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        }
        itemPedidoService.deleteIfExists(id);
        List<ItemPedidoResponseDTO> itensResponseDTO = new ArrayList<>();
        Double valorTotal = 0.0;
        for (ItemPedido itemPedido : obj.getItens()) {
            itemPedido.getPedido().setId(pedido.getId());
            ItemPedidoResponseDTO itemResponse = itemPedidoService.create(itemPedido);
            itensResponseDTO.add(itemResponse);
            valorTotal += itemResponse.getValorTotalItem();
        }
        PedidoResponseDTO response = mapper.map(obj, PedidoResponseDTO.class);
        response.setItens(itensResponseDTO);
        response.setValorTotal(valorTotal);
        response.setId(pedido.getId());
        pedido = mapper.map(response, Pedido.class);
        for (ItemPedido item : pedido.getItens()) {
            item.getPedido().setId(pedido.getId());
        }
        pedidoRepository.save(pedido);
        return response;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if (!opPedido.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        pedidoRepository.deleteById(id);
    }

}
