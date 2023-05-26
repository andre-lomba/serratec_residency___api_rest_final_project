package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import javax.validation.Valid;

import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorRequestDTO;
import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorResponsePedidosDTO;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/fornecedores")
@Api(tags = "FornecedorController", description = "Endpoints relacionados a Fornecedor")
public class FornecedorController {

        @Autowired
        FornecedorService fornecedorService;

        @PostMapping
        @ApiOperation(value = "Adiciona um fornecedor", notes = "Adiciona um fornecedor")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Created"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<FornecedorResponsePedidosDTO> create(
                        @Valid @RequestBody FornecedorRequestDTO fornecedor) {
                FornecedorResponsePedidosDTO fornecedorDTO = fornecedorService.create(fornecedor);
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
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Page<FornecedorResponsePedidosDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(fornecedorService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca um fornecedor pelo ID", notes = "Busca um fornecedor por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<FornecedorResponsePedidosDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(fornecedorService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza um fornecedor pelo ID", notes = "Atualiza um fornecedor por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<FornecedorResponsePedidosDTO> update(@PathVariable Long id,
                        @Valid @RequestBody FornecedorRequestDTO fornecedor) {
                return ResponseEntity.ok(fornecedorService.update(id, fornecedor));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta um fornecedor pelo ID", notes = "Deleta um fornecedor por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                fornecedorService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}
