package com.example.etour;

/**
 * Represents the server entity with connection status.
 */
public class Server {
    public String serverName = "ETOUR";
    public Boolean connectionStatus;

    /**
     * Checks if the server is available.
     * @return true if available.
     */
    public Boolean isAvailable() {
        // Assumption: Returns the connection status.
        return connectionStatus;
    }
}