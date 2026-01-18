package com.example.system;

/**
 * Command to recover previous system state.
 */
public class RecoverPreviousStateCommand implements Command {
    private SystemState previousState;
    private SystemState currentState;
    private SystemStateDAO stateDAO;

    public RecoverPreviousStateCommand(SystemStateDAO stateDAO) {
        this.stateDAO = stateDAO;
        this.currentState = stateDAO.retrieveState();
    }

    @Override
    public void execute() {
        System.out.println("RecoverPreviousStateCommand: Executing recovery...");
        previousState = stateDAO.rollbackToPreviousState();
        restoreState(previousState);
    }

    @Override
    public void undo() {
        System.out.println("RecoverPreviousStateCommand: Undoing recovery...");
        restoreState(currentState);
    }

    /**
     * Restores a specific system state.
     * @param state the state to restore
     */
    protected void restoreState(SystemState state) {
        stateDAO.persistState(state);
        System.out.println("RecoverPreviousStateCommand: Restored state - " + state.getSnapshotData());
    }
}