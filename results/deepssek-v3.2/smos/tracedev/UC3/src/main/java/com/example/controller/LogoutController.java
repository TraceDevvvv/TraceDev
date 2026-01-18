package com.example.controller;

import com.example.model.AuditLogger;
import com.example.model.SessionManager;

/**
 * Controller dedicated to handling logout requests.
 */
public class LogoutController {
    private SessionManager sessionManager = new SessionManager();
    private AuditLogger auditLogger = new AuditLogger();

    /**
     * Handles a logout request by invalidating the session and logging the action.
     */
    public void handleLogoutRequest() {
        System.out.println("LogoutController handling logout request.");
        // In a real system, the token would be extracted from the request context
        String token = "sample-token-123";
        boolean invalidated = sessionManager.invalidateSession(token);
        if (invalidated) {
            auditLogger.logAction("user123", "logout_complete", System.currentTimeMillis());
            System.out.println("LogoutController: logout successful.");
            // Return logout successful to FrontController as per sequence diagram
            logoutSuccessful();
        } else {
            System.out.println("LogoutController: session invalidation failed.");
        }
    }

    /**
     * Simulates returning logout successful as per sequence diagram.
     */
    public void logoutSuccessful() {
        System.out.println("LogoutController: logout successful return.");
    }
}