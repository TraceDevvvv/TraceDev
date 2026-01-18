package com.example.login;

/**
 * Manages the application's state, allowing for saving and recovery of a previous state.
 * This class uses a generic Object to represent the state, as per the diagram.
 */
public class ApplicationState {
    // Represents a generic previous state.
    // In a real application, this would be a more specific state object,
    // potentially serialized or a deep copy of relevant application data.
    private Object previousState;

    /**
     * Saves the current state of the application.
     *
     * @param state An object representing the current application state to be saved.
     */
    public void saveCurrentState(Object state) {
        System.out.println("[ApplicationState] Saving current state: " + state);
        this.previousState = state; // Simple assignment for demonstration
        System.out.println("[ApplicationState] State saved successfully.");
    }

    /**
     * Recovers the previously saved state.
     *
     * @return The previously saved state object, or null if no state was saved.
     */
    public Object recoverPreviousState() {
        if (previousState != null) {
            System.out.println("[ApplicationState] Recovering previous state...");
            Object recovered = this.previousState;
            this.previousState = null; // Clear after recovery, or keep if multiple recoveries are possible
            System.out.println("[ApplicationState] Previous state recovered: " + recovered);
            return recovered;
        } else {
            System.out.println("[ApplicationState] No previous state to recover.");
            return null;
        }
    }
}