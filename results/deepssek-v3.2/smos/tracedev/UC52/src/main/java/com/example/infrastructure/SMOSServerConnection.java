package com.example.infrastructure;

/**
 * Interface for connecting to the external SMOS server.
 * Used for connection validation and data synchronization.
 */
public interface SMOSServerConnection {
    /**
     * Checks if the connection to the SMOS server is active.
     * @return true if connected, false otherwise.
     */
    boolean isConnected();

    /**
     * Validates the connection to the SMOS server.
     * @throws Exception if the connection is interrupted or invalid.
     */
    void validateConnection() throws java.lang.Exception;
}
