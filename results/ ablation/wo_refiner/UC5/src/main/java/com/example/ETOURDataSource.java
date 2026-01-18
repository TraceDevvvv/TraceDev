package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * Data source with quality attributes (timeout, retries).
 * Implements quality requirement REQ-010.
 */
public class ETOURDataSource {
    private String serverUrl;
    private int connectionTimeout;
    private int maxRetries;

    public ETOURDataSource() {
        this.serverUrl = "http://example-etour-server";
        this.connectionTimeout = 5000; // 5 seconds
        this.maxRetries = 2;
    }

    public ETOURDataSource(String serverUrl, int connectionTimeout, int maxRetries) {
        this.serverUrl = serverUrl;
        this.connectionTimeout = connectionTimeout;
        this.maxRetries = maxRetries;
    }

    public boolean connect() {
        // Simulate connection
        System.out.println("Connecting to ETOUR server: " + serverUrl);
        return true;
    }

    public void disconnect() {
        System.out.println("Disconnecting from ETOUR server");
    }

    /**
     * Fetches cultural heritage data by criteria.
     * For simulation, returns dummy data.
     */
    public List<CulturalHeritage> fetchCulturalHeritageData(String criteria) {
        // Simulate data fetching
        CulturalHeritage item1 = new CulturalHeritage("CH001", "Ancient Temple", "A historic temple", "Rome", "Ancient", Arrays.asList("img1.jpg"));
        CulturalHeritage item2 = new CulturalHeritage("CH002", "Medieval Castle", "A grand castle", "Paris", "Medieval", Arrays.asList("img2.jpg"));
        return Arrays.asList(item1, item2);
    }

    /**
     * Fetches a cultural heritage item by ID.
     * This method may throw exceptions to simulate connection issues.
     */
    public CulturalHeritage fetchCulturalHeritageById(String id) {
        // Simulate possible errors as per sequence diagram
        if (id.equals("timeout")) {
            throw new RuntimeException("TimeoutException: connectionTimeout exceeded");
        } else if (id.equals("connection_fail")) {
            throw new RuntimeException("ConnectionException: Cannot connect to server");
        }
        // Return dummy data
        return new CulturalHeritage(id, "Sample Heritage", "Description for " + id, "Unknown", "Unknown", Arrays.asList("default.jpg"));
    }

    /**
     * Handles connection error and returns an exception.
     * For simulation, returns a generic exception.
     */
    private Exception handleConnectionError() {
        return new RuntimeException("ETOUR connection error");
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }
}