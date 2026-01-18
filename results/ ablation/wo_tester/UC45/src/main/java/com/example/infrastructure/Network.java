package com.example.infrastructure;

/**
 * Simulates network connectivity monitoring.
 * Added to satisfy requirement: Exit Condition "The connection to the server is interrupted."
 */
public class Network {
    private ConnectionListener connectionListener;

    public interface ConnectionListener {
        void onConnectionLost();
    }

    public Network() {
        // Simulate network monitoring startup
    }

    public void setConnectionListener(ConnectionListener listener) {
        this.connectionListener = listener;
        // In a real application, this would start listening for network events
    }

    public boolean checkConnection() {
        // Simulate connection check; for simplicity assume always connected
        return true;
    }

    // Added to satisfy requirement: Exit Condition "The connection to the server is interrupted."
    public void notifyConnectionLost() {
        if (connectionListener != null) {
            connectionListener.onConnectionLost();
        }
    }

    // Helper method to simulate connection loss (for testing)
    public void simulateConnectionLoss() {
        notifyConnectionLost();
    }
}