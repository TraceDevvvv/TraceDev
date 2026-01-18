package com.example.exception;

/**
 * Interface for state management.
 */
public interface StateManager {
    /**
     * Restores a previous state.
     * @param previousState The state to restore.
     */
    void restorePreviousState(State previousState);
}