package com.epam.jwd.validator;

public interface Validator<T>{
    boolean validate(T t);
}
