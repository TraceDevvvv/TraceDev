package com.example.service;

/**
 * Interface for logging service.
 */
public interface LoggingService {
    /**
     * Logs a message.
     * @param message the message to log
     */
    void log(String message);

    /**
     * Enables logging.
     */
    void enable();
}