package com.system;

/**
 * StateManager class managing current and previous system states.
 * Implements StateMemento interface.
 * Methods: saveState, recoverPreviousState, saveStateToStore.
 */
public class StateManager implements StateMemento {
    private SystemState currentState;
    private SystemState previousState;
    private SystemStateStore stateStore;

    public StateManager(SystemStateStore stateStore) {
        this.stateStore = stateStore;
        this.currentState = new SystemState("initial");
        this.previousState = null;
    }

    @Override
    public SystemState getState() {
        return currentState;
    }

    @Override
    public void restore(SystemState state) {
        this.previousState = this.currentState;
        this.currentState = state;
    }

    public SystemState saveState() {
        previousState = currentState;
        currentState = new SystemState("state_" + System.currentTimeMillis());
        return currentState;
    }

    public SystemState recoverPreviousState() {
        if (previousState != null) {
            currentState = previousState;
        }
        return currentState;
    }

    public void saveStateToStore(SystemState state) {
        stateStore.save(state);
    }

    // Method for message m20: StateStore -> StateMgr, "previous SystemState"
    public SystemState getPreviousSystemState() {
        return previousState;
    }

    // Method for message m21: StateMgr -> UI, "previous SystemState"
    public SystemState sendPreviousSystemState() {
        return previousState;
    }
}