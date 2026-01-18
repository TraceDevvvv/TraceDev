
package com.etoour.utility;

/**
 * Handles errors and logs them.
 */
public class ErrorHandler {
    private ConnectionManager connectionManager;

    public ErrorHandler(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void handleInvalidData(String errorMessage) {
        System.err.println("Invalid data error: " + errorMessage);
        // Could also log to a file or monitoring system.
    }

    public void logError(Exception error) {
        System.err.println("Logged error: " + error.getMessage());
        error.printStackTrace();
    }

    public void handleServerConnectionError() {
        System.err.println("Server connection error occurred.");
        // Removed reconnect call as ConnectionManager doesn't have this method
    }

    /**
     * Sequence diagram message: error logged (m25).
     */
    public void logErrorWithMessage(String message) {
        System.err.println("Error logged: " + message);
    }

    /**
     * Sequence diagram message: error logged (m30).
     */
    public void logErrorToController(String message) {
        System.err.println("Error logged (to controller): " + message);
    }
}
