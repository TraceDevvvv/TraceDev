package com.culturalheritage.exception;

/**
 * Custom exception thrown when a duplicate cultural heritage is detected
 * during the insertion process. This exception is part of the quality
 * requirement that prevents duplicate cultural heritage entries in the system.
 */
public class DuplicateHeritageException extends RuntimeException {
    
    /**
     * Constructs a new DuplicateHeritageException with the specified detail message.
     * 
     * @param message The detail message explaining which cultural heritage
     *                is a duplicate and why it cannot be inserted
     */
    public DuplicateHeritageException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new DuplicateHeritageException with the specified detail message
     * and cause.
     * 
     * @param message The detail message explaining the duplicate detection
     * @param cause The underlying cause of this exception
     */
    public DuplicateHeritageException(String message, Throwable cause) {
        super(message, cause);
    }
}