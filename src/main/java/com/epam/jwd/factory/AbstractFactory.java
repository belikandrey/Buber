package com.epam.jwd.factory;

import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.FactoryException;

public interface AbstractFactory<T extends Entity> {
    T create(Object... args) throws FactoryException;
}
