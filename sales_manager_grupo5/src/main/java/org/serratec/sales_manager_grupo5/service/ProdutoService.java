package org.serratec.sales_manager_grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.serratec.sales_manager_grupo5.common.ConversorDeLista;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseDTO;
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
public class ProdutoService implements ICRUDService<ProdutoRequestDTO, ProdutoResponseDTO> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProdutoResponseDTO create(ProdutoRequestDTO obj) {
        obj.setId(null);
        Optional<Produto> opProduto = produtoRepository.findByNomeIgnoreCase(obj.getNome().trim());
        if (opProduto.isPresent())
            throw new EntidadeExistenteException("Produto com o mesmo nome já registrado");
        for (Categoria categoria : obj.getCategorias()) {
            Optional<Categoria> opCategoria = categoriaRepository.findById(categoria.getId());
            if (!opCategoria.isPresent())
                throw new EntidadeNaoEncontradaException("Categoria não encontrada");
        }
        Produto produto = mapper.map(obj, Produto.class);
        produto = produtoRepository.save(produto);
        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    @Override
    public Page<ProdutoResponseDTO> findAll(Pageable page) {
        List<Produto> produtos = produtoRepository.findAll(page).getContent();
        List<ProdutoResponseDTO> produtosDTO = new ArrayList<>();
        for (Produto produto : produtos) {
            ProdutoResponseDTO produtoDTO = mapper.map(produto, ProdutoResponseDTO.class);
            produtosDTO.add(produtoDTO);
        }
        return ConversorDeLista.convertListProdutoDTOToPage(produtosDTO, page);
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        return mapper.map(opProduto.get(), ProdutoResponseDTO.class);
    }

    @Override
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO obj) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        obj.setId(id);
        if (!obj.getNome().equals(opProduto.get().getNome())) {
            opProduto = produtoRepository.findByNomeIgnoreCase(obj.getNome().trim());
            if (opProduto.isPresent())
                throw new EntidadeExistenteException("Produto com o mesmo nome já registrado");
        }
        for (Categoria categoria : obj.getCategorias()) {
            Optional<Categoria> opCategoria = categoriaRepository.findById(categoria.getId());
            if (!opCategoria.isPresent())
                throw new EntidadeNaoEncontradaException("Categoria não encontrada");
        }
        Produto produto = mapper.map(obj, Produto.class);
        produto = produtoRepository.save(produto);
        return mapper.map(produto, ProdutoResponseDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Produto> opProduto = produtoRepository.findById(id);
        if (!opProduto.isPresent())
            throw new EntidadeNaoEncontradaException("Produto não encontrado. Verifique o id informado.");
        produtoRepository.deleteById(id);
    }

}
