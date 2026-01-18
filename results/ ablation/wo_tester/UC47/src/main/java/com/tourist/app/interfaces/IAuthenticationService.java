package com.tourist.app.interfaces;

/**
 * Authentication service interface.
 */
public interface IAuthenticationService {
    /**
     * Checks if a user with the given id is authenticated.
     * @param userId the user id
     * @return true if authenticated, false otherwise
     */
    boolean IsAuthenticated(String userId);

    /**
     * Gets the current authenticated user id.
     * @return the current user id
     */
    String GetCurrentUser();
}