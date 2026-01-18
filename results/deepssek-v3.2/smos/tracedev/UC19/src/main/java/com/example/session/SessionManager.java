package com.example.session;

import com.example.model.User;

/**
 * Manages user session and tracks the last performed use case for entry condition validation.
 */
public class SessionManager {
    private boolean loggedIn;
    private User currentUser;
    private String previousUseCase; // Tracks last performed use case for entry condition validation

    // Constructor
    public SessionManager() {
        this.loggedIn = false;
        this.previousUseCase = null;
    }

    /**
     * Checks if user is logged in.
     * Sequence: Called by ViewAddressDetailsController.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Returns the current logged-in user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the previous use case performed by the user.
     * This method is assumed for entry condition validation.
     */
    public String getPreviousUseCase() {
        return previousUseCase;
    }

    /**
     * Sets the previous use case.
     * Called after a use case is performed.
     */
    public void setPreviousUseCase(String previousUseCase) {
        this.previousUseCase = previousUseCase;
    }

    /**
     * Logs in a user and sets the previous use case to the login use case.
     * For simulation purposes.
     */
    public void login(User user) {
        this.loggedIn = true;
        this.currentUser = user;
        this.previousUseCase = "Login";
    }

    /**
     * Logs out the user.
     */
    public void logout() {
        this.loggedIn = false;
        this.currentUser = null;
        this.previousUseCase = null;
    }
}