package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.ProdutoDTO;
import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Produto;
import org.serratec.sales_manager_grupo5.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService implements ICRUDService<Produto, ProdutoDTO> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public ProdutoDTO create(Produto obj) {
        Optional<Produto> opProduto = produtoRepository.findByNomeIgnoreCase(obj.getNome().trim());
        if (opProduto.isPresent())
            throw new EntidadeExistenteException("Produto com o mesmo nome já registrado");
        return new ProdutoDTO(produtoRepository.save(obj));
    }

    @Override
    public Page<ProdutoDTO> findAll(Pageable page) {
        List<Produto> produtos = produtoRepository.findAll(page).getContent();
        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        for (Produto produto : produtos) {
            ProdutoDTO produtoDTO = new ProdutoDTO(produto);
            produtosDTO.add(produtoDTO);
        }
        return ConversorDeLista.convertListProdutoDTOToPage(produtosDTO, page);
    }

    @Override
    public ProdutoDTO findById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        return new ProdutoDTO(opProduto.get());
    }

    @Override
    public ProdutoDTO update(Long id, Produto obj) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        Produto produtoBanco = opProduto.get();
        obj.setId(id);
        if (!obj.getNome().trim().toLowerCase().equals(produtoBanco.getNome().trim().toLowerCase())) {
            return create(obj);
        }
        return new ProdutoDTO(produtoRepository.save(obj));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        produtoRepository.deleteById(id);
    }

}
