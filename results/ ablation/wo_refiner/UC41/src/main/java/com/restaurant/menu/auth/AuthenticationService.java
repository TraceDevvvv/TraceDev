package com.restaurant.menu.auth;

/**
 * Authentication service implementing security requirement R17.
 */
public class AuthenticationService {
    private boolean authenticated = false;

    /**
     * Authenticates a user.
     * Assumption: for demonstration, accepts any non-empty username/password.
     */
    public boolean authenticate(String username, String password) {
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            authenticated = true;
            return true;
        }
        authenticated = false;
        return false;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void logout() {
        authenticated = false;
    }
}