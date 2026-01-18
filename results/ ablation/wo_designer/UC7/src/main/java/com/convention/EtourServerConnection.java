
package com.convention;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simulates connection to the ETOUR server.
 * Handles connectivity for data loading and processing (Quality Requirement).
 */
public class EtourServerConnection {
    private final AtomicBoolean connected;
    private final Random random;

    public EtourServerConnection() {
        this.connected = new AtomicBoolean(true);
        this.random = new Random();
    }

    /**
     * Check if connected to server.
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        // Simulate occasional connectivity issues
        if (random.nextDouble() < 0.1) { // 10% chance of connection failure
            connected.set(false);
            return false;
        }
        connected.set(true);
        return true;
    }

    /**
     * Process activation request on server.
     * @param convention The convention to activate
     * @return true if server processing successful, false otherwise
     */
    public boolean processActivation(Convention convention) {
        if (!isConnected()) {
            System.out.println("Server Error: Cannot process activation - no connection");
            return false;
        }
        
        // Simulate server processing with occasional failure
        if (random.nextDouble() < 0.05) { // 5% chance of server rejection
            System.out.println("Server Error: Processing failed for convention " + convention.getConventionId());
            return false;
        }
        
        System.out.println("Server: Successfully processed activation for convention " 
                         + convention.getConventionId());
        return true;
    }

    /**
     * Disconnect from the server.
     */
    public void disconnect() {
        connected.set(false);
        System.out.println("Disconnected from ETOUR server.");
    }
}
