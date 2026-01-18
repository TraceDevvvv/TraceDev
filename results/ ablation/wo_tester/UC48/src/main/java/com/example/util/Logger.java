package com.example.util;

/**
 * Utility class for logging.
 */
public class Logger {
    public void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }
    
    public void logError(String message) {
        System.out.println("[ERROR] " + message);
    }
}