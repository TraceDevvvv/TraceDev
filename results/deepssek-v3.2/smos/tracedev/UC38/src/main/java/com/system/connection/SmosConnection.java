package com.system.connection;

import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

/**
 * Simulates a connection to a SMOS server for managing justifications.
 */
public class SmosConnection {
    private boolean connected;

    public SmosConnection() {
        this.connected = false;
    }

    /**
     * Connects to the SMOS server.
     * @return true if connection successful.
     */
    public boolean connect() {
        // Simulate connection logic.
        this.connected = true;
        System.out.println("Connected to SMOS server.");
        return true;
    }

    /**
     * Disconnects from the SMOS server.
     */
    public void disconnect() {
        this.connected = false;
        System.out.println("Disconnected from SMOS server.");
    }

    /**
     * Checks if the connection is active.
     * @return true if connected.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Fetches justification data from the server by ID.
     * @param id The justification ID.
     * @return Optional containing the justification data as a map.
     */
    public Optional<Map<String, Object>> fetchJustification(String id) {
        if (!connected) {
            return Optional.empty();
        }
        // Simulate fetching data.
        Map<String, Object> data = new HashMap<>();
        data.put("justificationId", id);
        data.put("absenceId", "ABS001");
        data.put("description", "Sample justification description.");
        data.put("status", "PENDING");
        data.put("createdAt", new java.util.Date());
        data.put("updatedAt", new java.util.Date());
        return Optional.of(data);
    }

    /**
     * Updates a justification on the server.
     * @param id The justification ID.
     * @param data The updated data.
     * @return true if update successful.
     */
    public boolean updateJustification(String id, Map<String, Object> data) {
        if (!connected) {
            return false;
        }
        // Simulate update.
        System.out.println("Updated justification " + id + " on SMOS server.");
        return true;
    }

    /**
     * Deletes a justification from the server.
     * @param id The justification ID.
     * @return true if deletion successful.
     */
    public boolean deleteJustification(String id) {
        if (!connected) {
            return false;
        }
        // Simulate deletion.
        System.out.println("Deleted justification " + id + " from SMOS server.");
        return true;
    }
}