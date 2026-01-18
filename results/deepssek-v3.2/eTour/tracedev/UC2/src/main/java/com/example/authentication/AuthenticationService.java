package com.example.authentication;

import java.util.UUID;

/**
 * Interface for authentication serv.
 * Added to satisfy requirement: Entry Conditions: Agency Operator IS logged in
 */
public interface AuthenticationService {
    /**
     * Checks if a user is logged in.
     * @param userId the ID of the user
     * @return true if the user is logged in, false otherwise
     */
    boolean isLoggedIn(UUID userId);
}