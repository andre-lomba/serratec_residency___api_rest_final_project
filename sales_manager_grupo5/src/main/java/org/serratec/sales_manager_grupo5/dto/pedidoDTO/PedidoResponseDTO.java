package org.serratec.sales_manager_grupo5.dto.pedidoDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.serratec.sales_manager_grupo5.common.DateFormatter;
import org.serratec.sales_manager_grupo5.common.Round;
import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorResponseDTO;
import org.serratec.sales_manager_grupo5.dto.itemPedidoDTO.ItemPedidoResponseDTO;
import org.serratec.sales_manager_grupo5.model.ItemPedido;
import org.serratec.sales_manager_grupo5.model.Pedido;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a Pedido")
public class PedidoResponseDTO {

    private Long id;
    private FornecedorResponseDTO fornecedor;
    private String dataEmissao;
    private List<ItemPedidoResponseDTO> itens;
    private Double valorItens = 0.0;
    private Double descontoTotal = 0.0;
    private Double valorTotal;

    public PedidoResponseDTO(Pedido model) {
        this.id = model.getId();
        this.fornecedor = new FornecedorResponseDTO(model.getFornecedor());
        this.dataEmissao = DateFormatter.dateFormat(model.getDataEmissao());
        this.itens = new ArrayList<>();
        for (ItemPedido item : model.getItens()) {
            this.itens.add(new ItemPedidoResponseDTO(item));
            this.valorItens += (Round.round(item.getValorUnitario(), 2) * item.getQuantidade());
            this.descontoTotal += (Round.round(item.getDesconto(), 2) * item.getQuantidade());
        }
        this.valorItens = Round.round(this.valorItens, 2);
        this.descontoTotal = Round.round(this.descontoTotal, 2);
        this.valorTotal = Round.round(model.getValorTotal(), 2);
    }

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

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
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

    public Double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(Double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public Double getValorItens() {
        return valorItens;
    }

    public void setValorItens(Double valorItens) {
        this.valorItens = valorItens;
    }

}
