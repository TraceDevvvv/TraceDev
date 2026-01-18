package com.example.service;

import java.sql.Connection;

/**
 * Service to manage external SMOS server connection.
 * Infrastructure layer component.
 */
public class SMOSService {
    private Connection connection;
    private boolean connected = false;

    /**
     * Simulates connecting to the SMOS server.
     */
    public void connect() {
        // Assumption: In a real system, this would establish a network connection.
        connected = true;
        System.out.println("Connected to SMOS server.");
    }

    /**
     * Disconnects from the SMOS server.
     * Called as part of the exit condition.
     */
    public void disconnect() {
        // Close the connection if open.
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection = null;
        }
        connected = false;
        System.out.println("Disconnected from SMOS server.");
    }

    /**
     * Checks if currently connected to the SMOS server.
     * @return true if connected.
     */
    public boolean isConnected() {
        return connected;
    }
}