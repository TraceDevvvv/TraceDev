package com.example.state;

/**
 * Manages saving and restoring state using Memento.
 */
public class StateManager {
    private Memento memento;

    /**
     * Creates a StateManager with no initial memento.
     */
    public StateManager() {
        this.memento = null;
    }

    /**
     * Saves the given state into a memento.
     * @param state the state to save
     * @return the created memento
     */
    public Memento saveState(Object state) {
        this.memento = new Memento(state);
        return memento;
    }

    /**
     * Restores state from a memento.
     * @param memento the memento containing the state
     * @return the restored state object
     */
    public Object restoreState(Memento memento) {
        if (memento != null) {
            return memento.getState();
        }
        return null;
    }

    /**
     * Returns the last saved memento.
     * @return the last memento, or null if none
     */
    public Memento getLastMemento() {
        return memento;
    }
}