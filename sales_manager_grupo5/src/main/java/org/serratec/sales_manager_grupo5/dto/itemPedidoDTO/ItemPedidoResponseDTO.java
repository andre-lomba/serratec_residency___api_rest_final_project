package org.serratec.sales_manager_grupo5.dto.itemPedidoDTO;

import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseReduceDTO;

public class ItemPedidoResponseDTO {

    private ProdutoResponseReduceDTO produto;
    private Integer quantidade;
    private Double valorUnitario;
    private Double desconto;
    private Double valorTotalItem;

    public ProdutoResponseReduceDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResponseReduceDTO produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(Double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }

}
