package com.example.server;

/**
 * Represents a connection to the server.
 */
public class ServerConnection {
    private boolean connected;

    public ServerConnection() {
        this.connected = false;
    }

    /**
     * Checks if the connection is established.
     *
     * @return true if connected.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Establishes a connection.
     *
     * @return true if connection established successfully.
     */
    public boolean establishConnection() {
        connected = true;
        System.out.println("Server connection established.");
        return true;
    }

    /**
     * Closes the connection.
     */
    public void closeConnection() {
        connected = false;
        System.out.println("Server connection closed.");
    }

    /**
     * Simulates connection loss.
     */
    public void simulateConnectionLoss() {
        connected = false;
        System.out.println("Server connection lost.");
    }
}