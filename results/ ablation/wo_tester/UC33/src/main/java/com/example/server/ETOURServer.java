package com.example.server;

/**
 * Simulates an external ETOUR server.
 */
public class ETOURServer {
    /**
     * Verifies the connection to the server.
     * @return true if connection is successful, false otherwise
     */
    public boolean verifyConnection() {
        // Simulate network latency
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // For demonstration, assume connection is always successful unless a flag is set
        return true;
    }

    /**
     * Fetches external data from the server.
     * @param request the request object
     * @return the response object
     */
    public Object fetchExternalData(Object request) {
        // Simulate fetching data
        return "External data for: " + request;
    }
}