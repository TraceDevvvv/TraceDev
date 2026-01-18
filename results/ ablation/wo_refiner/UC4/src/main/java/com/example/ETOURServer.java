package com.example;

// External system class representing the ETOUR server
public class ETOURServer {
    private boolean isConnected;

    public ETOURServer() {
        this.isConnected = true; // Initially assume connected
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    // Pings the server to check connection status
    public boolean ping() {
        // In a real system, this would send a network request
        // For demo, simulate connection status
        return isConnected;
    }
}