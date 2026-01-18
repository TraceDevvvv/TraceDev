package com.culturalheritage.exception;

/**
 * Custom exception for when a cultural heritage object is not found.
 * This exception is thrown when an attempt is made to retrieve or operate on
 * a CulturalHeritageObject that does not exist in the data store.
 */
public class CulturalHeritageNotFoundException extends RuntimeException {

    /**
     * Constructs a new CulturalHeritageNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public CulturalHeritageNotFoundException(String message) {
        super(message);
    }
}