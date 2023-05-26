package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import javax.validation.Valid;

import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaRequestDTO;
import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaResponseProdutosDTO;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categorias")
@Api(tags = "CategoriaController", description = "Endpoints relacionados a Categoria")
public class CategoriaController {

        @Autowired
        CategoriaService categoriaService;

        @PostMapping
        @ApiOperation(value = "Adiciona uma categoria", notes = "Adiciona uma categoria")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Created"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<CategoriaResponseProdutosDTO> create(@Valid @RequestBody CategoriaRequestDTO categoria) {
                CategoriaResponseProdutosDTO categoriaDTO = categoriaService.create(categoria);
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
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Page<CategoriaResponseProdutosDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(categoriaService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca uma categoria pelo ID", notes = "Busca uma categoria por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<CategoriaResponseProdutosDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(categoriaService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza uma categoria pelo ID", notes = "Atualiza uma categoria por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<CategoriaResponseProdutosDTO> update(@PathVariable Long id,
                        @Valid @RequestBody CategoriaRequestDTO categoria) {
                return ResponseEntity.ok(categoriaService.update(id, categoria));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta uma categoria pelo ID", notes = "Deleta uma categoria por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                categoriaService.deleteById(id);
                return ResponseEntity.ok().build();
        }
}
