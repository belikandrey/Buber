package com.epam.jwd.exception;

public class ValidationException extends Exception{
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
