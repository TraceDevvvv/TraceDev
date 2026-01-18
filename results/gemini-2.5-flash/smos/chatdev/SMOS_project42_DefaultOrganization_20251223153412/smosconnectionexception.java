/*
Custom exception class to specifically handle errors related to SMOS server connection.
This allows for more specific error handling in the application logic.
*/
package com.chatdev.eliminatejustification.exceptions;
public class SMOSConnectionException extends Exception {
    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public SMOSConnectionException(String message) {
        super(message);
    }
    /**
     * Constructs a new SMOSConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *             (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}