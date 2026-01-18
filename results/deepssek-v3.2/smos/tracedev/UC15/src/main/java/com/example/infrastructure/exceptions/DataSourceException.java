package com.example.infrastructure.exceptions;

/**
 * Exception thrown when there is a data source error, such as a connection failure.
 */
public class DataSourceException extends RuntimeException {
    private String message;

    /**
     * Constructor for DataSourceException.
     * @param message The error message.
     */
    public DataSourceException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}