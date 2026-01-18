package com.example.application;

import com.example.domain.Credentials;
import com.example.domain.User;

/**
 * Port interface for authentication and authorization concerns.
 * (Added to satisfy REQ-001)
 */
public interface AuthenticationService {
    /**
     * Authenticates a user based on provided credentials.
     *
     * @param credentials The user's credentials (username, password).
     * @return A User object if authentication is successful, null otherwise (or throws an exception).
     */
    User authenticate(Credentials credentials);

    /**
     * Authorizes a user to perform a specific action on a resource.
     *
     * @param user The authenticated user.
     * @param resource The resource being accessed (e.g., "convention", "report").
     * @param action The action being performed (e.g., "create", "read", "update", "delete").
     * @return true if the user is authorized, false otherwise.
     */
    boolean authorize(User user, String resource, String action);
}