package com.example;

/**
 * Represents a connection to the server.
 * Allows connection, interruption, and status checking.
 */
public class ServerConnection {
    private boolean isConnected;

    public ServerConnection() {
        this.isConnected = false; // Initially not connected
    }

    /**
     * Connects to the server.
     */
    public void connect() {
        isConnected = true;
        System.out.println("Server connection established.");
    }

    /**
     * Interrupts the server connection.
     */
    public void interrupt() {
        isConnected = false;
        System.out.println("Server connection interrupted.");
    }

    public boolean isConnected() {
        return isConnected;
    }
}