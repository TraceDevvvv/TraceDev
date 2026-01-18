package smos.login;

import smos.system.Administrator;

/**
 * Service managing login state.
 */
public class LoginService {
    private Administrator currentUser;

    public boolean isLoggedIn() {
        // For simplicity, assume logged in if currentUser is not null
        return currentUser != null;
    }

    public Administrator getCurrentUser() {
        return currentUser;
    }

    // Simulate login for testing
    public void login(Administrator admin) {
        this.currentUser = admin;
    }

    public void logout() {
        this.currentUser = null;
    }
}