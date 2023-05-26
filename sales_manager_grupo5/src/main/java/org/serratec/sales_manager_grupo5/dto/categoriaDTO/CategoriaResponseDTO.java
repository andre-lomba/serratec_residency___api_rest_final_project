package org.serratec.sales_manager_grupo5.dto.categoriaDTO;

import org.serratec.sales_manager_grupo5.model.Categoria;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a Categoria (sem lista de Produto)")
public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;

    public CategoriaResponseDTO() {
    }

    public CategoriaResponseDTO(Categoria model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.descricao = model.getDescricao();
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

}
