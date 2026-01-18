package com.example.etour;

/**
 * Interface for ETOUR server connection management.
 */
public interface ETOURService {
    /**
     * Checks the connection to the ETOUR server.
     * @return true if the connection is OK, false otherwise.
     */
    boolean checkConnection();

    /**
     * Handles the loss of connection to the ETOUR server.
     * Exit Condition: The connection to the server ETOUR is interrupted.
     */
    void handleConnectionLoss();
}