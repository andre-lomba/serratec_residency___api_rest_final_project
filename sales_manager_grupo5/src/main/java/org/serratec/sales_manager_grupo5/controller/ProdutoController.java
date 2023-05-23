package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseDTO;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

        @Autowired
        ProdutoService produtoService;

        @PostMapping
        @ApiOperation(value = "Adiciona um produto", notes = "Adiciona um produto")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Cria o produto e retorna ele no corpo"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoRequestDTO produto) {
                ProdutoResponseDTO produtoDTO = produtoService.create(produto);
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
                        @ApiResponse(code = 200, message = "Retorna todos os produtos"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Page<ProdutoResponseDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(produtoService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca um produto", notes = "Busca um produto por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna o produto correspondente ao id"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(produtoService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza um produto", notes = "Atualiza um produto por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna o produto atualizado"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id,
                        @RequestBody ProdutoRequestDTO produtoDTO) {
                return ResponseEntity.ok(produtoService.update(id, produtoDTO));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta um produto", notes = "Deleta um produto por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Produto deletado"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                produtoService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}
