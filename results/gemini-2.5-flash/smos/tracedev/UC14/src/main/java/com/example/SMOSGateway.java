package com.example;

/**
 * Gateway for interacting with the SMOS system.
 * Added to satisfy requirement R15.
 */
public class SMOSGateway {

    /**
     * Sends class data to the SMOS system.
     * This method simulates an external system call and can throw a SMOSConnectionException.
     *
     * @param data The Class object to send to SMOS.
     * @throws SMOSConnectionException if there is an issue connecting to or sending data to SMOS.
     */
    public void sendDataToSMOS(Class data) throws SMOSConnectionException {
        // Simulate network latency or external system interaction
        System.out.println("SMOSGateway: Sending Class data to SMOS: " + data.getName() + "...");
        // For demonstration, let's randomly throw an exception to test the error path
        // In a real application, this would be based on actual connection status or SMOS response.
        if (Math.random() < 0.2) { // 20% chance of connection failure for testing
            throw new SMOSConnectionException("Failed to connect to SMOS server or send data for Class: " + data.getName());
        }
        System.out.println("SMOSGateway: Data sent successfully to SMOS for Class: " + data.getName());
    }
}