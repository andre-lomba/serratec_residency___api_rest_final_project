package org.serratec.sales_manager_grupo5.dto.itemPedidoDTO;

import javax.validation.constraints.NotNull;

public class ItemPedidoRequestDTO {

    @NotNull(message = "Informe o id do produto.")
    private Long id_produto;
    @NotNull(message = "Informe a quantidade do produto.")
    private Integer quantidade;

    public ItemPedidoRequestDTO() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

}
