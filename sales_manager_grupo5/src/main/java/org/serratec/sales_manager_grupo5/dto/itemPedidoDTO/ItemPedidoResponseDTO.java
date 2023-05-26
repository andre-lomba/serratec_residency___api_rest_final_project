package org.serratec.sales_manager_grupo5.dto.itemPedidoDTO;

import org.serratec.sales_manager_grupo5.common.Round;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseDTO;
import org.serratec.sales_manager_grupo5.model.ItemPedido;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a ItemPedido")
public class ItemPedidoResponseDTO {

    private ProdutoResponseDTO produto;

    private Double valorUnitario;
    private Double descontoUnitario;
    private Integer quantidade;
    private Double valorTotalItem;

    public ItemPedidoResponseDTO(ItemPedido model) {
        this.produto = new ProdutoResponseDTO(model.getProduto());
        this.quantidade = model.getQuantidade();
        this.valorUnitario = Round.round(model.getValorUnitario(), 2);
        this.descontoUnitario = Round.round(model.getDesconto(), 2);
        this.valorTotalItem = Round.round(model.getValorTotalItem(), 2);
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
        return descontoUnitario;
    }

    public void setDesconto(Double desconto) {
        this.descontoUnitario = desconto;
    }

    public Double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(Double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }

}
