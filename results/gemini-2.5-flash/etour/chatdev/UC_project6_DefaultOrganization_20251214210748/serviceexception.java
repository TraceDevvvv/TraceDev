/**
 * Custom exception class used to signal errors encountered during service operations,
 * such as simulated database or network interruptions.
 */
package com.chatdev.service;
/**
 * Custom exception class used to signal errors encountered during service operations,
 * such as simulated database or network interruptions.
 */
public class ServiceException extends Exception {
    /**
     * Constructs a new ServiceException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ServiceException(String message) {
        super(message);
    }
    /**
     * Constructs a new ServiceException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}