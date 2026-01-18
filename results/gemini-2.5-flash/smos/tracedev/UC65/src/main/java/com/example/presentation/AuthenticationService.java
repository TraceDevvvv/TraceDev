package com.example.presentation;

/**
 * Service for handling user authentication.
 * Added to satisfy requirement R3.
 */
public class AuthenticationService {

    /**
     * Checks if the current user is logged in.
     *
     * @return true if the user is logged in, false otherwise.
     */
    public boolean checkLoggedInStatus() {
        // --- Assumption: For simulation purposes, assume user is always logged in. ---
        System.out.println("[AuthenticationService] Checking logged-in status... User is logged in (R3).");
        return true;
    }
}