package com.absenceapp;

/**
 * Interface for a student registration service.
 * REQ-001: Defines a method to check if a user's registration is completed.
 */
public interface SRegistrationService {
    /**
     * Checks if a user's registration process is completed.
     *
     * @param userId The ID of the user (student) to check.
     * @return true if registration is completed, false otherwise.
     */
    boolean isRegistrationCompleted(String userId);
}