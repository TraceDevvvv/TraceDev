package com.example.etour;

public class ETourServerConnection {
    private boolean connected = false;

    public void connect() {
        // Simulate connection to ETOUR server
        connected = true;
        System.out.println("Connected to ETOUR server.");
    }

    public void disconnect() {
        // Simulate disconnection from ETOUR server
        if (connected) {
            connected = false;
            System.out.println("Disconnected from ETOUR server.");
        }
    }

    public boolean isConnected() {
        return connected;
    }
}