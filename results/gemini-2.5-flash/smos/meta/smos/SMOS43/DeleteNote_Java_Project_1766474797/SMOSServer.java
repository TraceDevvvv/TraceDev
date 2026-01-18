package com.system.school.integration;

/**
 * Simulates the connection and disconnection to an external SMOS (School Management Operating System) server.
 * This class is used to represent the state of the connection as mentioned in the use case postconditions.
 */
public class SMOSServer {
    private boolean isConnected;

    /**
     * Constructs a new SMOSServer instance, initially disconnected.
     */
    public SMOSServer() {
        this.isConnected = false;
    }

    /**
     * Establishes a simulated connection to the SMOS server.
     * Prints a message indicating the connection status.
     */
    public void connect() {
        if (!isConnected) {
            System.out.println("SMOS Server: Attempting to connect...");
            // Simulate connection logic
            this.isConnected = true;
            System.out.println("SMOS Server: Connection established successfully.");
        } else {
            System.out.println("SMOS Server: Already connected.");
        }
    }

    /**
     * Interrupts or closes the simulated connection to the SMOS server.
     * Prints a message indicating the disconnection status.
     */
    public void disconnect() {
        if (isConnected) {
            System.out.println("SMOS Server: Disconnecting from server...");
            // Simulate disconnection logic
            this.isConnected = false;
            System.out.println("SMOS Server: Connection to SMOS server interrupted.");
        } else {
            System.out.println("SMOS Server: Already disconnected.");
        }
    }

    /**
     * Checks if the SMOS server is currently connected.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }
}