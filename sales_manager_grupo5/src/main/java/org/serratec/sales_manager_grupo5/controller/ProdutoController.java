package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import javax.validation.Valid;

import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseCategoriasDTO;
import org.serratec.sales_manager_grupo5.service.ProdutoService;
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
@RequestMapping("/api/produtos")
@Api(tags = "ProdutoController", description = "Endpoints relacionados a Produto")
public class ProdutoController {

        @Autowired
        ProdutoService produtoService;

        @PostMapping
        @ApiOperation(value = "Adiciona um produto", notes = "Adiciona um produto")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Created"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<ProdutoResponseCategoriasDTO> create(@Valid @RequestBody ProdutoRequestDTO produto) {
                ProdutoResponseCategoriasDTO produtoDTO = produtoService.create(produto);
                URI uri = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(produtoDTO.getId())
                                .toUri();
                return ResponseEntity.created(uri).body(produtoDTO);
        }

        @GetMapping
        @ApiOperation(value = "Lista 10 produtos por página", notes = "Listagem de produtos")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Page<ProdutoResponseCategoriasDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(produtoService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca um produto pelo ID", notes = "Busca um produto por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<ProdutoResponseCategoriasDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(produtoService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza um produto pelo ID", notes = "Atualiza um produto por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<ProdutoResponseCategoriasDTO> update(@PathVariable Long id,
                        @Valid @RequestBody ProdutoRequestDTO produtoDTO) {
                return ResponseEntity.ok(produtoService.update(id, produtoDTO));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta um produto pelo ID", notes = "Deleta um produto por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                produtoService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}
