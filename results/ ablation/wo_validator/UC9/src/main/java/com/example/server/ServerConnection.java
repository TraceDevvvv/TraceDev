package com.example.server;

import com.example.model.PointOfRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a connection to an external server.
 */
public class ServerConnection {
    private String serverUrl;
    private int timeout;
    private boolean connected = false;

    public ServerConnection(String url) {
        this.serverUrl = url;
        this.timeout = 5000; // default timeout
    }

    public ServerConnection(String url, int timeout) {
        this.serverUrl = url;
        this.timeout = timeout;
    }

    /**
     * Simulates connecting to server.
     * @return true if connection successful
     */
    public boolean connect() {
        // Simulate connection logic
        connected = true;
        return true; // always successful for this simulation
    }

    /**
     * Sends a request to the server with given criteria.
     * @param request the search criteria
     * @return list of PointOfRest objects from server
     */
    public List<PointOfRest> sendRequest(Map<String, Object> request) {
        // Simulate server communication
        if (!connected) {
            if (!connect()) {
                throw new RuntimeException("Server connection failed");
            }
        }

        // Simulate fetching from server (mock data)
        return fetchFromServer();
    }

    /**
     * Simulates fetching data from server (mock implementation).
     * In real scenario, would use HTTP client or similar.
     */
    protected List<PointOfRest> fetchFromServer() {
        List<PointOfRest> points = new ArrayList<>();
        points.add(new PointOfRest("1", "Rest Area A", "Highway 1", List.of("Toilet", "Parking")));
        points.add(new PointOfRest("2", "Service Station B", "Highway 5", List.of("Fuel", "Cafe", "Restroom")));
        points.add(new PointOfRest("3", "Picnic Spot C", "Route 66", List.of("Tables", "BBQ")));
        return points;
    }

    /**
     * Disconnects from server.
     */
    public void disconnect() {
        connected = false;
    }

    // Getters and setters
    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isConnected() {
        return connected;
    }
}