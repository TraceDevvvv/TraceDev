package com.example.service;

/**
 * Service for saving and restoring previous state.
 * Implements requirement: Recovers previous state after invalid login attempt.
 */
public class StateRecoveryService {
    private Object previousState;

    public StateRecoveryService() {
        // Initialize with a default state (could be null or a default object).
        this.previousState = "DefaultPreviousState";
    }

    /**
     * Saves the given state as the previous state.
     * @param state the state to save
     */
    public void saveState(Object state) {
        this.previousState = state;
    }

    /**
     * Restores the previously saved state.
     * @return the previous state
     */
    public Object restoreState() {
        return previousState;
    }

    /**
     * Returns previousState as per sequence diagram message m10.
     * This method explicitly implements the return message from StateSvc to Interactor.
     * @return the previous state
     */
    public Object returnPreviousState() {
        return previousState;
    }
}