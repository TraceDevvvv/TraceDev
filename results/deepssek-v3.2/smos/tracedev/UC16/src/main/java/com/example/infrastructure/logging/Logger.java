package com.example.infrastructure.logging;

/**
 * Logger for information and error messages.
 */
public class Logger {
    private String logLevel;

    public Logger(String logLevel) {
        this.logLevel = logLevel;
    }

    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public void logError(String message, Exception exception) {
        System.err.println("[ERROR] " + message);
        if (exception != null) {
            exception.printStackTrace();
        }
    }
}