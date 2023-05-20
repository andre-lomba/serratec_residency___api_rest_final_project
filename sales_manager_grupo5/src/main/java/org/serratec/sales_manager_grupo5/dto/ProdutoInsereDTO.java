package org.serratec.sales_manager_grupo5.dto;

public class ProdutoInsereDTO {

    private Long id_produto;
    private Double quantidade;

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

}
