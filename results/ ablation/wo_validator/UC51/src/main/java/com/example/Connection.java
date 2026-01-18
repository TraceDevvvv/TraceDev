package com.example;

/**
 * Simulates a connection to a server.
 * Assumed class for connection management.
 */
public class Connection {
    private boolean connected = false;

    public void connect() {
        connected = true;
        System.out.println("Connection: Connected to server.");
    }

    public void disconnect() {
        connected = false;
        System.out.println("Connection: Disconnected from server.");
    }

    public boolean isConnected() {
        return connected;
    }
}