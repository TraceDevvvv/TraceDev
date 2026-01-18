package com.example.infrastructure;

import com.example.entity.Administrator;

/**
 * Service for authentication and session management.
 * Validates administrator sessions and retrieves current user.
 */
public class AuthenticationService {
    /**
     * Validates the session for a given administrator ID.
     * As per sequence diagram, this is called during preconditions.
     *
     * @param adminId the administrator's ID.
     * @return true if session is valid, false otherwise.
     */
    public boolean validateSession(Long adminId) {
        // For simplicity, assume session is always valid.
        System.out.println("Validating session for admin ID: " + adminId);
        return true;
    }

    /**
     * Retrieves the currently logged-in administrator.
     *
     * @return the current Administrator.
     */
    public Administrator getCurrentUser() {
        // For simplicity, return a dummy administrator.
        return new Administrator(1L, "Admin User");
    }

    /**
     * Returns session valid status (sequence diagram message).
     * @return boolean indicating session is valid.
     */
    public boolean sessionValid() {
        System.out.println("Session valid.");
        return true;
    }
}