package com.example;

/**
 * Service representing the ETOUR server.
 * Satisfies REQ-011.
 */
public class ETOURService {
    private boolean connected = true;

    /**
     * Requests data from the server.
     */
    public String requestData() {
        System.out.println("ETOURService: Requesting data...");
        if (!connected) {
            throw new RuntimeException("Connection lost to ETOUR server");
        }
        return "Sample data from ETOUR";
    }

    public boolean isConnected() {
        return connected;
    }

    // For testing interruption scenario
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}