package adapters;

import interfaces.ConfirmationHandler;

/**
 * Confirmation dialog implementing ConfirmationHandler (Flow step 9).
 */
public class ConfirmationDialog implements ConfirmationHandler {

    /**
     * Shows a confirmation dialog (simulated).
     * @param message the confirmation message.
     * @return true if confirmed, false if cancelled.
     */
    @Override
    public boolean requestConfirmation(String message) {
        System.out.println("Confirmation dialog: " + message);
        // Simulate user confirmation (always true for demonstration)
        return true;
    }

    /**
     * Alternative method for showing the dialog.
     * @param message the confirmation message.
     * @return true if confirmed, false if cancelled.
     */
    public boolean show(String message) {
        return requestConfirmation(message);
    }
}