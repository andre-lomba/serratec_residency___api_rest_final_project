package org.serratec.sales_manager_grupo5.dto.produtoDTO;

import java.util.ArrayList;
import java.util.List;

import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaResponseDTO;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;
    private List<CategoriaResponseDTO> categorias = new ArrayList<>();

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

    public List<CategoriaResponseDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaResponseDTO> categorias) {
        this.categorias = categorias;
    }

}
