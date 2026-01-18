package com.example.app;

/**
 * Handles user authentication-related operations, such as terminating user sessions.
 */
public class AuthenticationService {
    // - sessionManager : SessionManager
    private SessionManager sessionManager;

    /**
     * Constructs an AuthenticationService with a dependency on SessionManager.
     *
     * @param sessionManager The SessionManager instance to use for session operations.
     */
    public AuthenticationService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Terminates a user's session.
     * This involves getting the session ID and then invalidating it.
     *
     * @param userId The ID of the user whose session is to be terminated.
     * @return true if the session was successfully terminated, false otherwise.
     */
    public boolean terminateUserSession(String userId) {
        System.out.println("AuthenticationService: Attempting to terminate session for user: " + userId);
        // AuthService -> SessionMgr: getSessionIdForUser(userId)
        String sessionId = sessionManager.getSessionIdForUser(userId);

        if (sessionId != null && !sessionId.isEmpty()) {
            // AuthService -> SessionMgr: invalidateSession(sessionId)
            boolean sessionTerminated = sessionManager.invalidateSession(sessionId); // m9: sessionTerminated : boolean
            System.out.println("AuthenticationService: Session termination status for " + userId + ": " + sessionTerminated);
            // AuthService --> Controller: sessionTerminated : boolean
            return sessionTerminated; // m10: sessionTerminated : boolean
        } else {
            System.out.println("AuthenticationService: No active session found for user: " + userId);
            return false;
        }
    }
}