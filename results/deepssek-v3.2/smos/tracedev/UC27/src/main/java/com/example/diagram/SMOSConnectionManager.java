package com.example.diagram;

/**
 * Manages connection to the SMOS system per requirement REQ-002.
 */
public class SMOSConnectionManager {
    private boolean isConnected;

    public SMOSConnectionManager() {
        // Initially connected for demo.
        this.isConnected = true;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    /**
     * Checks the connection status.
     * @return true if connected, false otherwise.
     */
    public boolean checkConnection() {
        System.out.println("Checking SMOS connection...");
        return isConnected;
    }

    /**
     * Interrupts the connection.
     * Called when a connection interruption is detected.
     */
    public void interruptConnection() {
        System.out.println("Interrupting SMOS connection.");
        isConnected = false;
    }

    /**
     * Notifies about disconnection.
     * Called by the view controller when the administrator reports a connection interruption.
     */
    public void notifyDisconnect() {
        System.out.println("Notification: SMOS connection disconnected.");
        interruptConnection();
    }
}