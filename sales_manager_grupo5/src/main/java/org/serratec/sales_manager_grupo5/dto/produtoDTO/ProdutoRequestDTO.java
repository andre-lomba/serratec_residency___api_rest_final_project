package org.serratec.sales_manager_grupo5.dto.produtoDTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para requisições por métodos POST e PUT para construção de Produto")
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome deve ser preenchido.")
    @Size(min = 3, max = 100, message = "Nome deve ter entre {min} e {max} caracteres.")
    private String nome;
    @NotNull(message = "Informe um preço para o produto.")
    private Double preco;
    private List<Long> id_categorias = new ArrayList<>();

    public ProdutoRequestDTO() {
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

    public List<Long> getId_categorias() {
        return id_categorias;
    }

    public void setId_categorias(List<Long> id_categorias) {
        this.id_categorias = id_categorias;
    }

}
