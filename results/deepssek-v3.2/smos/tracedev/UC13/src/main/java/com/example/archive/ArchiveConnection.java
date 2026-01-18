package com.example.archive;

import com.example.model.Class;
import java.util.List;
import java.util.Map;

/**
 * Represents a connection to the archive (SMOS server).
 */
public class ArchiveConnection {
    private String serverUrl;
    private boolean isConnected;

    public ArchiveConnection(String serverUrl) {
        this.serverUrl = serverUrl;
        this.isConnected = false;
    }

    /**
     * Attempts to connect to the archive server.
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        // Simulate connection logic
        // In a real scenario, this would involve network operations
        isConnected = true; // Assume success for normal flow
        return isConnected;
    }

    /**
     * Disconnects from the archive server.
     */
    public void disconnect() {
        isConnected = false;
    }

    /**
     * Searches for classes in the archive based on criteria.
     * @param criteria search criteria map (e.g., academicYear)
     * @return list of matching Class objects
     */
    public List<Class> searchClasses(Map<String, String> criteria) {
        // Simulate archive search
        // For demonstration, we return a hardcoded list
        // REQ-F3: System searches for classes in the archive
        String year = criteria.get("academicYear");
        return List.of(
                new Class("C001", "Mathematics 101", year, "T001"),
                new Class("C002", "Physics 101", year, "T002"),
                new Class("C003", "Chemistry 101", year, "T003")
        );
    }

    /**
     * Checks if the connection is currently active.
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Notifies that the connection has been lost.
     */
    public void notifyConnectionLost() {
        // Log or handle connection loss notification
        System.out.println("Connection to archive lost.");
        isConnected = false;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * Simulates connection failure.
     * This method is called when the connection fails.
     */
    public void connectionFailed() {
        notifyConnectionLost();
    }
}