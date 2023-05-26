package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import javax.validation.Valid;

import org.serratec.sales_manager_grupo5.dto.usuarioDTO.UsuarioRequestDTO;
import org.serratec.sales_manager_grupo5.dto.usuarioDTO.UsuarioResponseDTO;
import org.serratec.sales_manager_grupo5.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> save(@Valid @RequestBody UsuarioRequestDTO cadastro) {
        UsuarioResponseDTO usuario = usuarioService.save(cadastro);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
}
