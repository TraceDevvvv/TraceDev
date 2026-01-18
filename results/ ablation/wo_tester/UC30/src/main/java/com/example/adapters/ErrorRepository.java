package com.example.adapters;

/**
 * Repository for logging errors.
 */
public interface ErrorRepository {
    void logError(String errorType, String details);
}