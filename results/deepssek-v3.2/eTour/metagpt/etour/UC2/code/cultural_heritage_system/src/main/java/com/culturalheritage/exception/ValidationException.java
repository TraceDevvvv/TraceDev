package com.culturalheritage.exception;

/**
 * Custom exception thrown when data validation fails during the insertion process.
 * This exception is part of the "Errored" use case mentioned in the requirements,
 * which is activated when data is invalid or insufficient.
 */
public class ValidationException extends RuntimeException {
    
    /**
     * Constructs a new ValidationException with the specified detail message.
     * 
     * @param message The detail message explaining what validation rule was violated
     *                and which field(s) are invalid or insufficient
     */
    public ValidationException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ValidationException with the specified detail message and cause.
     * 
     * @param message The detail message explaining the validation failure
     * @param cause The underlying cause of this exception
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}