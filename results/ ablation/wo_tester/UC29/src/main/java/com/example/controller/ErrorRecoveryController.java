package com.example.controller;

import com.example.model.SearchError;
import com.example.model.SystemStateMemento;

/**
 * Controller for handling error recovery operations.
 */
public class ErrorRecoveryController {
    private StateManager stateManager;

    public ErrorRecoveryController() {
        this.stateManager = new StateManager();
    }

    public ErrorRecoveryController(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * Handles search errors as per sequence diagram.
     * Added to satisfy recommendation 3.
     */
    public void handleSearchError(SearchError error) {
        System.out.println("Handling search error: " + error.getErrorMessage());
        // Log error or perform other error handling
    }

    public boolean confirmErrorReading() {
        System.out.println("Error reading confirmed by controller");
        return true;
    }

    /**
     * Recovers previous system state.
     * Added quality requirement traceability.
     */
    public void recoverPreviousState() {
        System.out.println("Initiating recovery of previous state...");
        SystemStateMemento previousState = stateManager.getLastState();
        stateManager.restoreState(previousState);
        System.out.println("Recovery initiated");
    }

    /**
     * Logs confirmation as per sequence diagram message m6.
     */
    public void logConfirmation() {
        System.out.println("Logs confirmation: Error reading confirmed.");
    }

    /**
     * Logs recovery completion as per sequence diagram message m8.
     */
    public void logRecoveryCompletion() {
        System.out.println("Logs recovery completion: Previous state restored.");
    }
}