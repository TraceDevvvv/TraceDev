'''
Custom exception class to represent an interruption in connecting
to the simulated ETOUR server.
'''
package com.chatdev.viscon; // Using a package for better organization
/**
 * Custom exception signaling an interruption in the connection to the ETOUR server.
 * This can be used to handle specific network or service availability issues.
 */
public class ConnectionInterruptionException extends Exception {
    /**
     * Constructs a new ConnectionInterruptionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionInterruptionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ConnectionInterruptionException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionInterruptionException(String message, Throwable cause) {
        super(message, cause);
    }
}