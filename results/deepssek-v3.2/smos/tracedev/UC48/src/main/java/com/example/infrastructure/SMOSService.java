package com.example.infrastructure;

/**
 * Mock service for SMOS server communication.
 */
public class SMOSService {
    // Simulated connection state
    private boolean connected = true;
    private Connection serverConnection;

    public boolean deleteReportCard(String reportCardId) {
        // Simulate server call
        if (isConnected()) {
            // Assume deletion always succeeds when connected
            return true;
        }
        return false;
    }

    public boolean isConnected() {
        return connected;
    }

    public void disconnect() {
        // Simulate disconnection
        connected = false;
    }

    public Connection getServerConnection() {
        return serverConnection;
    }

    public void setServerConnection(Connection serverConnection) {
        this.serverConnection = serverConnection;
    }
}