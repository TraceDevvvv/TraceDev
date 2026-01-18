package com.example.registration;

/**
 * Concrete implementation of ISMOSGateway that simulates interaction with an SMOS server.
 */
public class SMOSGateway implements ISMOSGateway {

    /**
     * Simulates interrupting the connection to the SMOS system.
     * In a real system, this would involve network calls or API interactions.
     * As per sequence diagram, it simulates 'disconnect()' and 'disconnectedOK()'.
     */
    @Override
    public void interruptSMOSConnection() {
        System.out.println("[Gateway] Attempting to disconnect from SMOS server...");
        // Simulate interaction with SMOS Server
        // Gateway -> SMOS : disconnect()
        // SMOS --> Gateway : disconnectedOK()
        System.out.println("[SMOS Server] Disconnect command received.");
        System.out.println("[SMOS Server] Successfully disconnected.");
        System.out.println("[Gateway] Connection to SMOS server successfully interrupted.");
    }
}