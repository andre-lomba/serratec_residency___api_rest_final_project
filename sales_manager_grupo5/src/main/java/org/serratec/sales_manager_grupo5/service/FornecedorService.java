package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorRequestDTO;
import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorResponsePedidosDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
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
public class FornecedorService implements ICRUDService<FornecedorRequestDTO, FornecedorResponsePedidosDTO> {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    String msgNotFound = "Fornecedor não encontrado. Verifique o id informado.";
    String msgRepeatedName = "Fornecedor com o mesmo nome já registrado";
    String msgRepeatedCNPJ = "Fornecedor com o mesmo CNPJ já registrado";

    @Override
    public FornecedorResponsePedidosDTO create(FornecedorRequestDTO request) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findByNomeIgnoreCase(request.getNome().trim());
        if (opFornecedor.isPresent())
            throw new EntidadeExistenteException(msgRepeatedName);
        Optional<Fornecedor> opFornecedorCnpj = fornecedorRepository
                .findByCnpjIgnoreCase(request.getCnpj().trim());
        if (opFornecedorCnpj.isPresent())
            throw new EntidadeExistenteException(msgRepeatedCNPJ);
        Fornecedor fornecedor = new Fornecedor(request);
        return new FornecedorResponsePedidosDTO(fornecedorRepository.save(fornecedor));
    }

    @Override
    public Page<FornecedorResponsePedidosDTO> findAll(Pageable page) {
        List<Fornecedor> fornecedorStream = fornecedorRepository.findAll(page).getContent();
        List<FornecedorResponsePedidosDTO> fornecedorDTOList = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedorStream) {
            fornecedorDTOList.add(new FornecedorResponsePedidosDTO(fornecedor));
        }
        return ConversorDeLista.convertListFornecedorDTOToPage(fornecedorDTOList, page);
    }

    @Override
    public FornecedorResponsePedidosDTO findById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException(msgNotFound);
        return new FornecedorResponsePedidosDTO(opFornecedor.get());
    }

    @Override
    public FornecedorResponsePedidosDTO update(Long id, FornecedorRequestDTO request) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException(msgNotFound);
        if (!request.getNome().trim().toLowerCase().equals(opFornecedor.get().getNome().trim().toLowerCase())) {
            Optional<Fornecedor> opFornecedor2 = fornecedorRepository.findByNomeIgnoreCase(request.getNome().trim());
            if (opFornecedor2.isPresent())
                throw new EntidadeExistenteException(msgRepeatedName);
        }
        if (!request.getCnpj().trim().equals(opFornecedor.get().getCnpj().trim())) {
            opFornecedor = fornecedorRepository
                    .findByCnpjIgnoreCase(request.getCnpj().trim());
            if (opFornecedor.isPresent())
                throw new EntidadeExistenteException(msgRepeatedCNPJ);
        }
        Fornecedor fornecedor = new Fornecedor(request);
        fornecedor.setId(id);
        return new FornecedorResponsePedidosDTO(fornecedorRepository.save(fornecedor));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException(msgNotFound);
        List<Pedido> pedidos = pedidoRepository.findAll();
        for (Pedido pedido : pedidos) {
            for (ItemPedido item : pedido.getItens()) {
                if (item.getProduto().getId().equals(id)) {
                    String error = String
                            .format("Exclusão não executada: Fornecedor com id %d consta em um ou mais pedidos.", id);
                    throw new EntidadeExistenteException(error);
                }
            }
        }
        fornecedorRepository.deleteById(id);
    }

}
