package com.example.infrastructure.monitoring;

/**
 * Monitors system availability and logs downtime events.
 */
public class SystemAvailabilityMonitor {
    
    public SystemAvailabilityMonitor() {
        // Constructor
    }
    
    /**
     * Checks if the system is currently available.
     * For demonstration, always returns true.
     * @return true if system is available
     */
    public boolean checkAvailability() {
        // In a real implementation, this would check system metrics
        return true;
    }
    
    /**
     * Logs downtime events for monitoring purposes.
     * @param message The downtime message
     */
    public void logDowntime(String message) {
        System.out.println("[SYSTEM DOWNTIME] " + message);
    }
}