package org.serratec.sales_manager_grupo5.dto.fornecedorDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FornecedorRequestDTO {

    @NotBlank(message = "Nome deve ser preenchido.")
    @Size(min = 3, max = 100, message = "Nome deve ter entre {min} e {max} caracteres.")
    private String nome;
    @NotBlank(message = "CNPJ deve ser preenchido.")
    @Size(min = 14, max = 14, message = "CNPJ deve ter {min} números, sem símbolos.")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve ser formado por 14 carcateres numéricos, sem letras ou símbolos.")
    private String cnpj;

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

}
