package com.example;

/**
 * Custom exception to indicate a failure in connecting to a data store
 * or external service, satisfying requirement REQ-10.
 * It extends RuntimeException so it can be unchecked, simplifying error propagation
 * in certain layers, but still allowing specific catching.
 */
public class ConnectionFailedException extends RuntimeException {

    /**
     * Constructs a new ConnectionFailedException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionFailedException(String message) {
        super(message);
    }
}