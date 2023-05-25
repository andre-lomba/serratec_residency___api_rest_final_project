package org.serratec.sales_manager_grupo5.dto.itemPedidoDTO;

import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseDTO;
import org.serratec.sales_manager_grupo5.model.ItemPedido;

public class ItemPedidoResponseDTO {

    private ProdutoResponseDTO produto;
    private Integer quantidade;
    private Double valorUnitario;
    private Double desconto;
    private Double valorTotalItem;

    public ItemPedidoResponseDTO(ItemPedido model) {
        this.produto = new ProdutoResponseDTO(model.getProduto());
        this.quantidade = model.getQuantidade();
        this.valorUnitario = model.getValorUnitario();
        this.desconto = model.getDesconto();
        this.valorTotalItem = model.getValorTotalItem();
    }

    public ProdutoResponseDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoResponseDTO produto) {
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
