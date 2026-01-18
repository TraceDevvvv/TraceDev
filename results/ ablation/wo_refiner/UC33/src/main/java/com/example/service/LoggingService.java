package com.example.service;

/**
 * Logging service for tracking registration attempts and system events.
 * Included to satisfy requirement REQ-006.
 */
public class LoggingService {
    
    /**
     * Logs a registration attempt.
     * @param email the email used in registration attempt
     * @param success whether the attempt was successful
     */
    public void logRegistrationAttempt(String email, boolean success) {
        String status = success ? "SUCCESS" : "FAILED";
        System.out.println("Registration attempt - Email: " + email + 
            " Status: " + status);
    }
    
    /**
     * Logs a system event.
     * @param event the event description
     */
    public void logSystemEvent(String event) {
        System.out.println("System Event: " + event);
    }
}