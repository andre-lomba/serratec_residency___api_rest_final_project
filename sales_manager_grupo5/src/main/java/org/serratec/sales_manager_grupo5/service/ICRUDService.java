package org.serratec.sales_manager_grupo5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICRUDService<objRequest, objResponse> {

    objResponse create(objRequest obj);

    Page<objResponse> findAll(Pageable page);

    objResponse findById(Long id);

    objResponse update(Long id, objRequest obj);

    void deleteById(Long id);

}
