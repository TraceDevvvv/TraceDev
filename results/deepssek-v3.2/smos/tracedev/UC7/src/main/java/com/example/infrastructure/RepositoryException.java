package com.example.infrastructure;

/**
 * General repository exception.
 */
public class RepositoryException extends Exception {
    private String message;

    public RepositoryException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}