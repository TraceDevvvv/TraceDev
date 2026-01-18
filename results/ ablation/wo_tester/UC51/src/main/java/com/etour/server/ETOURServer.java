package com.etour.server;

/**
 * Represents the ETOUR server.
 * Added to satisfy requirement Exit Conditions.
 */
public class ETOURServer {
    private String serverUrl;

    public ETOURServer(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * Establishes connection to the server.
     * @return true if connection successful, false otherwise.
     */
    public boolean establishConnection() {
        // Simulate connection attempt
        try {
            Thread.sleep(200); // simulated delay
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * Simulates a connection timeout.
     */
    public void connectionTimeout() {
        System.out.println("ETOUR Server: Connection timeout.");
    }
}