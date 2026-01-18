package com.example.exceptions;

/**
 * Custom exception for issues encountered within the application's service layer.
 * Part of the Application Layer's error handling.
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}