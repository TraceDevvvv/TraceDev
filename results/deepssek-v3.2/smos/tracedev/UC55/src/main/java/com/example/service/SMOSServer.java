package com.example.service;

/**
 * External server interface for SMOS system.
 * Stereotype: <<external>> from UML diagram.
 */
public class SMOSServer {
    public boolean receiveDelayData(String delayData) {
        // Simulate communication with external server
        // In real implementation, this would make an HTTP call or use other protocol
        System.out.println("Sending delay data to SMOS server: " + delayData);
        
        // Simulate success for demonstration
        // In production, this would return actual server response
        return true;
    }
}