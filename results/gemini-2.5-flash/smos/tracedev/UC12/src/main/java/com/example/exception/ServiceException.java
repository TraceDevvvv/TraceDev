package com.example.exception;

/**
 * Custom exception for service layer errors,
 * typically indicating a business logic failure or an issue propagating from lower layers.
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}