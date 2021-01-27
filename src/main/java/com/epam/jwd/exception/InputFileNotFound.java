package com.epam.jwd.exception;

public class InputFileNotFound extends RuntimeException{
    private String path;

    public InputFileNotFound(String path) {
        this.path = path;
    }

    public InputFileNotFound(String message, String path) {
        super(message);
        this.path = path;
    }
}
