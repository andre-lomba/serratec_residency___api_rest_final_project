package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorRequestDTO;
import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorResponseDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService implements ICRUDService<FornecedorRequestDTO, FornecedorResponseDTO> {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public FornecedorResponseDTO create(FornecedorRequestDTO obj) {
        obj.setId(null);
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findByNomeIgnoreCase(obj.getNome().trim());
        if (opFornecedor.isPresent())
            throw new EntidadeExistenteException("Fornecedor com o mesmo nome já registrado");
        Optional<Fornecedor> opFornecedorCnpj = fornecedorRepository
                .findByCnpjIgnoreCase(obj.getCnpj().trim());
        if (opFornecedorCnpj.isPresent())
            throw new EntidadeExistenteException("Fornecedor com o mesmo CNPJ já registrado");
        Fornecedor fornecedor = mapper.map(obj, Fornecedor.class);
        fornecedor = fornecedorRepository.save(fornecedor);
        return mapper.map(fornecedor, FornecedorResponseDTO.class);
    }

    @Override
    public Page<FornecedorResponseDTO> findAll(Pageable page) {
        List<Fornecedor> fornecedorStream = fornecedorRepository.findAll(page).getContent();
        List<FornecedorResponseDTO> fornecedorDTOList = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedorStream) {
            FornecedorResponseDTO fornecedorDTO = mapper.map(fornecedor, FornecedorResponseDTO.class);
            fornecedorDTOList.add(fornecedorDTO);
        }
        return ConversorDeLista.convertListFornecedorDTOToPage(fornecedorDTOList, page);
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        return mapper.map(opFornecedor.get(), FornecedorResponseDTO.class);
    }

    @Override
    public FornecedorResponseDTO update(Long id, FornecedorRequestDTO obj) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        obj.setId(id);
        if (!obj.getNome().trim().toLowerCase().equals(opFornecedor.get().getNome().trim().toLowerCase())) {
            opFornecedor = fornecedorRepository.findByNomeIgnoreCase(obj.getNome().trim());
            if (opFornecedor.isPresent())
                throw new EntidadeExistenteException("Fornecedor com o mesmo nome já registrado");
        }
        if (!obj.getCnpj().trim().equals(opFornecedor.get().getCnpj().trim())) {
            opFornecedor = fornecedorRepository
                    .findByCnpjIgnoreCase(obj.getCnpj().trim());
            if (opFornecedor.isPresent())
                throw new EntidadeExistenteException("Fornecedor com o mesmo CNPJ já registrado");
        }
        Fornecedor fornecedor = mapper.map(obj, Fornecedor.class);
        fornecedor = fornecedorRepository.save(fornecedor);
        return mapper.map(fornecedor, FornecedorResponseDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Fornecedor> opFornecedor = fornecedorRepository.findById(id);
        if (!opFornecedor.isPresent())
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado. Verifique o id informado.");
        fornecedorRepository.deleteById(id);
    }

}
