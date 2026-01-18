package com.example;

/**
 * Interface for handling external system connections, e.g., SMOS.
 * Added to satisfy requirement R9.
 */
public interface IConnectionHandler {
    /**
     * Establishes a connection.
     * @return true if connection is successful, false otherwise.
     */
    boolean connect();

    /**
     * Disconnects from the system.
     * @return true if disconnection is successful, false otherwise.
     */
    boolean disconnect();

    /**
     * Checks the current connection status.
     * @return true if connected, false otherwise.
     */
    boolean checkConnection();
}