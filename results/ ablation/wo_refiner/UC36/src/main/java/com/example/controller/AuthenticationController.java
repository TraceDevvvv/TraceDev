package com.example.controller;

import com.example.facade.NotificationFacade;
import com.example.model.*;
import com.example.service.LoginService;
import com.example.service.StateManager;

/**
 * Main controller for authentication flows (implements REQ‑1001, REQ‑1002, REQ‑1004, REQ‑1009).
 */
public class AuthenticationController {
    private LoginService loginService;
    private NotificationFacade notificationFacade;
    private StateManager stateManager;

    public AuthenticationController(LoginService loginService, NotificationFacade notificationFacade, StateManager stateManager) {
        this.loginService = loginService;
        this.notificationFacade = notificationFacade;
        this.stateManager = stateManager;
    }

    /**
     * Validates login credentials (entry condition REQ‑1004).
     * @param userData the login request
     * @return a ValidationResult
     */
    public ValidationResult validateLoginCredentials(LoginRequest userData) {
        return loginService.validateInput(userData);
    }

    /**
     * Handles an invalid login attempt.
     * @param errorMessage the error message
     * @return a RecoveryState indicating whether recovery was successful
     */
    public RecoveryState handleInvalidLogin(String errorMessage) {
        // Sequence diagram m7: NF returns notificationId
        Confirmation conf = notificationFacade.showError("Invalid login data", "ERROR");
        // Simulate user acknowledgement (in real scenario this would come from UI)
        Confirmation userAck = notificationFacade.acknowledgeNotification(conf.confirmationId);
        if (userAck.acknowledgedByUser) {
            // m12: SM internal restore()
            stateManager.internalRestore();
            // m13: SM returns sessionState
            SessionState previous = getPreviousState();
            boolean restored = previous != null && previous.restore();
            return new RecoveryState(previous, restored);
        } else {
            notificationFacade.clearPendingNotifications();
            loginService.cleanupSession(null);
            showDefaultScreen();
            return new RecoveryState(null, false);
        }
    }

    /**
     * Retrieves the previous session state.
     * @return the previous SessionState (or null)
     */
    public SessionState getPreviousState() {
        // In a real application, the session ID would come from the current context
        return stateManager.loadState("session123");
    }

    /**
     * Notifies the user (simulated).
     * @param notificationId the notification ID
     * @return true if notification was sent
     */
    public boolean notifyUser(String notificationId) {
        System.out.println("[AuthenticationController] Notifying user with ID: " + notificationId);
        return true;
    }

    /**
     * Returns to the previous state (simulated).
     * @return true if successful
     */
    public boolean returnToPreviousState() {
        System.out.println("[AuthenticationController] Returning to previous state.");
        return true;
    }

    /**
     * Shows the default screen (simulated).
     */
    public void showDefaultScreen() {
        System.out.println("[AuthenticationController] Showing default screen.");
    }

    /**
     * Simulates the login flow as described in the sequence diagram.
     * @param loginData the login request
     */
    public void submitLoginCredentials(LoginRequest loginData) {
        // Step: delegate authentication to LoginService
        boolean authResult = loginService.authenticate(loginData);
        if (!authResult) {
            // Invalid credentials path
            handleInvalidLogin("Invalid credentials");
            returnToPreviousState();  // exit condition REQ‑1009
        } else {
            // Valid credentials path
            System.out.println("[AuthenticationController] Authentication successful, redirecting to dashboard.");
            // m23: return redirectToDashboard()
            redirectToDashboard();
        }
    }

    /**
     * Redirects to dashboard as per sequence diagram message.
     */
    public void redirectToDashboard() {
        System.out.println("[AuthenticationController] Redirecting to dashboard.");
    }
}