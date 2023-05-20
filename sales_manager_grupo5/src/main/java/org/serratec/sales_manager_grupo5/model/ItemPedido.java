package org.serratec.sales_manager_grupo5.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    @Column
    private Double quantidade;

    @Column(name = "valor_unitario")
    private Double valorUnitario;

    @Column
    private Double desconto;

    @Column(name = "valor_total")
    private Double valorTotal;

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, Pedido pedido, Double quantidade, Double valorUnitario, Double desconto,
            Double valorTotal) {
        this.id.setProduto(produto);
        this.id.setPedido(pedido);
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
