package com.example.service;

import com.example.model.Feedback;

/**
 * Interface for restoring application state.
 */
public interface StateRecoveryService {
    /**
     * Restores the state based on existing feedback.
     * @param feedback the feedback used for recovery.
     */
    void restoreState(Feedback feedback);

    /**
     * State restored.
     * Corresponds to sequence diagram message m18.
     */
    default void stateRestored(Feedback feedback) {
        restoreState(feedback);
    }
}