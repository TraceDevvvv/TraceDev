package com.example.service;

import com.example.model.Session;
import com.example.model.LoginCredentials;

/**
 * Service responsible for authentication and session management.
 */
public class AuthenticationService {
    private Session sessionState;

    public AuthenticationService(Session sessionState) {
        this.sessionState = sessionState;
    }

    /**
     * Validates the provided login credentials.
     * For demonstration, we assume invalid if username or password is empty.
     *
     * @param credentials the login credentials
     * @return true if valid, false otherwise
     */
    public boolean validateLoginCredentials(LoginCredentials credentials) {
        // Simple validation: username and password must not be empty.
        if (credentials.getUsername() == null || credentials.getUsername().isEmpty()) {
            return false;
        }
        if (credentials.getPassword() == null || credentials.getPassword().isEmpty()) {
            return false;
        }
        // In a real application, we would check against a database or other store.
        return true;
    }

    /**
     * Retrieves the previous session state.
     * For simplicity, we return the current session state as the previous one.
     * In a real application, we might have a history of states.
     *
     * @return the previous session state
     */
    public Session getPreviousState() {
        // For this example, we assume the previous state is the same as the current.
        // In a real scenario, we would have a mechanism to store and retrieve previous states.
        return sessionState;
    }

    /**
     * Recovers the session to a given state.
     *
     * @param state the session state to recover to
     */
    public void recoverToState(Session state) {
        if (state != null) {
            // Restore the state by copying the state data.
            sessionState.restoreState(state.getState());
        }
    }
}