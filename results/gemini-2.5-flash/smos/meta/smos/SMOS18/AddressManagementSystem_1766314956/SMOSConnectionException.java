package com.address_management_system;

/**
 * Custom exception class for handling errors related to SMOS server connections.
 * This exception is thrown when there's an issue connecting to or communicating with the SMOS server.
 */
public class SMOSConnectionException extends RuntimeException {

    /**
     * Constructs a new SMOSConnectionException with no detail message.
     */
    public SMOSConnectionException() {
        super();
    }

    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SMOSConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new SMOSConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SMOSConnectionException with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     *
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSConnectionException(Throwable cause) {
        super(cause);
    }
}