package com.etour.connection;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Simulates a connection to the ETOUR server.
 * In a real system, this would handle network communication.
 */
public class ETOURServerConnection {
    private boolean connectionStatus;

    public void connect() {
        // Simulate connection establishment
        connectionStatus = true;
        System.out.println("Connected to ETOUR server.");
    }

    public void disconnect() {
        connectionStatus = false;
        System.out.println("Disconnected from ETOUR server.");
    }

    /**
     * Checks if the connection is currently active.
     * @return true if connected.
     */
    public boolean isConnected() {
        // For simulation, sometimes we simulate a disconnection.
        return connectionStatus;
    }

    /**
     * Fetches data for a specific tourist by ID.
     * @param id the tourist ID.
     * @return map of tourist attributes.
     */
    public Map<String, String> fetchTouristData(String id) {
        // Simulate a possible connection loss
        if (!isConnected()) {
            throw new RuntimeException("Connection lost while fetching tourist data.");
        }
        // Simulate data retrieval
        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("name", "Tourist " + id);
        data.put("email", id + "@example.com");
        data.put("phone", "123-456-7890");
        data.put("nationality", "Unknown");
        return data;
    }

    /**
     * Fetches data for all tourists.
     * @return list of maps, each representing a tourist.
     */
    public List<Map<String, String>> fetchAllTouristData() {
        if (!isConnected()) {
            throw new RuntimeException("Connection lost while fetching all tourist data.");
        }
        // Simulate retrieval of multiple tourists
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Map<String, String> data = new HashMap<>();
            String id = "T" + i;
            data.put("id", id);
            data.put("name", "Tourist " + i);
            data.put("email", "tourist" + i + "@example.com");
            list.add(data);
        }
        return list;
    }
}