'''
Custom exception to simulate an interruption of the connection to the ETOUR server.
This exception is used to represent network or server-side issues during data retrieval.
'''
package com.chatdev.ricercapuntidiristoro;
/**
 * Custom exception to simulate an interruption of the connection to the ETOUR server.
 * This exception is used to represent network or server-side issues during data retrieval.
 */
public class ETOURConnectionException extends Exception {
    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new ETOURConnectionException with the specified detail message and cause.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *             (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}