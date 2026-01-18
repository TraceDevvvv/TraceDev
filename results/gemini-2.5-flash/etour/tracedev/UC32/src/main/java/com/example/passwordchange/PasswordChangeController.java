package com.example.passwordchange;

import java.util.Map;

/**
 * Controller for the password change feature.
 * Manages the interaction between the PasswordChangeView, PasswordChangeModel,
 * delegates error handling to PasswordModificationErrorController, and uses
 * ApplicationNavigator for screen transitions.
 */
public class PasswordChangeController implements PasswordChangeViewListener {
    private PasswordChangeModel model;
    private PasswordChangeView view;
    private PasswordModificationErrorController errorController;
    private ApplicationNavigator navigator;

    /**
     * Constructor for PasswordChangeController.
     *
     * @param model         The {@link PasswordChangeModel} to manage password data.
     * @param view          The {@link PasswordChangeView} to display the UI.
     * @param errorController The {@link PasswordModificationErrorController} for error specific handling.
     * @param navigator     The {@link ApplicationNavigator} for navigation.
     */
    public PasswordChangeController(PasswordChangeModel model, PasswordChangeView view,
                                    PasswordModificationErrorController errorController,
                                    ApplicationNavigator navigator) {
        this.model = model;
        this.view = view;
        this.errorController = errorController;
        this.navigator = navigator;
        // The controller sets itself as the listener for view events to handle callbacks.
        this.view.setListener(this);
        System.out.println("PCC: Initialized.");
    }

    /**
     * Initiates the password change request process.
     * This method would typically be called when the user submits the form.
     */
    public void submitPasswordChangeRequest() {
        System.out.println("PCC: Submitting password change request.");

        // Assumption: In a real scenario, this would first get input from the view,
        // then update the model, and then validate.
        // For the sequence diagram, we start directly with model validation.
        // Let's assume the model's newPassword and confirmPassword are already set
        // (e.g., from a previous getUserInput() call by the controller).
        
        // Sequence Diagram Step: PCC -> Model : isValidConfirmation()
        if (!model.isValidConfirmation()) {
            System.out.println("PCC: Password confirmation is invalid. Delegating to error controller.");
            // Sequence Diagram Step: PCC -> PMEC : handleInvalidConfirmationError()
            errorController.handleInvalidConfirmationError();
            // After error is handled and displayed, the flow waits for user acknowledgment via View.
        } else {
            System.out.println("PCC: Password confirmation is valid. (Proceed with successful password change logic)");
            // Placeholder for successful password change logic not covered by this sequence diagram.
            // In a real app, this would involve calling a service layer to update the password.
        }
    }

    /**
     * Handles the event when the user acknowledges a password error.
     * This method is called via the {@link PasswordChangeViewListener} interface.
     */
    @Override
    public void onAcknowledgeError() {
        System.out.println("PCC: Received acknowledgment from View. Processing error acknowledgment.");
        acknowledgePasswordError();
    }

    /**
     * Processes the acknowledgment of a password error.
     * Delegates to the error controller and then refreshes the view.
     */
    public void acknowledgePasswordError() {
        System.out.println("PCC: acknowledgePasswordError() called.");
        // Sequence Diagram Step: PCC -> PMEC : handleUserAcknowledgment()
        errorController.handleUserAcknowledgment();

        // Sequence Diagram Step: PCC -> View : displayForm(Model)
        view.displayForm(model);
        System.out.println("PCC: Error acknowledgment flow completed.");
    }

    /**
     * Example method to simulate user input before submission.
     * Not directly part of the sequence diagram but necessary for a runnable example.
     * @param newPass
     * @param confirmPass
     */
    public void simulateUserInput(String newPass, String confirmPass) {
        System.out.println("\nPCC: Simulating user input for password change...");
        model.setNewPassword(newPass);
        model.setConfirmPassword(confirmPass);
        view.displayForm(model); // Display form with new inputs
    }

    /**
     * Main method to demonstrate the sequence diagram's flow.
     */
    public static void main(String[] args) {
        // 1. Setup all components
        PasswordChangeModel model = new PasswordChangeModel();
        PasswordChangeView view = new PasswordChangeView();
        ApplicationNavigator navigator = new ApplicationNavigator();
        PasswordModificationErrorController errorController = new PasswordModificationErrorController(model, view, navigator);
        PasswordChangeController controller = new PasswordChangeController(model, view, errorController, navigator);

        System.out.println("\n--- Scenario: Acknowledge Password Modification Error ---");
        System.out.println("Entry Condition: Password change request HAS been submitted, Password confirmation IS invalid.");

        // Simulate initial state where user enters passwords that don't match.
        controller.simulateUserInput("password123", "passwordXYZ");

        // 2. Simulate user submitting the form, leading to an invalid confirmation.
        // This triggers the first part of the sequence diagram: PCC -> Model: isValidConfirmation()
        System.out.println("\n--- Step 1: User submits form with invalid confirmation ---");
        controller.submitPasswordChangeRequest(); // This will trigger the invalid confirmation path

        // At this point, the View has displayed the error message.
        // The sequence diagram states: "2. User acknowledges the error message"
        System.out.println("\n--- Step 2: User acknowledges the displayed error ---");
        view.acknowledgeError(); // This will trigger the View -> PCC: acknowledgePasswordError() callback

        System.out.println("\n--- End of Scenario ---");

        System.out.println("\n--- Scenario: Valid Password Confirmation (brief) ---");
        controller.simulateUserInput("correctPass", "correctPass");
        controller.submitPasswordChangeRequest(); // This will trigger the valid confirmation path
    }
}