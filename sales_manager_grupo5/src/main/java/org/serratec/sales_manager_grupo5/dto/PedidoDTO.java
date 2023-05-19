package org.serratec.sales_manager_grupo5.dto;

import java.util.Date;
import java.util.List;

import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.model.ItemPedido;
import org.serratec.sales_manager_grupo5.model.Pedido;

public class PedidoDTO {

    private Long id;
    private Date dataEmissao;
    private Double valorTotal;
    private List<ItemPedido> itens;
    private Fornecedor fornecedor;

    public PedidoDTO(Long id, Date dataEmissao, Double valorTotal) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.valorTotal = valorTotal;
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataEmissao = pedido.getDataEmissao();
        this.valorTotal = pedido.getValorTotal();
        this.fornecedor = pedido.getFornecedor();
        for (ItemPedido item : pedido.getItens()) {
            itens.add(item);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
