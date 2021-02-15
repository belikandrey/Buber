package com.epam.jwd.dao;

import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface AbstractDao<K, T extends Entity> {

    List<T> findAll() throws DaoException;

    Optional<T> findEntityById(K id) throws DaoException;

    boolean update(T entity) throws DaoException;

    boolean remove(T entity) throws DaoException;

    boolean add(T entity) throws DaoException;
}
