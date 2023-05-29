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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/usuarios")
@Api(tags = "UsuarioController", description = "Endpoints para cadastro de Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ApiOperation(value = "Cadastra um Usuario", notes = "Cadastra um Usuario se não existente.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
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
