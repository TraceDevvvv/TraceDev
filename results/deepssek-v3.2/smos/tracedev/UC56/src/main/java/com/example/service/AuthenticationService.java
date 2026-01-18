package com.example.service;

import com.example.model.Professor;

/**
 * Service handling professor authentication.
 */
public class AuthenticationService {

    /**
     * Authenticates a professor by username and password.
     * For simplicity, we assume a dummy authentication.
     * In a real application, this would validate against a database or external service.
     * @param username the username
     * @param password the password
     * @return Professor instance if authentication succeeds, null otherwise.
     */
    public Professor authenticate(String username, String password) {
        // Dummy authentication: any non‑empty username/password is accepted.
        if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
            // Assume the professor id is derived from username and name is the username.
            return new Professor("prof-" + username, username);
        }
        return null;
    }

    /**
     * Checks if a professor is currently logged in.
     * For simplicity, we always return true if professorId is not null.
     * @param professorId the professor's ID
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn(String professorId) {
        return professorId != null;
    }
}