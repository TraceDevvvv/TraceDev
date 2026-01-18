package com.example.service;

import com.example.model.User;
import com.example.repository.IUserRepository;

/**
 * Service responsible for user authentication and authorization.
 * Added to handle entry conditions "User IS logged in" and "User HAS role 'ATA staff'".
 */
public class AuthenticationService {
    private IUserRepository userRepository;

    /**
     * Constructor for AuthenticationService, typically used for dependency injection.
     * @param userRepository The repository for accessing User data.
     */
    public AuthenticationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Checks if a user is authenticated (i.e., logged in).
     * This corresponds to the "ClassListView -> AuthenticationService : isAuthenticated(userId)"
     * interaction in the sequence diagram.
     *
     * @param userId The ID of the user to check.
     * @return true if the user exists (simulating being logged in), false otherwise.
     */
    public boolean isAuthenticated(String userId) {
        System.out.println("AuthenticationService: Checking if user '" + userId + "' is authenticated.");
        User user = userRepository.findById(userId);
        boolean authenticated = (user != null);
        System.out.println("AuthenticationService: User '" + userId + "' authenticated: " + authenticated);
        return authenticated;
    }

    /**
     * Checks if a user has a specific role.
     * This corresponds to the "ClassListView -> AuthenticationService : hasRole(userId, role)"
     * interaction in the sequence diagram.
     *
     * @param userId The ID of the user to check.
     * @param role The role to verify.
     * @return true if the user exists and has the specified role, false otherwise.
     */
    public boolean hasRole(String userId, String role) {
        System.out.println("AuthenticationService: Checking if user '" + userId + "' has role '" + role + "'.");
        User user = userRepository.findById(userId);
        boolean hasRole = (user != null && user.hasRole(role));
        System.out.println("AuthenticationService: User '" + userId + "' has role '" + role + "': " + hasRole);
        return hasRole;
    }
}