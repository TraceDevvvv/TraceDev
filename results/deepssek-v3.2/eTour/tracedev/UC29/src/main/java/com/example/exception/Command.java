package com.example.exception;

/**
 * Abstract command class following the Command pattern.
 */
public abstract class Command {
    protected State state;

    /**
     * Executes the command.
     */
    public abstract void execute();

    /**
     * Undoes the command.
     */
    public abstract void undo();

    /**
     * Gets the state associated with this command.
     * @return The State.
     */
    public State getState() {
        return state;
    }
}