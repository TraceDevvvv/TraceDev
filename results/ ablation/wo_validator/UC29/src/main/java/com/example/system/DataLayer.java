package com.example.system;

/**
 * Abstract data layer class.
 */
public abstract class DataLayer {
    /**
     * Retrieves system state.
     * @return the system state
     */
    public abstract SystemState retrieveState();

    /**
     * Persists system state.
     * @param state the system state to persist
     */
    public abstract void persistState(SystemState state);
}