package com.editteachingsystem;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * SMOSServerConnection class simulates connection to the SMOS server.
 * Handles connection status, error conditions, and logging as specified
 * in the Edit Teaching use case postconditions.
 */
public class SMOSServerConnection {
    
    private AtomicBoolean isConnected;
    private AtomicBoolean connectionInterrupted;
    private Random random;
    private int connectionId;
    private LocalDateTime lastConnectionTime;
    
    /**
     * Constructor initializes the connection
     */
    public SMOSServerConnection() {
        this.isConnected = new AtomicBoolean(true); // Start connected
        this.connectionInterrupted = new AtomicBoolean(false);
        this.random = new Random();
        this.connectionId = random.nextInt(1000);
        this.lastConnectionTime = LocalDateTime.now();
        
        // Simulate occasional connection drops (10% chance on initialization)
        if (random.nextDouble() < 0.1) {
            isConnected.set(false);
            connectionInterrupted.set(true);
            System.err.println("SMOS Server: Connection interrupted on initialization");
        }
    }
    
    /**
     * Checks if the connection to SMOS server is active
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        // Simulate occasional connection drops (5% chance on each check)
        if (isConnected.get() && random.nextDouble() < 0.05) {
            isConnected.set(false);
            connectionInterrupted.set(true);
            System.err.println("SMOS Server: Connection interrupted during check");
        }
        
        return isConnected.get();
    }
    
    /**
     * Attempts to log a change to the SMOS server
     * @param changeDescription description of the change
     * @throws SMOSConnectionException if connection is not available
     */
    public void logChange(String changeDescription) throws SMOSConnectionException {
        if (!isConnected()) {
            throw new SMOSConnectionException("Cannot log change: SMOS server connection is not available");
        }
        
        // Simulate potential logging failure (3% chance)
        if (random.nextDouble() < 0.03) {
            throw new SMOSConnectionException("Failed to log change to SMOS server: Internal server error");
        }
        
        // Simulate successful logging
        String logEntry = String.format(
            "[%s] [Connection ID: %d] %s", 
            LocalDateTime.now(), 
            connectionId, 
            changeDescription
        );
        
        System.out.println("SMOS Server: Change logged successfully: " + logEntry);
        lastConnectionTime = LocalDateTime.now();
    }
    
    /**
     * Attempts to reconnect to the SMOS server
     * @throws SMOSConnectionException if reconnection fails
     */
    public void reconnect() throws SMOSConnectionException {
        System.out.println("Attempting to reconnect to SMOS server...");
        
        // Simulate reconnection success (80% chance)
        if (random.nextDouble() < 0.8) {
            isConnected.set(true);
            connectionInterrupted.set(false);
            connectionId = random.nextInt(1000);
            lastConnectionTime = LocalDateTime.now();
            System.out.println("SMOS Server: Reconnection successful. New connection ID: " + connectionId);
        } else {
            throw new SMOSConnectionException("Failed to reconnect to SMOS server");
        }
    }
    
    /**
     * Forcefully interrupts the connection (simulating administrator interruption)
     */
    public void interruptConnection() {
        if (isConnected.get()) {
            isConnected.set(false);
            connectionInterrupted.set(true);
            System.out.println("SMOS Server: Connection interrupted by administrator");
        }
    }
    
    /**
     * Gets the connection ID
     * @return connection ID
     */
    public int getConnectionId() {
        return connectionId;
    }
    
    /**
     * Gets the last successful connection time
     * @return last connection time
     */
    public LocalDateTime getLastConnectionTime() {
        return lastConnectionTime;
    }
    
    /**
     * Checks if connection was interrupted
     * @return true if interrupted, false otherwise
     */
    public boolean isConnectionInterrupted() {
        return connectionInterrupted.get();
    }
    
    /**
     * Resets the interruption flag
     */
    public void resetInterruptionFlag() {
        connectionInterrupted.set(false);
    }
    
    /**
     * Gets the connection status as a string
     * @return connection status string
     */
    public String getConnectionStatus() {
        if (isConnected()) {
            return String.format("CONNECTED (ID: %d, Last: %s)", 
                connectionId, lastConnectionTime);
        } else if (connectionInterrupted.get()) {
            return "INTERRUPTED";
        } else {
            return "DISCONNECTED";
        }
    }
}

/**
 * Exception class for SMOS connection errors
 */
class SMOSConnectionException extends Exception {
    public SMOSConnectionException(String message) {
        super(message);
    }
    
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}