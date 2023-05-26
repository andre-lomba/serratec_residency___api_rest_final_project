package org.serratec.sales_manager_grupo5.dto.produtoDTO;

import java.util.ArrayList;
import java.util.List;

import org.serratec.sales_manager_grupo5.common.Round;
import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaResponseDTO;
import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.model.Produto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a Produto (com lista de Categoria)")
public class ProdutoResponseCategoriasDTO {

    private Long id;
    private String nome;
    private Double preco;
    private List<CategoriaResponseDTO> categorias;

    public ProdutoResponseCategoriasDTO(Produto model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.preco = Round.round(model.getPreco(), 2);
        this.categorias = new ArrayList<>();
        for (Categoria cat : model.getCategorias()) {
            this.categorias.add(new CategoriaResponseDTO(cat));
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

    public List<CategoriaResponseDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaResponseDTO> categorias) {
        this.categorias = categorias;
    }

}
