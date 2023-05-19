package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import org.serratec.sales_manager_grupo5.dto.CategoriaDTO;
import org.serratec.sales_manager_grupo5.model.Categoria;
import org.serratec.sales_manager_grupo5.service.CategoriaService;
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
@RequestMapping("/api/categorias")
public class CategoriaController {

        @Autowired
        CategoriaService categoriaService;

        @PostMapping
        @ApiOperation(value = "Adiciona uma categoria", notes = "Adiciona uma categoria")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Cria a categoria e retorna ela no corpo"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<CategoriaDTO> create(@RequestBody Categoria categoria) {
                CategoriaDTO categoriaDTO = categoriaService.create(categoria);
                URI uri = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(categoriaDTO.getId())
                                .toUri();
                return ResponseEntity.created(uri).body(categoriaDTO);
        }

        @GetMapping
        @ApiOperation(value = "Lista 10 categorias por página", notes = "Listagem de categorias")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna todas as categorias"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Page<CategoriaDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(categoriaService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca uma categoria", notes = "Busca uma categoria por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna a categoria correspondente ao id"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(categoriaService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza uma categoria", notes = "Atualiza uma categoria por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna a categoria atualizada"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody Categoria categoria) {
                return ResponseEntity.ok(categoriaService.update(id, categoria));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta um categoria", notes = "Deleta uma categoria por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Categoria deletada"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                categoriaService.deleteById(id);
                return ResponseEntity.ok().build();
        }
}
