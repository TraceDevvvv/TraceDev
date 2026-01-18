package com.example.tagmanagement;

/**
 * Controller responsible for handling user interactions related to tag management,
 * specifically orchestrating the error handling flow when an existing tag is attempted to be added.
 */
public class TagManagementController {
    // Dependencies injected via constructor
    private final AddExistingTagErrorHandlerUseCase errorUseCase;
    private final TagErrorView tagErrorView;

    /**
     * Constructs a TagManagementController with its required dependencies.
     *
     * @param errorUseCase The use case for handling existing tag errors.
     * @param tagErrorView The view component for displaying error messages.
     */
    public TagManagementController(AddExistingTagErrorHandlerUseCase errorUseCase, TagErrorView tagErrorView) {
        this.errorUseCase = errorUseCase;
        this.tagErrorView = tagErrorView;
    }

    /**
     * Handles the scenario where an attempt to add a tag reveals it already exists.
     * This is the entry point for the sequence diagram.
     *
     * Entry Conditions:
     * - User attempted to add tag
     * - Tag already exists in system
     */
    public void handleExistingTagError() {
        System.out.println("\n[Controller] handleExistingTagError() called.");
        // Display the error message through the view.
        // This is the interaction: Controller -> View : displayError()
        tagErrorView.displayError("Error: Tag already exists. Please choose a different name.");

        // After displaying the error, request user confirmation.
        // This initiates the user interaction that will eventually call back errorConfirmed().
        // This maps to the User-View interaction for confirmReading().
        tagErrorView.requestConfirmation();
    }

    /**
     * Called by the TagErrorView when the user confirms reading the error message.
     * This method proceeds with the error handling process.
     */
    public void errorConfirmed() {
        System.out.println("[Controller] User confirmation received via errorConfirmed().");
        // Delegate to the error handling use case to confirm the error read and recover state.
        // This is the interaction: Controller -> ErrorHandlerUC : confirmErrorRead()
        errorUseCase.confirmErrorRead();
    }

    /**
     * Called by the AddExistingTagErrorHandlerUseCase after the error has been fully handled
     * and the system state has been recovered.
     */
    public void errorHandled() {
        System.out.println("[Controller] Error handling process acknowledged as complete.");
        System.out.println("-----------------------------------------------------------");
        System.out.println("[Controller] Control returned to user interaction.");
        // Exit Conditions: Control IS returned to the user interaction.
    }
}