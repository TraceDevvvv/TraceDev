package com.example.connection;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles connection state and interruption detection.
 */
public class ConnectionHandler {
    private Map<String, Object> sessionData;
    private boolean isConnected;

    /**
     * Constructor.
     */
    public ConnectionHandler() {
        sessionData = new HashMap<>();
        isConnected = true; // Assume initially connected
    }

    /**
     * Detect if an interruption occurred.
     */
    public boolean detectInterruption() {
        // Simulate detection: here we assume interruption occurs randomly
        // In a real application, this would monitor network status or heartbeat
        double random = Math.random();
        if (random < 0.1) { // 10% chance of interruption for simulation
            isConnected = false;
            notifyInterruption();
            return true;
        }
        return false;
    }

    /**
     * Clear session data.
     */
    public void clearSession() {
        sessionData.clear();
        System.out.println("Session cleared.");
    }

    /**
     * Notify about interruption (could be used to inform other components).
     */
    public void notifyInterruption() {
        System.out.println("Connection interruption notified.");
    }
}