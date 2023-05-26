package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseCategoriasDTO;
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
public class ProdutoService implements ICRUDService<ProdutoRequestDTO, ProdutoResponseCategoriasDTO> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    String msgerror = "Produto não encontrado. Verifique o id informado.";

    @Override
    public ProdutoResponseCategoriasDTO create(ProdutoRequestDTO request) {
        Optional<Produto> opProduto = produtoRepository.findByNomeIgnoreCase(request.getNome().trim());
        if (opProduto.isPresent())
            throw new EntidadeExistenteException("Produto com o mesmo nome já registrado");
        Produto produto = new Produto(request);
        for (Long id_cat : request.getId_categorias()) {
            Optional<Categoria> opCategoria = categoriaRepository.findById(id_cat);
            if (!opCategoria.isPresent()) {
                String catNotFoundMsg = String.format("Categoria com id %d não encontrada", id_cat);
                throw new EntidadeNaoEncontradaException(catNotFoundMsg);
            }
            produto.getCategorias().add(opCategoria.get());
        }
        return new ProdutoResponseCategoriasDTO(produtoRepository.save(produto));
    }

    @Override
    public Page<ProdutoResponseCategoriasDTO> findAll(Pageable page) {
        List<Produto> produtos = produtoRepository.findAll(page).getContent();
        List<ProdutoResponseCategoriasDTO> produtosDTO = new ArrayList<>();
        for (Produto produto : produtos) {
            produtosDTO.add(new ProdutoResponseCategoriasDTO(produto));
        }
        return ConversorDeLista.convertListProdutoDTOToPage(produtosDTO, page);
    }

    @Override
    public ProdutoResponseCategoriasDTO findById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        return new ProdutoResponseCategoriasDTO(opProduto.get());
    }

    @Override
    public ProdutoResponseCategoriasDTO update(Long id, ProdutoRequestDTO request) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        if (!request.getNome().equals(opProduto.get().getNome())) {
            opProduto = produtoRepository.findByNomeIgnoreCase(request.getNome().trim());
            if (opProduto.isPresent())
                throw new EntidadeExistenteException("Produto com o mesmo nome já registrado");
        }
        Produto produto = new Produto(request);
        produto.setId(id);
        for (Long id_cat : request.getId_categorias()) {
            Optional<Categoria> opCategoria = categoriaRepository.findById(id_cat);
            if (!opCategoria.isPresent()) {
                String catNotFoundMsg = String.format("Categoria com id %d não encontrada", id_cat);
                throw new EntidadeNaoEncontradaException(catNotFoundMsg);
            }
            produto.getCategorias().add(opCategoria.get());
        }

        return new ProdutoResponseCategoriasDTO(produtoRepository.save(produto));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        if (!opProduto.get().getItens().isEmpty()) {
            String error = String
                    .format("Exclusão não executada: Produto com id %d consta em um ou mais pedidos.", id);
            throw new EntidadeExistenteException(error);
        }
        produtoRepository.deleteById(id);
    }

}
