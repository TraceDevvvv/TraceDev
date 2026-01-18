package com.example.repository;

/**
 * Exception thrown when there is a data access error.
 */
public class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }
}