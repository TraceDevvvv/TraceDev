package com.example.refreshmentpoint;

/**
 * Custom exception to represent a failure in connecting to the ETOUR server.
 * This is used to satisfy requirement XC3.
 */
public class ETOURConnectionException extends Exception {
    public ETOURConnectionException(String message) {
        super(message);
    }
}