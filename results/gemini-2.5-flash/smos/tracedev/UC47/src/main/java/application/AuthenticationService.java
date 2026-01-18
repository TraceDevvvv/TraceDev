package application;

import domain.User;
import domain.Role;

/**
 * Application service for user authentication and authorization.
 * Added to satisfy REQ-001.
 */
public class AuthenticationService {

    /**
     * Checks if a user is authenticated. (Mock implementation)
     * @param user The user to check.
     * @return true if the user is considered authenticated, false otherwise.
     */
    public boolean isAuthenticated(User user) {
        // In a real system, this would involve checking tokens, sessions, etc.
        // For this mock, any non-null user is considered authenticated.
        System.out.println("DEBUG: AuthenticationService: isAuthenticated(" + (user != null ? user.getUsername() : "null") + ")");
        return user != null;
    }

    /**
     * Checks if a user has a specific role.
     * @param user The user to check.
     * @param role The role to verify.
     * @return true if the user has the specified role, false otherwise.
     */
    public boolean hasRole(User user, Role role) {
        if (user == null) {
            System.out.println("DEBUG: AuthenticationService: User is null, cannot check role.");
            return false;
        }
        System.out.println("DEBUG: AuthenticationService: hasRole(" + user.getUsername() + ", " + role + ") -> " + (user.getRole() == role));
        return user.getRole() == role;
    }
}