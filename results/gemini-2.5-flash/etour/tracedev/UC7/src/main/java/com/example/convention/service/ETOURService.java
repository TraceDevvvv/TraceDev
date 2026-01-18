package com.example.convention.service;

import java.util.Random;

/**
 * External ETOUR Service simulation.
 * Responsible for sending activation notifications.
 */
public class ETOURService {
    private Random random = new Random();

    /**
     * Sends an activation notification to the ETOUR system for a given convention.
     * [R10, R11] Method `sendActivationNotification` implemented to satisfy requirements R10, R11.
     * Simulates success or failure (connection interruption) randomly.
     *
     * @param conventionId The ID of the convention that was activated.
     * @return True if the notification was sent successfully, false if there was a simulated connection error.
     */
    public boolean sendActivationNotification(String conventionId) {
        System.out.println("ETOUR: Attempting to send activation notification for Convention " + conventionId);
        // Simulate network call and potential interruption [R11]
        // 70% chance of success, 30% chance of failure (connection error)
        if (random.nextDouble() < 0.7) {
            System.out.println("ETOUR: Notification for " + conventionId + " sent successfully.");
            return true; // ETOUR Notification Success
        } else {
            System.out.println("ETOUR: Failed to send notification for " + conventionId + ". (Simulated connection error).");
            return false; // ETOUR Connection Interrupted
        }
    }
}