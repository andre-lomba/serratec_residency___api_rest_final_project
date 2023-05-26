package org.serratec.sales_manager_grupo5.repository;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

}
