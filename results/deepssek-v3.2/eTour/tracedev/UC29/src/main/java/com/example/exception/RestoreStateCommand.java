package com.example.exception;

/**
 * Concrete command to restore a state.
 */
public class RestoreStateCommand extends Command {

    /**
     * Constructor accepting a state to restore.
     * @param state The state to restore.
     */
    public RestoreStateCommand(State state) {
        this.state = state;
    }

    @Override
    public void execute() {
        System.out.println("RestoreStateCommand executing. Restoring state: " + state.getData());
        // In a real scenario, this would apply the state to the system.
    }

    @Override
    public void undo() {
        System.out.println("RestoreStateCommand undoing. Reverting state: " + state.getData());
        // In a real scenario, this would revert the state.
    }
}