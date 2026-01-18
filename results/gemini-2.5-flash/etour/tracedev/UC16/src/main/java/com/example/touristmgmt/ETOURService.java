package com.example.touristmgmt;

/**
 * Represents an external ETOUR service, typically an integration point.
 */
public class ETOURService {

    /**
     * Disconnects from the ETOUR server.
     * This might involve closing a connection, revoking tokens, etc.
     */
    public void disconnect() {
        System.out.println("ETOURService: Disconnecting from ETOUR server...");
        // Simulate disconnection logic
        System.out.println("ETOURService: Disconnected successfully.");
    }
}