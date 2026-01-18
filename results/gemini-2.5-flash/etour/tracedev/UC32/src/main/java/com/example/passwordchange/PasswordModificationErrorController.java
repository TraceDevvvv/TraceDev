package com.example.passwordchange;

/**
 * Controller responsible for handling password modification errors.
 * It manages displaying error messages, clearing them, and navigating the user.
 */
public class PasswordModificationErrorController {
    private PasswordChangeModel model;
    private PasswordChangeView view;
    private ApplicationNavigator navigator;

    /**
     * Constructor for PasswordModificationErrorController.
     *
     * @param model     The {@link PasswordChangeModel} to update with error messages.
     * @param view      The {@link PasswordChangeView} to display error messages.
     * @param navigator The {@link ApplicationNavigator} to navigate after error handling.
     */
    public PasswordModificationErrorController(PasswordChangeModel model, PasswordChangeView view, ApplicationNavigator navigator) {
        this.model = model;
        this.view = view;
        this.navigator = navigator;
        System.out.println("PMEC: Initialized.");
    }

    /**
     * Handles the specific case where password confirmation is invalid.
     * Sets an appropriate error message in the model and displays it via the view.
     */
    public void handleInvalidConfirmationError() {
        System.out.println("PMEC: Handling invalid confirmation error.");
        // R10: "Password confirmation does not match. Please re-enter both fields carefully."
        String errorMessage = "Password confirmation does not match. Please re-enter both fields carefully.";
        model.setCurrentErrorMessage(errorMessage);
        view.showErrorMessage(model.getCurrentErrorMessage());
    }

    /**
     * Handles the user's acknowledgment of an error message.
     * Clears the error and returns the user to the password change screen.
     */
    public void handleUserAcknowledgment() {
        System.out.println("PMEC: Handling user acknowledgment.");
        model.clearErrorMessage();
        navigator.showPasswordChangeScreen();
        // The sequence diagram shows PCC calls displayForm(Model) after PMEC returns.
        // So, PMEC itself doesn't directly call displayForm here. It's done by the PCC
        // after PMEC completes its task.
    }
}