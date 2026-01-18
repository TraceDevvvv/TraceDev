package com.example.service;

/**
 * Service for handling registration cancellations.
 * Included to satisfy requirement REQ-013.
 */
public class CancellationService {
    
    /**
     * Cancels a user registration.
     * @param userId the ID of user to cancel
     * @return true if cancellation was successful
     */
    public boolean cancelRegistration(String userId) {
        // In a real implementation, this would remove or deactivate the user
        System.out.println("Registration cancelled for user: " + userId);
        return true;
    }
}