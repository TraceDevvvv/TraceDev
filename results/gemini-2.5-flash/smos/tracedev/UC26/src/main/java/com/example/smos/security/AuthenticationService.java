package com.example.smos.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service responsible for user authentication and session management.
 * Corresponds to the 'AuthenticationService' class in the UML Class Diagram.
 */
public class AuthenticationService {

    // In-memory map to store active sessions
    private final Map<String, Session> activeSessions = new ConcurrentHashMap<>();

    // A simple user store for demonstration purposes
    private final Map<String, String> userCredentials = new ConcurrentHashMap<>();

    public AuthenticationService() {
        // Initialize with a dummy user for demonstration
        userCredentials.put("admin", "password123");
        userCredentials.put("guest", "guestpass");
    }

    /**
     * Authenticates a user with the given username and password.
     * If successful, a new session is created and returned.
     * Corresponds to the 'login' method in the UML Class Diagram.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A new Session object if authentication is successful, null otherwise.
     */
    public Session login(String username, String password) {
        System.out.println("AuthService: Attempting to login user: " + username);
        // Simple authentication logic
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            Session session = new Session(username); // Using username as userId for simplicity
            activeSessions.put(session.getSessionId(), session);
            System.out.println("AuthService: User " + username + " logged in. Session created: " + session.getSessionId());
            return session;
        }
        System.out.println("AuthService: Login failed for user: " + username);
        return null;
    }

    /**
     * Logs out an active session, invalidating it.
     * Corresponds to the 'logout' method in the UML Class Diagram.
     * @param session The session to be logged out.
     */
    public void logout(Session session) {
        if (session != null && activeSessions.containsKey(session.getSessionId())) {
            activeSessions.remove(session.getSessionId());
            System.out.println("AuthService: Session " + session.getSessionId() + " for user " + session.getUserId() + " logged out.");
        } else {
            System.out.println("AuthService: Attempted to logout an invalid or non-existent session.");
        }
    }

    /**
     * Checks if a given session is currently active and authenticated.
     * Corresponds to the 'isAuthenticated' method in the UML Class Diagram.
     * @param session The session to check.
     * @return true if the session is active, false otherwise.
     */
    public boolean isAuthenticated(Session session) {
        if (session == null) {
            return false;
        }
        return activeSessions.containsKey(session.getSessionId());
    }
}