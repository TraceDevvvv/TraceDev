package com.school.smos;

/**
 * Active connection to SMOS server.
 */
public class SMOSConnection {
    private SMOSServer server;
    private boolean isConnected;

    public SMOSConnection(SMOSServer server) {
        this.server = server;
        this.isConnected = true;
    }

    public void send(String data) {
        if (isConnected) {
            System.out.println("Sending data to SMOS: " + data);
        } else {
            throw new IllegalStateException("Not connected to SMOS server");
        }
    }

    public void disconnect() {
        isConnected = false;
        System.out.println("SMOS connection disconnected.");
    }

    /**
     * Interrupts the connection as per sequence diagram opt block.
     */
    public void interrupt() {
        System.out.println("Administrator interrupted SMOS connection.");
        disconnect();
    }

    public boolean isConnected() {
        return isConnected;
    }
}