package com.example.adapters;

/**
 * Simple error repository that logs to console.
 */
public class ConsoleErrorRepository implements ErrorRepository {
    @Override
    public void logError(String errorType, String details) {
        System.err.println("ERROR [" + errorType + "]: " + details);
    }
}