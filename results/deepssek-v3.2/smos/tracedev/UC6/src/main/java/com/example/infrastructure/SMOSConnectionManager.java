package com.example.infrastructure;

/**
 * Manages the connection to the SMOS system.
 * Checks connection status and allows interruption.
 */
public class SMOSConnectionManager {
    /**
     * Checks if the connection to SMOS is alive.
     *
     * @return true if connection is OK, false otherwise.
     */
    public boolean checkConnection() {
        System.out.println("Checking SMOS connection...");
        // Simulate a successful connection
        return true;
    }

    /**
     * Interrupts the connection (e.g., due to user action).
     * Satisfies requirement Exit Conditions.
     */
    public void interrupt() {
        System.out.println("SMOS connection interrupted.");
    }

    /**
     * Returns connection OK status (sequence diagram message).
     * @return boolean indicating connection is OK.
     */
    public boolean connectionOK() {
        System.out.println("Connection OK.");
        return true;
    }

    /**
     * Returns connection interrupted status (sequence diagram message).
     * @return boolean indicating connection is interrupted.
     */
    public boolean connectionInterrupted() {
        System.out.println("Connection interrupted.");
        return false;
    }
}