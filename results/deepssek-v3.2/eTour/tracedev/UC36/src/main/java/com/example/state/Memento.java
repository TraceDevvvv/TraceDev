package com.example.state;

/**
 * Memento pattern: stores an object's internal state.
 */
public class Memento {
    private Object state;

    /**
     * Creates a memento with the given state.
     * @param state the state to store
     */
    public Memento(Object state) {
        this.state = state;
    }

    /**
     * Returns the stored state.
     * @return the stored state
     */
    public Object getState() {
        return state;
    }
}