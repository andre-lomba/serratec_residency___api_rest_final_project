package org.serratec.sales_manager_grupo5.service;

import org.serratec.sales_manager_grupo5.dto.usuarioDTO.UsuarioRequestDTO;
import org.serratec.sales_manager_grupo5.dto.usuarioDTO.UsuarioResponseDTO;
import org.serratec.sales_manager_grupo5.exception.UnmatchingPasswordException;
import org.serratec.sales_manager_grupo5.model.Usuario;
import org.serratec.sales_manager_grupo5.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UsuarioResponseDTO save(UsuarioRequestDTO cadastro) {
        Usuario usuario = new Usuario(cadastro);
        if (!cadastro.getPassword().equals(cadastro.getConfirmPassword())) {
            throw new UnmatchingPasswordException("confirmPassword deve ser igual ao password.");
        }
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return new UsuarioResponseDTO(usuarioRepository.save(usuario));
    }

}
