package com.example.search;

/**
 * Custom runtime exception to represent connection interruptions or errors.
 * This directly maps to the 'ConnectionError' class in the UML diagram.
 * Added to satisfy requirement R10.
 */
public class ConnectionError extends RuntimeException {
    // Public field as specified in the UML diagram
    public String message;

    /**
     * Constructor for ConnectionError.
     * @param message A descriptive message about the connection error.
     */
    public ConnectionError(String message) {
        super(message); // Call to super constructor of RuntimeException
        this.message = message;
    }
}