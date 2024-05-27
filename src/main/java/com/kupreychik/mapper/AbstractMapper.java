package com.kupreychik.mapper;

public interface AbstractMapper<F, T> {
    F fromDto(T t);

    T fromEntity(F t);
}
