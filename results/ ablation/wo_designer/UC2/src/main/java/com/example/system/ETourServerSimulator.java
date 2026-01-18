package com.example.system;

/**
 * Simulates connection to the ETOUR server.
 * In a real scenario, this would handle network operations.
 */
public class ETourServerSimulator {
    private boolean connected = true;

    /**
     * Simulates server connection status.
     * @return true if connected, false if interrupted
     */
    public boolean isConnected() {
        // Simulate occasional disconnection
        if (Math.random() < 0.1) { // 10% chance of disconnection
            connected = false;
        }
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}