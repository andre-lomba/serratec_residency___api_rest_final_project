package org.serratec.sales_manager_grupo5.dto.pedidoDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.model.ItemPedido;

public class PedidoRequestDTO {

    private Fornecedor fornecedor;
    private Date dataEmissao;
    private Set<ItemPedido> itens = new HashSet<>();

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

}
