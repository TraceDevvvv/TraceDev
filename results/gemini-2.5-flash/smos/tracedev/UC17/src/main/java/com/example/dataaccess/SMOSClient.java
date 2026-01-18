package com.example.dataaccess;

import com.example.dto.SMOSAddressData;
import com.example.exceptions.ConnectionException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Simulates an external client for the SMOS (Supply Management & Operations System).
 * Responsible for connecting to SMOS and fetching raw address data.
 */
public class SMOSClient {
    private boolean simulatedConnectionFailure = false; // For testing connection interruption
    private Random random = new Random();

    public SMOSClient() {
        System.out.println("[SMOSClient] Initialized.");
    }

    /**
     * Simulates fetching raw address data from the SMOS server.
     * Can randomly throw a ConnectionException to simulate network issues.
     *
     * @return A list of raw SMOSAddressData.
     * @throws ConnectionException If a connection or communication error occurs with SMOS.
     */
    public List<SMOSAddressData> fetchRawAddresses() throws ConnectionException {
        System.out.println("[SMOSClient] Attempting to fetch raw addresses from SMOS...");

        // Simulate connection interruption based on flag or random chance
        if (simulatedConnectionFailure || random.nextInt(100) < 20) { // 20% chance of failure
            System.err.println("[SMOSClient] Simulated connection interruption!");
            throw new ConnectionException("Failed to connect to SMOS server or retrieve data.");
        }

        if (!isConnected()) {
            System.err.println("[SMOSClient] Not connected to SMOS, cannot fetch data.");
            throw new ConnectionException("SMOS client is not connected.");
        }

        // Simulate successful data retrieval
        List<SMOSAddressData> rawData = Arrays.asList(
                new SMOSAddressData("SMOS001", "123 Main St", "Anytown", "12345"),
                new SMOSAddressData("SMOS002", "456 Oak Ave", "Sometown", "67890"),
                new SMOSAddressData("SMOS003", "789 Pine Ln", "Otherville", "11223")
        );
        System.out.println("[SMOSClient] Successfully fetched " + rawData.size() + " raw addresses.");
        return rawData;
    }

    /**
     * Checks if the SMOS client is currently connected.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        // In a real scenario, this would involve a ping or handshake.
        // For simulation, assume always connected unless explicitly failing.
        return true;
    }

    /**
     * Setter for simulating connection failure, primarily for testing error paths.
     * @param simulatedConnectionFailure true to force a ConnectionException, false otherwise.
     */
    public void setSimulatedConnectionFailure(boolean simulatedConnectionFailure) {
        this.simulatedConnectionFailure = simulatedConnectionFailure;
    }
}