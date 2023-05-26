package org.serratec.sales_manager_grupo5.dto.categoriaDTO;

import java.util.ArrayList;
import java.util.List;

import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseDTO;
import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.model.Produto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a Categoria (com lista de Produto)")
public class CategoriaResponseProdutosDTO {

    private Long id;
    private String nome;
    private String descricao;
    private List<ProdutoResponseDTO> produtos;

    public CategoriaResponseProdutosDTO() {
    }

    public CategoriaResponseProdutosDTO(Categoria model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.descricao = model.getDescricao();
        this.produtos = new ArrayList<>();
        for (Produto produto : model.getProdutos()) {
            ProdutoResponseDTO prod = new ProdutoResponseDTO(produto);
            this.produtos.add(prod);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ProdutoResponseDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoResponseDTO> produtos) {
        this.produtos = produtos;
    }

}
