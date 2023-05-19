package org.serratec.sales_manager_grupo5.controller;

import java.net.URI;

import org.serratec.sales_manager_grupo5.dto.PedidoDTO;
import org.serratec.sales_manager_grupo5.model.Pedido;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

        @Autowired
        PedidoService pedidoService;

        @PostMapping
        @ApiOperation(value = "Adiciona um pedido", notes = "Adiciona um pedido")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Cria o pedido e retorna ele no corpo"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<PedidoDTO> create(@RequestBody Pedido pedido) {
                PedidoDTO pedidoDTO = pedidoService.create(pedido);
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
                        @ApiResponse(code = 200, message = "Retorna todos os pedidos"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Page<PedidoDTO>> findAll(
                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
                return ResponseEntity.ok(pedidoService.findAll(page));
        }

        @GetMapping("/{id}")
        @ApiOperation(value = "Busca um pedido", notes = "Busca um pedido por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna o pedido correspondente ao id"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
                return ResponseEntity.ok(pedidoService.findById(id));
        }

        @PutMapping("/{id}")
        @ApiOperation(value = "Atualiza um pedido", notes = "Atualiza um pedido por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Retorna o pedido atualizado"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody Pedido pedido) {
                return ResponseEntity.ok(pedidoService.update(id, pedido));
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value = "Deleta um pedido", notes = "Deleta um pedido por id")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Pedido deletado"),
                        @ApiResponse(code = 401, message = "Erro de autenticação"),
                        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
                        @ApiResponse(code = 404, message = "Recurso não encontrado"),
                        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
        })
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
                pedidoService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}
