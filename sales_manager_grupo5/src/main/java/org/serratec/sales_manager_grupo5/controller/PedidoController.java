package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import javax.validation.Valid;

import org.serratec.sales_manager_grupo5.dto.pedidoDTO.PedidoRequestDTO;
import org.serratec.sales_manager_grupo5.dto.pedidoDTO.PedidoResponseDTO;
import org.serratec.sales_manager_grupo5.service.PedidoService;
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
@RequestMapping("/api/pedidos")
@Api(tags = "PedidoController", description = "Endpoints relacionados a Pedido")
public class PedidoController {

        @Autowired
        PedidoService pedidoService;

        @PostMapping
        @ApiOperation(value = "Adiciona um pedido", notes = "Insira um pedido com os campos 'dataEmissao', 'id_fornecedor' e a lista 'produtos' - cada elemetno dessa lista deve conter 'id_produto' e 'quantidade'.")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Created"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<PedidoResponseDTO> create(@Valid @RequestBody PedidoRequestDTO pedido) {
                PedidoResponseDTO pedidoDTO = pedidoService.create(pedido);
                URI uri = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(pedidoDTO.getId())
                                .toUri();
                return ResponseEntity.created(uri).body(pedidoDTO);
        }

        @GetMapping
        @ApiOperation(value = "Lista 10 pedidos por página", notes = "Listagem de pedidos")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Page<PedidoResponseDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(pedidoService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca um pedido pelo ID", notes = "Busca um pedido por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(pedidoService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza um pedido pelo ID", notes = "Atualize um pedido com os campos 'dataEmissao', 'id_fornecedor' e a lista 'produtos' - cada elemetno dessa lista deve coter os campos 'id_produto' e 'quantidade'.")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<PedidoResponseDTO> update(@PathVariable Long id,
                        @Valid @RequestBody PedidoRequestDTO pedido) {
                return ResponseEntity.ok(pedidoService.update(id, pedido));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta um pedido pelo ID", notes = "Deleta um pedido por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK"),
                        @ApiResponse(code = 400, message = "Bad Request"),
                        @ApiResponse(code = 401, message = "Unauthorized"),
                        @ApiResponse(code = 403, message = "Forbidden"),
                        @ApiResponse(code = 404, message = "Not Found"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                pedidoService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}
