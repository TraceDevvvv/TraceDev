package com.example.infrastructure;

/**
 * Exception for SMOS server connection failures.
 * Extends RepositoryException as per class diagram.
 */
public class SMOSConnectionException extends RepositoryException {
    public SMOSConnectionException(String message) {
        super(message);
    }
}