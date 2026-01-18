package com.example.exception;

/**
 * Custom exception to represent ETOUR server connection interruption.
 */
public class ETOURException extends Exception {
    public ETOURException(String message) {
        super(message);
    }
}