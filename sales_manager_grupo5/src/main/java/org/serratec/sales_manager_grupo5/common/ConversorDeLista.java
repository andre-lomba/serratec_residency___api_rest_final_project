package org.serratec.sales_manager_grupo5.common;

import java.util.List;

import org.serratec.sales_manager_grupo5.dto.CategoriaDTO;
import org.serratec.sales_manager_grupo5.dto.FornecedorDTO;
import org.serratec.sales_manager_grupo5.dto.PedidoDTO;
import org.serratec.sales_manager_grupo5.dto.ProdutoDTO;
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
    public static final Page<ProdutoDTO> convertListProdutoDTOToPage(List<ProdutoDTO> list, Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<ProdutoDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

    /**
     * Converte uma lista de PedidoDTO em Page
     * 
     * @param list
     * @param pageable
     * @return page
     */
    public static final Page<PedidoDTO> convertListPedidoDTOToPage(List<PedidoDTO> list, Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<PedidoDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

    /**
     * Converte uma lista de FornecedorDTO em Page
     * 
     * @param list
     * @param pageable
     * @return page
     */
    public static final Page<FornecedorDTO> convertListFornecedorDTOToPage(List<FornecedorDTO> list,
            Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<FornecedorDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

    /**
     * Converte uma lista de CategoriaDTO em Page
     * 
     * @param list
     * @param pageable
     * @return page
     */
    public static final Page<CategoriaDTO> convertListCategoriaDTOToPage(List<CategoriaDTO> list,
            Pageable pageable) {
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<CategoriaDTO> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
        return page;
    }

}
