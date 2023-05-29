package org.serratec.sales_manager_grupo5.dto.pedidoDTO;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.serratec.sales_manager_grupo5.dto.itemPedidoDTO.ItemPedidoRequestDTO;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para requisições por métodos POST e PUT para construção de Pedido")
public class PedidoRequestDTO {

    @NotNull(message = "Informe o id do fornecedor.")
    private Long id_fornecedor;
    @NotEmpty(message = "Informe os itens do pedido")
    private Set<ItemPedidoRequestDTO> itens = new HashSet<>();

    public PedidoRequestDTO() {
    }

    public Long getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Long id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public Set<ItemPedidoRequestDTO> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedidoRequestDTO> itens) {
        this.itens = itens;
    }

}
