package com.example.service;

import com.example.domain.ConnectionStatus;

/**
 * Simulates server connection status and heartbeat.
 */
public class ServerConnection {
    private ConnectionStatus status;
    private long lastHeartbeat;

    public ServerConnection() {
        this.status = ConnectionStatus.CONNECTED; // assume connected initially
        this.lastHeartbeat = System.currentTimeMillis();
    }

    public ConnectionStatus checkConnection() {
        // Simulate occasional disconnection for testing
        // In a real app, this would ping the server.
        long now = System.currentTimeMillis();
        if (now - lastHeartbeat > 30000) { // 30 seconds timeout
            status = ConnectionStatus.TIMEOUT;
        }
        return status;
    }

    public boolean isConnected() {
        return status == ConnectionStatus.CONNECTED;
    }

    public void sendHeartbeat() {
        lastHeartbeat = System.currentTimeMillis();
        status = ConnectionStatus.CONNECTED;
    }

    // For testing simulation
    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }
}