package com.kupreychik.service.abstracts;

import com.kupreychik.model.dto.paginig.PageDTO;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface BasicService<F, T, ID extends Serializable> {

    T save(F t);

    void delete(ID id);

    F findById(ID id);

    PageDTO<F> findAll(Pageable pageable);
}
