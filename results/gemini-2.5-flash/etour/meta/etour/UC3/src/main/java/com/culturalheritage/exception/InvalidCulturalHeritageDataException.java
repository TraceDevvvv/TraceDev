package com.culturalheritage.exception;

/**
 * Custom exception for invalid cultural heritage object data.
 * This exception is thrown when validation rules for a CulturalHeritageObject are violated.
 */
public class InvalidCulturalHeritageDataException extends RuntimeException {

    /**
     * Constructs a new InvalidCulturalHeritageDataException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidCulturalHeritageDataException(String message) {
        super(message);
    }
}