package com.example;

/**
 * Interface for logging messages.
 * Provides methods for different log levels: info, warn, and error.
 */
public interface Logger {

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    void info(String message);

    /**
     * Logs a warning message.
     *
     * @param message The message to log.
     */
    void warn(String message);

    /**
     * Logs an error message.
     *
     * @param message The message to log.
     */
    void error(String message);
}