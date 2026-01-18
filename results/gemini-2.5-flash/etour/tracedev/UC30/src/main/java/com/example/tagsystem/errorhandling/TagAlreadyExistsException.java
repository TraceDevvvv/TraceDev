package com.example.tagsystem.errorhandling;

/**
 * Custom exception indicating that a tag with the specified name already exists.
 * This is a runtime exception, meaning it does not need to be explicitly declared in method signatures (unchecked).
 */
public class TagAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new TagAlreadyExistsException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public TagAlreadyExistsException(String message) {
        super(message);
    }
}