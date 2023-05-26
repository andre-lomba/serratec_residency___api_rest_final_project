package org.serratec.sales_manager_grupo5.dto.usuarioDTO;

import org.serratec.sales_manager_grupo5.model.Usuario;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Classe usada para retornar informações do cadastro de Usuario.")
public class UsuarioResponseDTO {

    private Long id;
    private String email;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Usuario model) {
        this.id = model.getId();
        this.email = model.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
