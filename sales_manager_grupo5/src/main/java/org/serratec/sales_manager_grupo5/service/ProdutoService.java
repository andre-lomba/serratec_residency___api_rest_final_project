package org.serratec.sales_manager_grupo5.service;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.model.Produto;
import org.serratec.sales_manager_grupo5.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {
        Optional<Produto> opProduto = produtoRepository.findByNomeIgnoreCase(produto.getNome());
        if (opProduto.isPresent())
            throw new EntidadeExistenteException("Produto com o mesmo nome já registrado");
        return produtoRepository.save(produto);
    }

    public Page<Produto> findAll(Pageable page) {
        return produtoRepository.findAll(page);
    }

    public Produto findById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        return produtoRepository.findById(id).get();
    }

    public Produto update(Long id, Produto produto) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        Produto produtoBanco = opProduto.get();
        if (produto.getNome() != produtoBanco.getNome()) {
            Optional<Produto> opProdutoNome = produtoRepository.findByNomeIgnoreCase(produto.getNome());
            if (opProdutoNome.isPresent())
                throw new EntidadeExistenteException("Produto com o mesmo nome já registrado");
        }
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        produtoRepository.deleteById(id);
    }

}
