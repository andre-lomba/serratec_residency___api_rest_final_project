package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import org.serratec.sales_manager_grupo5.dto.FornecedorDTO;
import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.serratec.sales_manager_grupo5.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

        @Autowired
        FornecedorService fornecedorService;

        @PostMapping
        @ApiOperation(value = "Adiciona um fornecedor", notes = "Adiciona um fornecedor")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Cria o fornecedor e retorna ele no corpo"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<FornecedorDTO> create(@RequestBody Fornecedor fornecedor) {
                FornecedorDTO fornecedorDTO = fornecedorService.create(fornecedor);
                URI uri = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(fornecedorDTO.getId())
                                .toUri();
                return ResponseEntity.created(uri).body(fornecedorDTO);
        }

        @GetMapping
        @ApiOperation(value = "Lista 10 fornecedores por página", notes = "Listagem de fornecedores")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna todos os fornecedores"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Page<FornecedorDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(fornecedorService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca um fornecedor", notes = "Busca um fornecedor por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna o fornecedor correspondente ao id"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<FornecedorDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(fornecedorService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza um fornecedor", notes = "Atualiza um fornecedor por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna o fornecedor atualizado"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<FornecedorDTO> update(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
                return ResponseEntity.ok(fornecedorService.update(id, fornecedor));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta um fornecedor", notes = "Deleta um fornecedor por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Fornecedor deletado"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                fornecedorService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}
