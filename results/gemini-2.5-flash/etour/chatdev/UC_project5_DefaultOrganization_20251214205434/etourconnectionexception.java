'''
Custom exception class to represent an interruption in connection
to the simulated ETOUR server. This helps in handling specific error
conditions related to data retrieval.
'''
package com.chatdev.culturalviewer.service;
/**
 * '''
 * Custom exception class to represent an interruption in connection
 * to the simulated ETOUR server. This helps in handling specific error
 * conditions related to data retrieval.
 * '''
 */
public class ETOURConnectionException extends Exception {
    /**
     * '''
     * Constructs a new ETOURConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * '''
     */
    public ETOURConnectionException(String message) {
        super(message);
    }
    /**
     * '''
     * Constructs a new ETOURConnectionException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *             (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     * '''
     */
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}