package com.example;

/**
 * Represents the connection to the external ETOUR server.
 * Added to satisfy requirement REQ-CONN-001.
 */
public class ETOURServer {
    private String connectionStatus;

    public ETOURServer() {
        this.connectionStatus = "Connected";
    }

    public boolean isConnectionActive() {
        return "Connected".equals(connectionStatus);
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String status) {
        this.connectionStatus = status;
    }
}