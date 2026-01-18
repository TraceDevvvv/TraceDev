package domain;

import infrastructure.UserRepository;

/**
 * Service handling user authentication and session management.
 */
public class AuthenticationService {
    private UserRepository userRepository;
    // Simple session tracking
    private boolean loggedIn = false;
    private String currentUserId;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates an administrator based on credentials.
     * For simplicity, any non‑null string is accepted.
     */
    public boolean authenticate(String credentials) {
        if (credentials != null && !credentials.trim().isEmpty()) {
            loggedIn = true;
            currentUserId = "admin"; // hard‑coded for demo
            System.out.println("AuthenticationService: Login successful for " + currentUserId);
            return true;
        }
        return false;
    }

    public boolean isUserLoggedIn(String userId) {
        // In a real system, check session against given userId
        return loggedIn && currentUserId != null && currentUserId.equals(userId);
    }

    public void logout(String userId) {
        if (loggedIn && currentUserId != null && currentUserId.equals(userId)) {
            loggedIn = false;
            currentUserId = null;
            System.out.println("AuthenticationService: Logged out " + userId);
        }
    }
}