package org.serratec.sales_manager_grupo5.service;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    public Fornecedor create(Fornecedor fornecedor) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findByNomeIgnoreCase(fornecedor.getNome());
        if (opFornecedor.isPresent())
            throw new EntidadeExistenteException("Fornecedor com o mesmo nome já registrado");
        Optional<Fornecedor> opFornecedorCnpj = fornecedorRepository.findByCnpjIgnoreCase(fornecedor.getCnpj());
        if(opFornecedorCnpj.isPresent())
            throw new EntidadeExistenteException("Fornecedor com o mesmo CNPJ já registrado");
        return fornecedorRepository.save(fornecedor);
    }

    public Page<Fornecedor> findAll(Pageable page) {
        return fornecedorRepository.findAll(page);
    }

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        return fornecedorRepository.findById(id).get();
    }

    public Fornecedor update(Long id, Fornecedor fornecedor) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        Fornecedor fornecedorBanco = opFornecedor.get();
        if (fornecedor.getNome() != fornecedorBanco.getNome()) {
            Optional<Fornecedor> opFornecedorNome = fornecedorRepository.findByNomeIgnoreCase(fornecedor.getNome());
            if (!opFornecedorNome.isPresent())
                throw new EntidadeExistenteException("Fornecedor com o mesmo nome já registrado");
        }
        fornecedor.setId(id);
        return fornecedorRepository.save(fornecedor);
    }

    public void deleteById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        fornecedorRepository.deleteById(id);
    }
    
}
