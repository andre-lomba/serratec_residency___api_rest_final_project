package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaRequestDTO;
import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaResponseProdutosDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.model.Produto;
import org.serratec.sales_manager_grupo5.repository.CategoriaRepository;
import org.serratec.sales_manager_grupo5.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICRUDService<CategoriaRequestDTO, CategoriaResponseProdutosDTO> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    String msgerror = "Categoria não encontrada. Verifique o id informado.";

    @Override
    public CategoriaResponseProdutosDTO create(CategoriaRequestDTO request) {
        Optional<Categoria> opCategoria = categoriaRepository.findByNomeIgnoreCase(request.getNome().trim());
        if (opCategoria.isPresent())
            throw new EntidadeExistenteException("Categoria com o mesmo nome já registrada");
        for (Long id_prod : request.getId_produtos()) {
            Optional<Produto> opProduto = produtoRepository.findById(id_prod);
            if (!opProduto.isPresent()) {
                String prodNotFoundMsg = String.format("Produto com id %d não encontrado", id_prod);
                throw new EntidadeNaoEncontradaException(prodNotFoundMsg);
            }
        }
        Categoria categoria = categoriaRepository.save(new Categoria(request));
        return new CategoriaResponseProdutosDTO(categoria);
    }

    @Override
    public Page<CategoriaResponseProdutosDTO> findAll(Pageable page) {
        List<Categoria> categoriaList = categoriaRepository.findAll(page).getContent();
        List<CategoriaResponseProdutosDTO> categoriaDTOList = new ArrayList<>();
        for (Categoria categoria : categoriaList) {
            categoriaDTOList.add(new CategoriaResponseProdutosDTO(categoria));
        }
        return ConversorDeLista.convertListCategoriaDTOToPage(categoriaDTOList, page);
    }

    @Override
    public CategoriaResponseProdutosDTO findById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        return new CategoriaResponseProdutosDTO(opCategoria.get());
    }

    @Override
    public CategoriaResponseProdutosDTO update(Long id, CategoriaRequestDTO request) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        if (!request.getNome().trim().toLowerCase().equals(opCategoria.get().getNome().trim().toLowerCase())) {
            opCategoria = categoriaRepository.findByNomeIgnoreCase(request.getNome().trim());
            if (opCategoria.isPresent())
                throw new EntidadeExistenteException("Categoria com o mesmo nome já registrada");
        }
        Categoria categoria = new Categoria(request);
        categoria.setId(id);
        return new CategoriaResponseProdutosDTO(categoriaRepository.save(categoria));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        categoriaRepository.deleteById(id);
    }

}
