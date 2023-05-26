package org.serratec.sales_manager_grupo5.repository;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Page<Fornecedor> findAll(Pageable pageable);

    Optional<Fornecedor> findByNomeIgnoreCase(String nome);

    Optional<Fornecedor> findByCnpjIgnoreCase(String cnpj);
}
