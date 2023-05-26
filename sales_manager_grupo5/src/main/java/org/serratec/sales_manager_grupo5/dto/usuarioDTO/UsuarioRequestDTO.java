package org.serratec.sales_manager_grupo5.dto.usuarioDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank(message = "Preencha o campo email.")
    @Size(min = 6, message = "O email deve ter no mínimo {min} caracteres.")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Endereço de email com formato inválido")
    private String email;
    @NotBlank(message = "Preencha o campo password.")
    @Size(min = 8, message = "O password deve ter no mínimo {min} caracteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password deve conter pelo menos um número, uma letra minúscula, uma letra maiúscula e um caractere especial (ex.:@#$%). Espaços em branco não são permitidos.")
    private String password;
    @NotBlank(message = "Preencha o campo confirmPassword para confirmar o password.")
    private String confirmPassword;

    public UsuarioRequestDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
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
        UsuarioRequestDTO other = (UsuarioRequestDTO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (confirmPassword == null) {
            if (other.confirmPassword != null)
                return false;
        } else if (!confirmPassword.equals(other.confirmPassword))
            return false;
        return true;
    }

}
