package com.example.model;

/**
 * Utility class for logging security-relevant actions.
 */
public class AuditLogger {
    /**
     * Logs an action performed by a user.
     * @param userId the ID of the user performing the action.
     * @param action the description of the action.
     * @param timestamp the time the action occurred.
     */
    public void logAction(String userId, String action, long timestamp) {
        System.out.println("AUDIT LOG: User=" + userId + ", Action=" + action + ", Time=" + timestamp);
    }
}