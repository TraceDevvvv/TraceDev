package com.example.data;

/**
 * Represents a connection to the SMOS server.
 * Corresponds to ServerConnection in the class diagram.
 */
public class ServerConnection {
    private boolean isActive;

    public ServerConnection() {
        this.isActive = true; // Initially connected.
    }

    /**
     * Interrupts the connection (simulates administrator interruption).
     * Corresponds to interrupt() in class diagram.
     */
    public void interrupt() {
        System.out.println("ServerConnection: Interrupted by administrator.");
        isActive = false;
    }

    public boolean isConnected() {
        return isActive;
    }
}