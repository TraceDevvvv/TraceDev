package com.example.system;

/**
 * Data access object for system state persistence.
 */
public class SystemStateDAO extends DataLayer {
    private SystemState currentState;
    private SystemState previousState;

    public SystemStateDAO() {
        // Initialize with a default state for demonstration
        currentState = new SystemState(1, "Initial State");
        previousState = new SystemState(0, "Previous State");
    }

    @Override
    public SystemState retrieveState() {
        System.out.println("SystemStateDAO: Retrieving current state.");
        return currentState;
    }

    @Override
    public void persistState(SystemState state) {
        this.currentState = state;
        System.out.println("SystemStateDAO: Persisted state - " + state.getSnapshotData());
    }

    /**
     * Rolls back to previous state.
     * @return the previous state
     */
    public SystemState rollbackToPreviousState() {
        System.out.println("SystemStateDAO: Rolling back to previous state.");
        SystemState temp = currentState;
        currentState = previousState;
        previousState = temp;
        return previousState;
    }
}