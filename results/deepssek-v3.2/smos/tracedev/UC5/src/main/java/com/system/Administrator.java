package com.system;

/**
 * Administrator entity, inherits from User (REQ-001).
 * Handles login/logout and authentication state.
 */
public class Administrator extends User {
    private String adminId;
    private String role;
    private boolean isLoggedIn;
    private static Administrator currentAdmin;

    public Administrator(String adminId, String role, String userId, String username, String email) {
        super(userId, username, email);
        this.adminId = adminId;
        this.role = role;
        this.isLoggedIn = false;
    }

    /**
     * Login method called prior to main sequence.
     * Sequence diagram group: Login Flow
     */
    public void login() {
        isLoggedIn = true;
        currentAdmin = this;
        System.out.println("Administrator " + adminId + " logged in.");
    }

    public void logout() {
        isLoggedIn = false;
        currentAdmin = null;
        System.out.println("Administrator logged out.");
    }

    public boolean isAuthenticated() {
        return isLoggedIn;
    }

    public static Administrator getCurrentAdmin() {
        return currentAdmin;
    }
}