package org.serratec.sales_manager_grupo5.dto.itemPedidoDTO;

import org.serratec.sales_manager_grupo5.model.Produto;

public class ItemPedidoRequestDTO {

    private Produto produto;
    private Integer quantidade;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
