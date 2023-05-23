package org.serratec.sales_manager_grupo5.dto.pedidoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorResponseDTO;
import org.serratec.sales_manager_grupo5.dto.itemPedidoDTO.ItemPedidoResponseDTO;

public class PedidoResponseDTO {

    private Long id;
    private FornecedorResponseDTO fornecedor;
    private Date dataEmissao;
    private List<ItemPedidoResponseDTO> itens = new ArrayList<>();
    private Double valorTotal = 0.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FornecedorResponseDTO getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorResponseDTO fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public List<ItemPedidoResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoResponseDTO> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
