package org.serratec.sales_manager_grupo5.dto.fornecedorDTO;

import java.util.ArrayList;
import java.util.List;

import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.model.Pedido;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a Fornecedor (com lista de Pedido)")
public class FornecedorResponsePedidosDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private List<Long> id_pedidos;

    public FornecedorResponsePedidosDTO(Fornecedor model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.cnpj = model.getCnpj();
        this.id_pedidos = new ArrayList<>();
        for (Pedido pedido : model.getPedidos()) {
            this.id_pedidos.add(pedido.getId());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(List<Long> id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

}
