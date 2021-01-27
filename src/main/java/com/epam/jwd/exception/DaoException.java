package com.epam.jwd.exception;

public class DaoException extends Exception{
    public DaoException() {
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }
}
