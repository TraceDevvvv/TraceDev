package com.example.tourist;

/**
 * Represents connection to the ETOUR server.
 * Simulates server connection status and data update operations.
 */
public class ETOURServerConnection {
    private boolean connectionStatus;

    public ETOURServerConnection() {
        this.connectionStatus = true; // Assume initially connected
    }

    public boolean isConnected() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean status) {
        this.connectionStatus = status;
    }

    /**
     * Updates tourist data on the server (renamed from uploadData per REQ-007).
     * @param tourist the tourist entity to update
     * @return true if successful, false on failure
     */
    public boolean updateTouristData(Tourist tourist) {
        // Simulate server update operation
        // In a real scenario, this would make an API call
        if (connectionStatus) {
            System.out.println("Updating tourist data on server for tourist ID: " + tourist.getId());
            return true;
        } else {
            System.out.println("Server connection failed for tourist ID: " + tourist.getId());
            return false;
        }
    }
}