package com.example.touristapp.infrastructure;

/**
 * Custom exception for repository-related errors,
 * particularly for issues connecting to external persistence systems like ETOUR Server.
 */
public class RepositoryException extends Exception {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}