package org.serratec.sales_manager_grupo5.dto.produtoDTO;

import java.util.HashSet;
import java.util.Set;

import org.serratec.sales_manager_grupo5.model.Categoria;

public class ProdutoRequestDTO {

    private Long id;
    private String nome;
    private Double preco;
    private Set<Categoria> categorias = new HashSet<>();

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

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
