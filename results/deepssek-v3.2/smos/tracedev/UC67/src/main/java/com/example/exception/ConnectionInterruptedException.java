package com.example.exception;

/**
 * Exception class for traceability of connection interruptions
 * As specified in class diagram
 */
public class ConnectionInterruptedException extends RuntimeException {
    
    public ConnectionInterruptedException() {
        super();
    }
    
    public ConnectionInterruptedException(String message) {
        super(message);
    }
    
    public ConnectionInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}