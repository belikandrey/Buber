package com.epam.jwd.dto.converter;

public interface Converter<T, K> {
    T toEntity(K dto);
    K toDto(T entity);
}
