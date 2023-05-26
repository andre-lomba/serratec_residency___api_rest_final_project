package org.serratec.sales_manager_grupo5.dto.produtoDTO;

import org.serratec.sales_manager_grupo5.common.Round;
import org.serratec.sales_manager_grupo5.model.Produto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a Produto (sem lista de Categoria)")
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;

    public ProdutoResponseDTO(Produto model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.preco = Round.round(model.getPreco(), 2);
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

}
