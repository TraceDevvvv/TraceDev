package com.activeconvention.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple Logger utility for logging messages to console.
 * This class provides static methods for logging informational and error messages
 * with a timestamp, aiding in debugging and monitoring.
 */
public class Logger {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs an informational message to the console.
     *
     * @param message The informational message to log.
     */
    public static void logInfo(String message) {
        System.out.println(LocalDateTime.now().format(formatter) + " [INFO] " + message);
    }

    /**
     * Logs an error message to the console, optionally including a Throwable's stack trace.
     *
     * @param message The error message to log.
     * @param t       The Throwable (e.g., Exception) that caused the error, can be null.
     */
    public static void logError(String message, Throwable t) {
        System.err.println(LocalDateTime.now().format(formatter) + " [ERROR] " + message);
        if (t != null) {
            t.printStackTrace(System.err);
        }
    }
}