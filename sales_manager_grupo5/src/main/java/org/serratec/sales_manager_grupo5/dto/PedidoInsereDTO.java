package org.serratec.sales_manager_grupo5.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoInsereDTO {

    private Long id;
    private Date dataEmissao;
    private Long id_fornecedor;
    private List<ProdutoInsereDTO> produtos = new ArrayList<>();

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Long getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Long id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public List<ProdutoInsereDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoInsereDTO> produtos) {
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
