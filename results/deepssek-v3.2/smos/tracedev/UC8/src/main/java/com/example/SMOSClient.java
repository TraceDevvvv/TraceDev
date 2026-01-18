package com.example;

/**
 * Client for external SMOS service.
 */
public class SMOSClient {
    private boolean connected;
    private ErrorHandler errorHandler;

    public SMOSClient(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        this.connected = false;
    }

    public void connect() {
        this.connected = true;
        System.out.println("SMOS connected.");
    }

    public void disconnect() {
        this.connected = false;
        System.out.println("SMOS disconnected.");
    }

    public boolean isConnected() {
        return connected;
    }

    /**
     * Sends data to SMOS. Simulates possible connection interruption.
     */
    public void sendData(Object data) {
        if (!connected) {
            connect();
        }
        try {
            // Simulate sending data
            System.out.println("SMOS sending data: " + data.toString());
            // Simulate random connection interruption
            if (Math.random() < 0.3) {
                throw new RuntimeException("SMOS connection interrupted");
            }
        } catch (Exception e) {
            errorHandler.handleSystemError(e);
            throw new RuntimeException("SMOS send failed", e);
        }
    }
}