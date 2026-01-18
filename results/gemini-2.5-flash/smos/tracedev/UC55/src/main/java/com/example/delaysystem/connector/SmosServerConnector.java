package com.example.delaysystem.connector;

import com.example.delaysystem.model.DelayData;
import java.util.List;

/**
 * Simulates a connector to an external SMOS (Student Management & Operations System) server.
 * Provides methods to send data and check connection status.
 */
public class SmosServerConnector {
    // A flag to simulate connection failures for testing purposes.
    private static boolean simulateConnectionFailure = false;

    /**
     * Constructs a new SmosServerConnector.
     */
    public SmosServerConnector() {
        System.out.println("[SMOSConnector] Initialized.");
    }

    /**
     * Sends a list of DelayData objects to the simulated SMOS server.
     * This method simulates network communication and potential failures.
     * Implements the "SMOS Connector Interaction" part of the sequence diagram.
     *
     * @param data The list of DelayData to send.
     * @return true if data transmission was successful, false if a simulated error occurred.
     */
    public boolean sendDelayData(List<DelayData> data) {
        if (simulateConnectionFailure) {
            System.err.println("[SMOSConnector] ERROR: Simulating connection interruption. Data not sent.");
            return false;
        }

        System.out.println("[SMOSConnector] Sending " + data.size() + " DelayData entries to SMOS server...");
        // Simulate network delay and processing.
        try {
            Thread.sleep(200); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[SMOSConnector] Thread interrupted during send operation.");
            return false;
        }
        System.out.println("[SMOSConnector] Data sent successfully to SMOS server.");
        return true; // Simulate successful transmission
    }

    /**
     * Checks if the connector is currently connected to the SMOS server.
     * For this simulation, it always returns true unless connection failure is simulated.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        if (simulateConnectionFailure) {
            System.out.println("[SMOSConnector] Connection status: Disconnected (simulated failure).");
            return false;
        }
        System.out.println("[SMOSConnector] Connection status: Connected.");
        return true; // Always connected in this simulation unless failure is active
    }

    /**
     * Static method to enable or disable simulation of connection failure.
     * This is useful for testing the error path in the sequence diagram.
     *
     * @param failure True to simulate connection failure, false otherwise.
     */
    public static void setSimulateConnectionFailure(boolean failure) {
        simulateConnectionFailure = failure;
        System.out.println("[SMOSConnector] Connection failure simulation set to: " + failure);
    }
}