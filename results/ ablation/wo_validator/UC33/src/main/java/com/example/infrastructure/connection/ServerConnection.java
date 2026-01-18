package com.example.infrastructure.connection;

/**
 * Represents a connection to an external server.
 */
public class ServerConnection {
    private boolean isConnected = false;

    public boolean connect() {
        isConnected = true;
        System.out.println("Server connected.");
        return true;
    }

    public void disconnect() {
        isConnected = false;
        System.out.println("Server disconnected.");
    }

    public boolean isAlive() {
        return isConnected;
    }
}