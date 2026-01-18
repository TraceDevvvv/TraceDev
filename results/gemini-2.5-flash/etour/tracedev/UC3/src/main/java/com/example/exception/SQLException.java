package com.example.exception;

/**
 * Custom SQLException class.
 * This is used as a placeholder for the generic SQLException mentioned in the diagram,
 * to distinguish from java.sql.SQLException.
 * For this context, it will represent database-related errors in the Repository layer.
 */
public class SQLException extends Exception {
    private static final long serialVersionUID = 1L;

    public SQLException(String message) {
        super(message);
    }

    public SQLException(String message, Throwable cause) {
        super(message, cause);
    }
}