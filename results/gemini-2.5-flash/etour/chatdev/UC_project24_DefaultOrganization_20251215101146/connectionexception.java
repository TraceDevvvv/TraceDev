/*
 * Custom exception to represent network or data access connection issues
 * in the service layer, promoting consistent error handling.
 */
package com.chatdev.exception;
/**
 * Custom exception to represent network or data access connection issues
 * in the service layer, promoting consistent error handling.
 */
public class ConnectionException extends Exception {
    /**
     * Constructs a new ConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ConnectionException with the specified detail message and cause.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause The cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}