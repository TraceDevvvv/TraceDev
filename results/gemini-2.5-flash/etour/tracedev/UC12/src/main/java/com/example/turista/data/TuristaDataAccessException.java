package com.example.turista.data;

/**
 * Custom exception for data access layer failures related to Turista data.
 * This exception indicates issues like connection problems with the ETOUR server
 * or data not being found at the persistence level.
 */
public class TuristaDataAccessException extends Exception {
    public TuristaDataAccessException(String message) {
        super(message);
    }

    public TuristaDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}