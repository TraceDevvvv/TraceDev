package com.example.infrastructure;

import com.example.application.AuthenticationService;
import com.example.domain.Credentials;
import com.example.domain.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for the AuthenticationService port, simulating an external authentication system.
 * (Added to satisfy REQ-001)
 */
public class ExternalAuthenticationAdapter implements AuthenticationService {

    /**
     * Simulates user authentication.
     *
     * @param credentials The user's credentials.
     * @return A dummy User object if credentials are "test/test", null otherwise.
     */
    @Override
    public User authenticate(Credentials credentials) {
        System.out.println("Adapter (Auth): Authenticating user: " + credentials.getUsername());
        if ("test".equals(credentials.getUsername()) && "test".equals(credentials.getPassword())) {
            // Simulate a successful authentication
            List<String> roles = Arrays.asList("OPERATOR", "USER");
            System.out.println("Adapter (Auth): Authentication successful for " + credentials.getUsername());
            return new User(credentials.getUsername() + "-id", roles);
        }
        System.out.println("Adapter (Auth): Authentication failed for " + credentials.getUsername());
        return null; // Authentication failed
    }

    /**
     * Simulates user authorization.
     * For demonstration, always returns true.
     *
     * @param user The authenticated user.
     * @param resource The resource being accessed.
     * @param action The action being performed.
     * @return true, always, for this mock implementation.
     */
    @Override
    public boolean authorize(User user, String resource, String action) {
        System.out.println("Adapter (Auth): Authorizing user '" + user.getId() + "' for '" + action + "' on '" + resource + "'");
        // For REQ-001, the note states authentication is handled externally before the controller.
        // This mock adapter simply allows all actions for any authenticated user.
        // In a real system, this would check user roles against resource permissions.
        return true; // Assume authorized for all actions for simplicity
    }
}