package org.serratec.sales_manager_grupo5.repository;

import java.util.Optional;

import org.serratec.sales_manager_grupo5.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findAll(Pageable pageable);

    Optional<Categoria> findByNomeIgnoreCase(String nome);
}
