package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaRequestDTO;
import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaResponseDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICRUDService<CategoriaRequestDTO, CategoriaResponseDTO> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoriaResponseDTO create(CategoriaRequestDTO obj) {
        obj.setId(null);
        Optional<Categoria> opCategoria = categoriaRepository.findByNomeIgnoreCase(obj.getNome().trim());
        if (opCategoria.isPresent())
            throw new EntidadeExistenteException("Categoria com o mesmo nome já registrada");
        Categoria categoria = mapper.map(obj, Categoria.class);
        categoria = categoriaRepository.save(categoria);
        return mapper.map(categoria, CategoriaResponseDTO.class);
    }

    @Override
    public Page<CategoriaResponseDTO> findAll(Pageable page) {
        List<Categoria> categoriaList = categoriaRepository.findAll(page).getContent();
        List<CategoriaResponseDTO> categoriaDTOList = new ArrayList<>();
        for (Categoria categoria : categoriaList) {
            CategoriaResponseDTO categoriaDTO = mapper.map(categoria, CategoriaResponseDTO.class);
            categoriaDTOList.add(categoriaDTO);
        }
        return ConversorDeLista.convertListCategoriaDTOToPage(categoriaDTOList, page);
    }

    @Override
    public CategoriaResponseDTO findById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        return mapper.map(opCategoria.get(), CategoriaResponseDTO.class);
    }

    @Override
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO obj) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        Categoria categoriaBanco = opCategoria.get();
        obj.setId(id);
        if (!obj.getNome().trim().toLowerCase().equals(categoriaBanco.getNome().trim().toLowerCase())) {
            opCategoria = categoriaRepository.findByNomeIgnoreCase(obj.getNome().trim());
            if (opCategoria.isPresent())
                throw new EntidadeExistenteException("Categoria com o mesmo nome já registrada");
        }
        categoriaBanco = mapper.map(obj, Categoria.class);
        return mapper.map(categoriaBanco, CategoriaResponseDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        categoriaRepository.deleteById(id);
    }

}
