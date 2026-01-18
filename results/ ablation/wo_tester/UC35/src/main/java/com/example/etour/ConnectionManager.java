package com.example.etour;

/**
 * Manages the connection status to the server.
 * This class is used to check if the server is available.
 */
public class ConnectionManager {
    public Boolean serverAvailable;

    /**
     * Checks the status of the server.
     * @return true if server is available, false otherwise.
     */
    public Boolean checkServerStatus() {
        // Simulating server check; in real implementation, would ping the server.
        serverAvailable = Boolean.TRUE;
        return serverAvailable;
    }

    /**
     * Gets the ETOUR connection status.
     * @return true if connection is successful.
     */
    public Boolean getETOURConnection() {
        // Assumption: Returns the current serverAvailable status.
        return serverAvailable;
    }
}