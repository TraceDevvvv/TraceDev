package com.example.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles and logs errors.
 */
public class ErrorHandler {
    private List<String> errorLog;

    public ErrorHandler() {
        this.errorLog = new ArrayList<>();
    }

    public void handleValidationError(String error) {
        String logEntry = "Validation Error: " + error;
        errorLog.add(logEntry);
    }

    public void handleConnectionError() {
        String logEntry = "Connection Error: Lost connection to database.";
        errorLog.add(logEntry);
    }

    public List<String> getErrorLog() {
        return new ArrayList<>(errorLog);
    }
}