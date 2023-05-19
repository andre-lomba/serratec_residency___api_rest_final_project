package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.CategoriaDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICRUDService<Categoria, CategoriaDTO> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO create(Categoria obj) {
        Optional<Categoria> opCategoria = categoriaRepository.findByNomeIgnoreCase(obj.getNome().trim());
        if (opCategoria.isPresent())
            throw new EntidadeExistenteException("Categoria com o mesmo nome já registrada");
        return new CategoriaDTO(categoriaRepository.save(obj));
    }

    @Override
    public Page<CategoriaDTO> findAll(Pageable page) {
        List<Categoria> categoriaList = categoriaRepository.findAll(page).getContent();
        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
        for (Categoria categoria : categoriaList) {
            CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
            categoriaDTOList.add(categoriaDTO);
        }
        return ConversorDeLista.convertListCategoriaDTOToPage(categoriaDTOList, page);
    }

    @Override
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        return new CategoriaDTO(categoriaRepository.findById(id).get());
    }

    @Override
    public CategoriaDTO update(Long id, Categoria obj) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        Categoria categoriaBanco = opCategoria.get();
        obj.setId(id);
        if (!obj.getNome().trim().toLowerCase().equals(categoriaBanco.getNome().trim().toLowerCase())) {
            return create(obj);
        }
        return new CategoriaDTO(categoriaRepository.save(obj));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        categoriaRepository.deleteById(id);
    }

}
