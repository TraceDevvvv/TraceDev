package com.example.teachingsystem.integration.smos;

import com.example.teachingsystem.exception.SMOSConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling integration with the SMOS server.
 * This class simulates connection and disconnection operations with an external system.
 */
@Service
public class SMOSIntegrationService {

    // Logger for logging messages related to SMOS integration
    private static final Logger log = LoggerFactory.getLogger(SMOSIntegrationService.class);

    // Flag to track the connection status to the SMOS server
    private boolean isConnected = false;

    /**
     * Establishes a simulated connection to the SMOS server.
     * In a real-world scenario, this would involve actual network calls
     * and authentication.
     */
    public void connect() {
        // Simulate connection logic
        log.info("Attempting to connect to SMOS server...");
        try {
            // Simulate a delay or complex connection process
            Thread.sleep(500); // Simulate network latency
            this.isConnected = true;
            log.info("Successfully connected to SMOS server.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("SMOS connection interrupted unexpectedly.", e);
            this.isConnected = false;
            // According to design, SMOSConnectionException is available,
            // but for connect(), we'll just log for now as the use case
            // focuses on disconnect post-deletion.
            // If connection failure is critical, an exception could be thrown here.
        }
    }

    /**
     * Disconnects from the SMOS server.
     * As per the use case postcondition, this connection is interrupted
     * after a teaching is eliminated.
     */
    public void disconnect() {
        if (this.isConnected) {
            log.info("Attempting to disconnect from SMOS server...");
            try {
                // Simulate disconnection logic
                Thread.sleep(300); // Simulate network latency
                this.isConnected = false;
                log.info("Successfully disconnected from SMOS server. Connection interrupted.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("SMOS disconnection interrupted, but proceeding. Connection status might be inconsistent.", e);
                // As per design, "logs the warning but proceeds" for disconnection failure.
                // We don't re-throw SMOSConnectionException here to allow the main flow to continue.
            }
        } else {
            log.warn("Attempted to disconnect from SMOS server, but it was not connected.");
        }
    }

    /**
     * Checks if the system is currently connected to the SMOS server.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return this.isConnected;
    }
}