package com.example.user;

/**
 * User entity for login traceability.
 */
public class User {
    private boolean loggedIn;

    public User(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}