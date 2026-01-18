package com.example.logging;

import java.util.ArrayList;
import java.util.List;

/**
 * Logs informational and error messages.
 */
public class Logger {
    private List<String> logEntries;

    public Logger() {
        this.logEntries = new ArrayList<>();
    }

    public void logInfo(String message) {
        String entry = "INFO: " + message;
        logEntries.add(entry);
        System.out.println(entry);
    }

    public void logError(String message) {
        String entry = "ERROR: " + message;
        logEntries.add(entry);
        System.err.println(entry);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logEntries);
    }
}