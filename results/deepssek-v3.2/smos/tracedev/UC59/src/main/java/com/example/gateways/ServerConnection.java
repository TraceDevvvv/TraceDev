package com.example.gateways;

/**
 * Represents a connection to the server.
 * Implements Connection participant in sequence diagram.
 */
public class ServerConnection {
    private boolean isConnected;
    private String serverAddress;
    
    public ServerConnection(String serverAddress) {
        this.serverAddress = serverAddress;
        this.isConnected = false;
    }
    
    /**
     * Opens connection to server.
     */
    public boolean open() {
        // Simulate connection opening
        isConnected = true;
        System.out.println("Connected to server at: " + serverAddress);
        return true;
    }
    
    /**
     * Sends a request to the server.
     */
    public String sendRequest(String request) {
        if (!isConnected) {
            throw new RuntimeException("Not connected to server");
        }
        
        // Simulate server response
        System.out.println("Sending request: " + request);
        return "{ \"data\": \"Sample JSON response from server\" }";
    }
    
    /**
     * Closes the connection.
     */
    public void close() {
        isConnected = false;
        System.out.println("Disconnected from server");
    }
    
    /**
     * Checks if connection is active.
     */
    public boolean isActive() {
        return isConnected;
    }
}