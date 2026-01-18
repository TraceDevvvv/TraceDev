package com.example.application;

import com.example.presentation.TouristController; // For TouristController.Credentials

/**
 * Application Layer: Handles authentication concerns for the system.
 * Added to satisfy requirement REQ-001 from the class diagram.
 */
public class AuthenticationService {

    // Simple in-memory "user store" for demonstration
    private static final String VALID_USERNAME = "testuser";
    private static final String VALID_PASSWORD = "password";
    private static final String AUTHENTICATED_TOURIST_ID = "T123";
    private static final String VALID_SESSION_ID = "session-abc-123";

    /**
     * Authenticates a user based on provided credentials.
     * Corresponds to `authenticate(credentials: Credentials)` in the class diagram.
     * @param credentials The user's login credentials.
     * @return An AuthenticationResult indicating success or failure and relevant data.
     */
    public AuthenticationResult authenticate(TouristController.Credentials credentials) {
        System.out.println("AuthenticationService: Attempting to authenticate user: " + credentials.username);
        if (VALID_USERNAME.equals(credentials.username) && VALID_PASSWORD.equals(credentials.password)) {
            System.out.println("AuthenticationService: Authentication successful for " + credentials.username);
            return new AuthenticationResult(true, AUTHENTICATED_TOURIST_ID, VALID_SESSION_ID);
        } else {
            System.out.println("AuthenticationService: Authentication failed for " + credentials.username);
            return new AuthenticationResult(false, null, null);
        }
    }

    /**
     * Checks if a given session ID is currently authenticated.
     * Corresponds to `isAuthenticated(sessionId: String)` in the class diagram.
     * @param sessionId The session identifier.
     * @return True if the session is authenticated, false otherwise.
     */
    public boolean isAuthenticated(String sessionId) {
        System.out.println("AuthenticationService: Checking if session " + sessionId + " is authenticated.");
        return VALID_SESSION_ID.equals(sessionId); // Simple check against a known valid session
    }

    /**
     * Retrieves the tourist ID associated with an authenticated session.
     * Corresponds to `getAuthenticatedTouristId(sessionId: String)` in the class diagram.
     * @param sessionId The session identifier.
     * @return The tourist ID if authenticated, null otherwise.
     */
    public String getAuthenticatedTouristId(String sessionId) {
        System.out.println("AuthenticationService: Getting authenticated tourist ID for session " + sessionId);
        if (isAuthenticated(sessionId)) {
            return AUTHENTICATED_TOURIST_ID;
        }
        return null;
    }
}