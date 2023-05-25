package org.serratec.sales_manager_grupo5.common;

import java.util.List;

import org.serratec.sales_manager_grupo5.dto.categoriaDTO.CategoriaResponseProdutosDTO;
import org.serratec.sales_manager_grupo5.dto.fornecedorDTO.FornecedorResponsePedidosDTO;
import org.serratec.sales_manager_grupo5.dto.pedidoDTO.PedidoResponseDTO;
import org.serratec.sales_manager_grupo5.dto.produtoDTO.ProdutoResponseCategoriasDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Classe para conversão de Listas
 */
public class ConversorDeLista {

    /**
     * Converte uma lista de ProdutoDTO em Page
     * 
     * @param list
     * @param pageable
     * @return page
     */
    public static final Page<ProdutoResponseCategoriasDTO> convertListProdutoDTOToPage(
            List<ProdutoResponseCategoriasDTO> list,
            Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<ProdutoResponseCategoriasDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

    /**
     * Converte uma lista de PedidoDTO em Page
     * 
     * @param list
     * @param pageable
     * @return page
     */
    public static final Page<PedidoResponseDTO> convertListPedidoDTOToPage(List<PedidoResponseDTO> list,
            Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<PedidoResponseDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

    /**
     * Converte uma lista de FornecedorDTO em Page
     * 
     * @param list
     * @param pageable
     * @return page
     */
    public static final Page<FornecedorResponsePedidosDTO> convertListFornecedorDTOToPage(
            List<FornecedorResponsePedidosDTO> list,
            Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<FornecedorResponsePedidosDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

    /**
     * Converte uma lista de CategoriaDTO em Page
     * 
     * @param list
     * @param pageable
     * @return page
     */
    public static final Page<CategoriaResponseProdutosDTO> convertListCategoriaDTOToPage(
            List<CategoriaResponseProdutosDTO> list,
            Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<CategoriaResponseProdutosDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

}
