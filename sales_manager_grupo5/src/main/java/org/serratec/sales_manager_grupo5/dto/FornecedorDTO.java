package org.serratec.sales_manager_grupo5.dto;

import java.util.List;

import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.model.Pedido;

public class FornecedorDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private List<Pedido> pedidos;

    public FornecedorDTO(Long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public FornecedorDTO(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.cnpj = fornecedor.getCnpj();
        for (Pedido pedido : fornecedor.getPedidos()) {
            pedidos.add(pedido);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
