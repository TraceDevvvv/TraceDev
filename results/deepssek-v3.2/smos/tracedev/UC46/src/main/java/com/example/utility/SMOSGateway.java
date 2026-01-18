package com.example.utility;

import com.example.entity.ReportCardEntity;

/**
 * Utility class to communicate with the SMOS server.
 * Quality Requirement: persistData() completes within 3 seconds.
 */
public class SMOSGateway {
    private String serverUrl;
    private boolean connected;

    public SMOSGateway(String serverUrl) {
        this.serverUrl = serverUrl;
        this.connected = false;
    }

    /**
     * Attempts to connect to the SMOS server.
     * @return true if connection successful, false otherwise.
     */
    public boolean connect() {
        // Simulate connection logic
        try {
            Thread.sleep(500); // Simulate network delay
            connected = true;
            System.out.println("Connected to SMOS server at " + serverUrl);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            connected = false;
            return false;
        }
    }

    public boolean isConnected() {
        return connected;
    }

    /**
     * Persists the report card entity to the SMOS server.
     * Must complete within 3 seconds as per quality requirement.
     * @param entity the report card entity
     * @return true if persistence successful, false otherwise
     */
    public boolean persistData(ReportCardEntity entity) {
        if (!connected) {
            System.err.println("Cannot persist data: not connected to SMOS server.");
            return false;
        }
        long startTime = System.currentTimeMillis();
        // Simulate persistence to remote server
        try {
            Thread.sleep(1500); // Simulate network and processing time
            if (System.currentTimeMillis() - startTime > 3000) {
                System.err.println("Persistence exceeded 3 seconds quality requirement.");
                return false;
            }
            System.out.println("Persisted ReportCardEntity for student " + entity.getStudentId() + " to SMOS.");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Persistence interrupted: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the connection to the SMOS server.
     */
    public void closeConnection() {
        connected = false;
        System.out.println("Connection to SMOS server closed.");
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}