package com.example.exceptions;

/**
 * Exception thrown by the application service layer, encapsulating lower-level exceptions.
 */
public class ApplicationServiceException extends Exception {
    public ApplicationServiceException(String message) {
        super(message);
    }

    public ApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}