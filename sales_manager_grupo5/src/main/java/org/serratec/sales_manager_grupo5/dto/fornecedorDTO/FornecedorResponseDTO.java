package org.serratec.sales_manager_grupo5.dto.fornecedorDTO;

import org.serratec.sales_manager_grupo5.model.Fornecedor;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para respostas relacionadas a Fornecedor (sem lista de Pedido)")
public class FornecedorResponseDTO {

    private Long id;
    private String nome;
    private String cnpj;

    public FornecedorResponseDTO(Fornecedor model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.cnpj = model.getCnpj();
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

}
