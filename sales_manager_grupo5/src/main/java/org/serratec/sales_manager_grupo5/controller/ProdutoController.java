package org.serratec.sales_manager_grupo5.controller;

import org.serratec.sales_manager_grupo5.model.Produto;
import org.serratec.sales_manager_grupo5.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    @ApiOperation(value = "Lista 10 produtos por página", notes = "Listagem de produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os produtos"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
    })

    public ResponseEntity<Page<Produto>> findAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10, page = 0) Pageable page) {
        return ResponseEntity.ok(produtoService.findAll(page));
    }

}
