package org.serratec.sales_manager_grupo5.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaRequestDTO;

@Entity
@Table
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;
    @Column
    private String nome;
    @Column
    private String descricao;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public Categoria() {
    }

    public Categoria(CategoriaRequestDTO request) {
        this.nome = request.getNome();
        this.descricao = request.getDescricao();
        this.produtos = new ArrayList<>();
        for (Long idProd : request.getId_produtos()) {
            Produto produto = new Produto();
            produto.setId(idProd);
            this.produtos.add(produto);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
