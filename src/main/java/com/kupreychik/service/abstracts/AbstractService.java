package com.kupreychik.service.abstracts;

import com.kupreychik.mapper.AbstractMapper;
import com.kupreychik.model.dto.IsDTO;
import com.kupreychik.model.entity.HasId;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T extends HasId, F> {

    private JpaRepository<HasId<? extends Number>, ? extends Number> repository;
    private AbstractMapper<HasId<? extends Number>, IsDTO> mapper;

    protected AbstractService(JpaRepository repository, AbstractMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
