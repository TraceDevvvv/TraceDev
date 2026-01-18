package com.example.manager;

/**
 * Manager for SMOS connection.
 * Can interrupt the connection, satisfying requirement "Exit Condition: SMOS interrupt".
 */
public class SMOSConnectionManager {
    /**
     * Interrupts the connection to the SMOS server.
     */
    public void interruptConnection() {
        System.out.println("SMOS connection interruption initiated.");
        // In a real implementation, this would close network connections, clean up resources, etc.
    }

    /**
     * Method corresponding to sequence return m23: Connection interrupted.
     */
    public void returnConnectionInterrupted() {
        System.out.println("Connection interrupted");
    }
}