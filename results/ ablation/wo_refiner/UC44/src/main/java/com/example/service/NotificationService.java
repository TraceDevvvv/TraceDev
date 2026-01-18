package com.example.service;

/**
 * Service for notifying agencies about conventions.
 * Implements requirement REQ-014: handles ETOUR server failures.
 */
public class NotificationService {
    private int maxRetries = 3; // REQ-014

    /**
     * Notify the agency about a convention.
     * Returns true if successful, false otherwise.
     */
    public boolean notifyAgency(int conventionId, int agencyId) {
        // Simulate notification to external agency system
        System.out.println("Notifying agency " + agencyId + " about convention " + conventionId);

        // Simulate occasional connection failure (for demonstration)
        if (Math.random() < 0.3) { // 30% chance of failure
            handleConnectionLoss();
            return false;
        }

        return true; // Notification successful
    }

    /**
     * Handle connection loss with retry logic (REQ-014).
     */
    public void handleConnectionLoss() {
        System.out.println("Connection to ETOUR server lost. Initiating retry mechanism...");

        for (int i = 1; i <= maxRetries; i++) {
            System.out.println("Retry attempt " + i + " of " + maxRetries);
            try {
                Thread.sleep(1000); // Simulate delay between retries
                // In real implementation, would attempt to reconnect
                if (Math.random() < 0.7) { // 70% chance retry succeeds
                    System.out.println("Reconnected successfully on attempt " + i);
                    return;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Retry interrupted: " + e.getMessage());
                break;
            }
        }
        System.out.println("All retry attempts failed.");
    }
}