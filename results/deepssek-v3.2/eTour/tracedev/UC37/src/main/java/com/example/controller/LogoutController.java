package com.example.controller;

import com.example.model.LogoutConfirmation;
import com.example.model.RegisteredUser;
import com.example.repository.UserRepository;
import com.example.service.SessionService;
import java.util.Date;

/**
 * Secure logout controller implementing the Use Case Controller pattern.
 * Coordinates logout business logic, session invalidation, and user state updates.
 */
public class LogoutController {
    private SessionService sessionService;
    private UserRepository userRepository;

    public LogoutController(SessionService sessionService, UserRepository userRepository) {
        this.sessionService = sessionService;
        this.userRepository = userRepository;
    }

    /**
     * Initiates the logout process for a user.
     * @param userId The user ID.
     * @return true if logout process started successfully, false otherwise.
     */
    public boolean initiateLogout(String userId) {
        RegisteredUser user = userRepository.findById(userId);
        if (user == null) {
            return false;
        }
        // Check session validity
        if (!sessionService.isSessionValid(userId)) {
            return false;
        }
        // Request confirmation (Step 2)
        boolean confirmationRequested = requestConfirmation(userId);
        return confirmationRequested;
    }

    /**
     * Requests confirmation for logout (Step 2 in sequence diagram).
     * In a real application, this would trigger UI confirmation.
     * @param userId The user ID.
     * @return true if confirmation request is successful.
     */
    public boolean requestConfirmation(String userId) {
        // This method would typically interact with the UI layer.
        // For this implementation, we assume confirmation is always requested.
        return true;
    }

    /**
     * Confirms the logout after user approval.
     * @param userId The user ID.
     * @return true if logout succeeded, false otherwise.
     */
    public boolean confirmLogout(String userId) {
        // Create logout confirmation (Step 3)
        LogoutConfirmation confirmation = createLogoutConfirmation(userId);
        if (confirmation == null) {
            return false;
        }
        // Execute the logout
        executeLogout(userId);
        return true;
    }

    /**
     * Executes the logout by invalidating session and updating user state.
     * @param userId The user ID.
     */
    public void executeLogout(String userId) {
        // Invalidate session
        sessionService.invalidateSession(userId);
        // Update user logged-in status
        RegisteredUser user = userRepository.findById(userId);
        if (user != null) {
            user.setLoggedIn(false);
            userRepository.update(user);
        }
    }

    /**
     * Creates a logout confirmation object.
     * @param userId The user ID.
     * @return A new LogoutConfirmation instance.
     */
    public LogoutConfirmation createLogoutConfirmation(String userId) {
        return new LogoutConfirmation(userId, new Date());
    }

    /**
     * Simulates UI notification of successful logout.
     */
    public void logoutSuccess() {
        // This would inform the UI layer.
        System.out.println("Logout successful.");
    }

    /**
     * Simulates UI notification of invalid session.
     */
    public void sessionAlreadyInvalid() {
        // This would inform the UI layer.
        System.out.println("Session already invalid.");
    }

    // The following methods correspond to sequence diagram messages
    public void accessesLogoutFunctionality(String userId) {
        // UI calls this method to start logout
        initiateLogout(userId);
    }

    public void confirmLogoutRequest(String userId) {
        confirmLogout(userId);
    }

    public void displayConfirmationDialog(String userId) {
        // This would be called by UI to display dialog
        // Simulated by requestConfirmation
        requestConfirmation(userId);
    }

    public void displaySuccessNotification(String message) {
        System.out.println("Success notification: " + message);
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public RegisteredUser getUserEntity(String userId) {
        return userRepository.findById(userId);
    }
}