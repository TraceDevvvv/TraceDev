package com.example.business;

/**
 * Exception thrown when an operator tries to access a site without permission.
 * Used in the alternative flow of the Sequence Diagram.
 */
public class AccessDeniedException extends Exception {
    public AccessDeniedException(String message) {
        super(message);
    }
}