package com.example.exceptions;

/**
 * Custom exception for service layer operations.
 * Extends standard Java Exception.
 */
public class ServiceException extends Exception {
    /**
     * Constructs a new ServiceException with the specified detail message.
     * @param message the detail message.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new ServiceException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}