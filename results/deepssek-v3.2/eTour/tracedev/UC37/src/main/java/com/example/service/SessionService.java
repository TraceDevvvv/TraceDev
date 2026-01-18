package com.example.service;

import com.example.model.Session;

/**
 * Interface for session management.
 */
public interface SessionService {
    /**
     * Retrieves a session for a given user ID.
     * @param userId The user ID.
     * @return The session object.
     */
    Session getSession(String userId);

    /**
     * Invalidates the session for a given user ID.
     * @param userId The user ID.
     * @return true if invalidation succeeded, false otherwise.
     */
    boolean invalidateSession(String userId);

    /**
     * Checks if the session for a given user ID is valid.
     * @param userId The user ID.
     * @return true if the session is valid, false otherwise.
     */
    boolean isSessionValid(String userId);
}