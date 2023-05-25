package org.serratec.sales_manager_grupo5.dto.categoriaDTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaRequestDTO {

    @NotBlank(message = "Nome deve ser preenchido")
    @Size(min = 3, max = 100, message = "Nome deve ter entre {min} e {max} caracteres")
    private String nome;
    @Size(max = 200, message = "Descrição deve ter no méximo {max} caracteres")
    private String descricao;
    private List<Long> id_produtos = new ArrayList<>();

    public CategoriaRequestDTO() {
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

    public List<Long> getId_produtos() {
        return id_produtos;
    }

    public void setId_produtos(List<Long> id_produtos) {
        this.id_produtos = id_produtos;
    }

}
