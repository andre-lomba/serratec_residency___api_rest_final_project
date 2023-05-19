package org.serratec.sales_manager_grupo5.service;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria create(Categoria categoria) {
        Optional<Categoria> opCategoria = categoriaRepository.findByNomeIgnoreCase(categoria.getNome().trim());
        if (opCategoria.isPresent())
            throw new EntidadeExistenteException("Categoria com o mesmo nome já registrada");
        return categoriaRepository.save(categoria);
    }

    public Page<Categoria> findAll(Pageable page) {
        return categoriaRepository.findAll(page);
    }

    public Categoria findById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        return categoriaRepository.findById(id).get();
    }

    public Categoria update(Long id, Categoria categoria) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        Categoria categoriaBanco = opCategoria.get();
        categoria.setId(id);
        if (!categoria.getNome().trim().toLowerCase().equals(categoriaBanco.getNome().trim().toLowerCase())) {
            return create(categoria);
        }
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException("Categoria não encontrada. Verifique o id informado.");
        categoriaRepository.deleteById(id);
    }

}
