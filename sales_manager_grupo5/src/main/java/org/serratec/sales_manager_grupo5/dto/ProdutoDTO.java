package org.serratec.sales_manager_grupo5.dto;

import java.util.List;

import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.model.Produto;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;
    private List<Categoria> categorias;

    public ProdutoDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        for (Categoria categoria : produto.getCategorias()) {
            categorias.add(categoria);
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

}
