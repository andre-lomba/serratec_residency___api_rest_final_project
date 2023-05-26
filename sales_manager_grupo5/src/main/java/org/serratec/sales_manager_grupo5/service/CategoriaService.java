package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

    @Transactional
    @Override
    public CategoriaResponseProdutosDTO create(CategoriaRequestDTO request) {
        Optional<Categoria> opCategoria = categoriaRepository.findByNomeIgnoreCase(request.getNome().trim());
        if (opCategoria.isPresent())
            throw new EntidadeExistenteException("Categoria com o mesmo nome já registrada");
        Categoria categoria = new Categoria(request);
        categoria = categoriaRepository.save(categoria);
        for (Long id_produto : request.getId_produtos()) {
            Optional<Produto> opProduto = produtoRepository.findById(id_produto);
            if (!opProduto.isPresent()) {
                String msgProd = String.format("Produto com id %d não encontrado.", id_produto);
                throw new EntidadeNaoEncontradaException(msgProd);
            }
            opProduto.get().getCategorias().add(categoria);
            Produto produto = produtoRepository.save(opProduto.get());
            categoria.getProdutos().add(produto);
        }
        return new CategoriaResponseProdutosDTO(categoriaRepository.save(categoria));
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

    @Transactional
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
        for (Produto produto : opCategoria.get().getProdutos()) {
            produto.getCategorias().remove(opCategoria.get());
            produtoRepository.save(produto);
        }
        Categoria categoria = new Categoria(request);
        categoria.setId(id);
        for (Long id_produto : request.getId_produtos()) {
            Optional<Produto> opProduto = produtoRepository.findById(id_produto);
            if (!opProduto.isPresent()) {
                String msgProd = String.format("Produto com id %d não encontrado.", id_produto);
                throw new EntidadeNaoEncontradaException(msgProd);
            }
            opProduto.get().getCategorias().add(categoria);
            Produto produto = produtoRepository.save(opProduto.get());
            categoria.getProdutos().add(produto);
        }
        return new CategoriaResponseProdutosDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if (!opCategoria.isPresent())
            throw new EntidadeNaoEncontradaException(msgerror);
        for (Produto produto : opCategoria.get().getProdutos()) {
            produto.getCategorias().remove(opCategoria.get());
            produtoRepository.save(produto);
        }
        categoriaRepository.deleteById(id);
    }

}
