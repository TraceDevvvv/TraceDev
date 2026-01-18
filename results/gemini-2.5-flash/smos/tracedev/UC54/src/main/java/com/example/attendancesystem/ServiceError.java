package com.example.attendancesystem;

/**
 * Custom exception for errors occurring in the service layer.
 * This class is added to satisfy R16 from the class diagram.
 */
public class ServiceError extends Exception {
    /**
     * Constructs a new ServiceError with the specified detail message.
     * @param message the detail message.
     */
    public ServiceError(String message) {
        super(message);
    }

    /**
     * Constructs a new ServiceError with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ServiceError(String message, Throwable cause) {
        super(message, cause);
    }
}