package com.example.handler;

/**
 * Handles connection to the external ETOUR system.
 * Manages connection checks and interruption handling.
 */
public class ConnectionHandler {
    /**
     * Checks the ETOUR connection status.
     * Simulates connection check.
     *
     * @return true if connection is available, false otherwise
     */
    public boolean checkETOURConnection() {
        // Simulate connection check; for demo, assume 90% availability.
        // In real scenario, would ping the ETOUR server.
        return Math.random() > 0.1; // 90% chance of true
    }

    /**
     * Handles connection interruption.
     * Logs or performs recovery actions.
     */
    public void handleInterruption() {
        System.out.println("ConnectionHandler: Handling ETOUR connection interruption.");
        // Could implement retry logic, notify user, etc.
    }

    /**
     * Called when an interruption occurs.
     * Similar to handleInterruption, but may be used for callback.
     */
    public void onInterruption() {
        System.out.println("ConnectionHandler: onInterruption called.");
        handleInterruption();
    }

    /**
     * Returns connection status.
     *
     * @return connection status string
     */
    public String connectionStatus() {
        boolean status = checkETOURConnection();
        return status ? "Connected" : "Disconnected";
    }

    /**
     * Returns interruption handled message.
     *
     * @return interruption handled message
     */
    public String interruptionHandled() {
        handleInterruption();
        return "interruption handled";
    }
}