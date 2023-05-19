package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.FornecedorDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService implements ICRUDService<Fornecedor, FornecedorDTO> {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public FornecedorDTO create(Fornecedor obj) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findByNomeIgnoreCase(obj.getNome().trim());
        if (opFornecedor.isPresent())
            throw new EntidadeExistenteException("Fornecedor com o mesmo nome já registrado");
        Optional<Fornecedor> opFornecedorCnpj = fornecedorRepository
                .findByCnpjIgnoreCase(obj.getCnpj().trim());
        if (opFornecedorCnpj.isPresent())
            throw new EntidadeExistenteException("Fornecedor com o mesmo CNPJ já registrado");
        return new FornecedorDTO(fornecedorRepository.save(obj));
    }

    @Override
    public Page<FornecedorDTO> findAll(Pageable page) {
        List<Fornecedor> fornecedorStream = fornecedorRepository.findAll(page).getContent();
        List<FornecedorDTO> fornecedorDTOList = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedorStream) {
            FornecedorDTO fornecedorDTO = new FornecedorDTO(fornecedor);
            fornecedorDTOList.add(fornecedorDTO);
        }
        return ConversorDeLista.convertListFornecedorDTOToPage(fornecedorDTOList, page);
    }

    @Override
    public FornecedorDTO findById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        return new FornecedorDTO(fornecedorRepository.findById(id).get());
    }

    @Override
    public FornecedorDTO update(Long id, Fornecedor obj) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        Fornecedor fornecedorBanco = opFornecedor.get();
        obj.setId(id);
        if (!obj.getNome().trim().toLowerCase().equals(fornecedorBanco.getNome().trim().toLowerCase())
                && !obj.getCnpj().trim().equals(fornecedorBanco.getCnpj().trim())) {
            return create(obj);
        }
        return new FornecedorDTO(fornecedorRepository.save(obj));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        fornecedorRepository.deleteById(id);
    }

}
