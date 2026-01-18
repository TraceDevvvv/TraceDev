package ui;

import application.ConventionFormDTO;
import application.SubmitConventionCommand;
import infrastructure.ConventionController;

/**
 * UI component for the Convention form.
 * Interacts with the ConventionController to load and submit forms.
 */
public class ConventionFormComponent {
    private final ConventionController conventionController;

    public ConventionFormComponent(ConventionController conventionController) {
        this.conventionController = conventionController;
    }

    /**
     * Loads the convention form for a given restaurant.
     * @param restaurantId the restaurant identifier
     * @return ConventionFormDTO containing form data
     */
    public ConventionFormDTO loadForm(String restaurantId) {
        // Simulate obtaining an authentication token (in real app, from UI context)
        String authToken = "sample-token";
        return conventionController.getConventionForm(restaurantId, authToken);
    }

    /**
     * Submits the filled convention form.
     * @param command the submit command
     * @return true if submission was successful
     */
    public boolean submitForm(SubmitConventionCommand command) {
        // Simulate obtaining an authentication token (in real app, from UI context)
        String authToken = "sample-token";
        boolean success = conventionController.submitConvention(command, authToken);
        if (success) {
            // Show confirmation dialog as per requirement Flow‑006
            boolean confirmed = displayConfirmationDialog("Confirm submission?");
            if (confirmed) {
                System.out.println("Operation completed successfully.");
                return true;
            } else {
                System.out.println("Submission cancelled by user.");
                return false;
            }
        }
        return false;
    }

    /**
     * Displays a confirmation dialog to the user.
     * Added for requirement Flow‑006.
     * @param message the message to display
     * @return true if user confirms, false otherwise
     */
    public boolean displayConfirmationDialog(String message) {
        // In a real UI, this would show a dialog.
        // For simulation, we assume the user confirms.
        System.out.println("Confirmation dialog: " + message);
        return true;
    }
}