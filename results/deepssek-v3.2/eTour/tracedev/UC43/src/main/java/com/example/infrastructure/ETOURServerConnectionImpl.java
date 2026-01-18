package com.example.infrastructure;

/**
 * A simple implementation of ETOURServerConnection.
 */
public class ETOURServerConnectionImpl implements ETOURServerConnection {
    private boolean connected = true; // Simulating a normally connected state.

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public boolean checkConnection() {
        // Simulate occasional connection failure for demonstration.
        // In a real system, this would ping the server.
        connected = Math.random() > 0.2; // 80% chance of being connected.
        return connected;
    }

    @Override
    public boolean sendData(Object data) {
        if (!connected) {
            return false;
        }
        System.out.println("Data sent to server: " + data);
        return true;
    }
}