package com.example.handler;

import com.example.state.StateManager;
import com.example.view.LoginForm;
import com.example.model.FormState;

import javax.swing.JOptionPane;

/**
 * Handles errors and orchestrates recovery flow.
 */
public class ErrorHandler {
    private StateManager stateManager;

    /**
     * Constructs an ErrorHandler with a StateManager.
     * @param manager the state manager
     */
    public ErrorHandler(StateManager manager) {
        this.stateManager = manager;
    }

    /**
     * Notifies the user about an error and asks for confirmation.
     * Implements Flow-2: system asks user to confirm they've read the error.
     * @param errorMessage the error message to display
     * @return true if user confirms, false otherwise
     */
    public boolean notifyUser(String errorMessage) {
        // Display error message on the form (done via LoginForm.displayErrorMessage)
        // In this implementation, we assume the form is available via static reference or passed.
        // For simplicity, we'll rely on the form instance passed in later calls.
        // The actual display is handled by LoginForm.displayErrorMessage called from controller.
        // This method returns true as per sequence (user confirms).
        return true;
    }

    /**
     * Recovers the previous state using StateManager.
     */
    public void recoverState() {
        // Retrieve last memento and restore state.
        // In sequence: getLastMemento -> restoreState -> restoreFormState.
    }

    /**
     * Returns control to the controller after recovery.
     */
    public void returnControl() {
        // In this sequence, just a placeholder.
    }

    /**
     * Handles the error confirmation flow, implementing sequence diagram messages.
     * This method is called by the controller during error handling.
     */
    public void handleErrorConfirmation(LoginForm form) {
        // m13: requestErrorConfirmation()
        boolean confirmed = form.requestErrorConfirmation();
        if (confirmed) {
            // m14: Shows confirmation dialog (already shown by requestErrorConfirmation)
            // m15: Confirms reading notification (user's action)
            // Continue recovery flow
            recoverStateFromMemento(form);
            clearForm(form);
            returnToController();
        }
    }

    /**
     * Recovers state from last memento and restores form.
     */
    private void recoverStateFromMemento(LoginForm form) {
        Object restoredState = stateManager.restoreState(stateManager.getLastMemento());
        if (restoredState instanceof FormState) {
            FormState previousState = (FormState) restoredState;
            form.restoreFormState(previousState);
        }
    }

    /**
     * Clears the form after error handling.
     */
    private void clearForm(LoginForm form) {
        form.clearForm();
    }

    /**
     * Returns control to controller (sequence m23: Recovery complete).
     */
    private void returnToController() {
        // Control returns naturally to controller after error handling
    }
}