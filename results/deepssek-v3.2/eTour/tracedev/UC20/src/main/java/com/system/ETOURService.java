package com.system;

/**
 * Simulates an external ETOUR server connection.
 */
public class ETOURService {
    private boolean connected = true; // Simulate connection state

    /**
     * Checks if the service is currently connected.
     * @return true if connected
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Checks the connection status (could perform a ping).
     * @return true if connection is alive
     */
    public boolean checkConnection() {
        // Simulate occasional failure: 10% chance of disconnection.
        if (Math.random() < 0.1) {
            connected = false;
        }
        return connected;
    }

    /**
     * Sends data to the external server.
     * @param data the data to send
     * @return true if send succeeded
     */
    public boolean sendData(Object data) {
        if (!connected) {
            return false;
        }
        System.out.println("Data sent to ETOUR: " + data);
        return true;
    }

    // For testing: allow manual disconnection.
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}