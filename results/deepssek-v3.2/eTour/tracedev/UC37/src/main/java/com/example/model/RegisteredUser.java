package com.example.model;

/**
 * Represents a registered user in the system.
 */
public class RegisteredUser {
    private String username;
    private String email;
    private boolean isLoggedIn;

    public RegisteredUser(String username, String email, boolean isLoggedIn) {
        this.username = username;
        this.email = email;
        this.isLoggedIn = isLoggedIn;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean status) {
        this.isLoggedIn = status;
    }

    /**
     * Logout method to be called by the controller.
     * This method sets the user's logged-in status to false.
     * Note: The actual logout process is handled by LogoutController.
     */
    public void logout() {
        this.isLoggedIn = false;
    }
}