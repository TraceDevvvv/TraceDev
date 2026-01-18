package com.example;

/**
 * Represents the view responsible for displaying the form to edit a feedback comment
 * and handling user interaction for submission or cancellation.
 */
public class EditCommentView {
    private CommentModificationController controller;
    private FeedbackDTO currentFeedback; // Stores the feedback being edited
    private String newCommentText; // Stores the new comment entered by user

    /**
     * Sets the controller that this view will interact with.
     * @param controller The CommentModificationController instance.
     */
    public void setController(CommentModificationController controller) {
        this.controller = controller;
    }

    /**
     * Displays the edit form with the current feedback details.
     * @param feedback The FeedbackDTO containing the details of the feedback to edit.
     */
    public void displayEditForm(FeedbackDTO feedback) {
        this.currentFeedback = feedback;
        System.out.println("\n--- Edit Feedback Comment ---");
        if (feedback == null) {
            System.out.println("Feedback details not available for editing.");
            return;
        }
        System.out.println("Feedback ID: " + feedback.id);
        System.out.println("Site Name: " + feedback.siteName);
        System.out.println("Current Comment: '" + feedback.comment + "'");
        System.out.println("Status: " + feedback.status);
        System.out.println("-----------------------------");
    }

    /**
     * Simulates showing the edit comment form to the operator.
     * In a real UI, this would render a screen.
     */
    public void showEditCommentForm() {
        System.out.println("Please enter the new comment below or type 'cancel' to return without changes:");
    }

    /**
     * Simulates the operator entering a new comment.
     * @param newCommentText The text entered by the operator.
     */
    public void enterNewComment(String newCommentText) {
        this.newCommentText = newCommentText;
        System.out.println("EditCommentView: User entered new comment: '" + newCommentText + "'");
    }

    /**
     * Simulates the operator submitting the form.
     * This method would typically be called by a 'Submit' button click.
     */
    public void submitForm() {
        if (controller != null && currentFeedback != null && newCommentText != null) {
            controller.submitEditedComment(currentFeedback.id, newCommentText);
        } else {
            System.err.println("Error: Cannot submit form, controller/feedback/comment not set.");
            showError("Failed to submit comment. Missing data.");
        }
    }

    /**
     * Displays a confirmation prompt to the operator.
     */
    public void showConfirmationPrompt() {
        System.out.println("\n--- Confirm Change ---");
        System.out.println("Are you sure you want to change the comment to: '" + newCommentText + "'?");
        System.out.println("Type 'yes' to confirm, or 'no' to cancel.");
    }

    /**
     * Prompts the operator for confirmation.
     */
    public void promptConfirmation() {
        // This is primarily for the sequence diagram, actual logic is in showConfirmationPrompt()
        // and MainApplication handling input.
    }

    /**
     * Simulates the operator confirming the change.
     * This method would typically be called by a 'Confirm' button click.
     */
    public void confirmChange() {
        if (controller != null && currentFeedback != null && newCommentText != null) {
            CommentEditDTO editDto = new CommentEditDTO(currentFeedback.id, newCommentText);
            controller.confirmCommentChange(editDto);
        } else {
            System.err.println("Error: Cannot confirm change, controller/feedback/comment not set.");
            showError("Failed to confirm change. Missing data.");
        }
    }

    /**
     * Simulates the operator cancelling the operation.
     * This method would typically be called by a 'Cancel' button click.
     */
    public void cancelOperation() {
        if (controller != null) {
            controller.cancelCommentChange();
        } else {
            System.err.println("Error: Cannot cancel operation, controller not set.");
            showError("Failed to cancel. Controller not set.");
        }
    }

    /**
     * Displays a success message to the operator.
     * @param message The success message to display.
     */
    public void showSuccessMessage(String message) {
        System.out.println("EditCommentView Success: " + message);
    }

    /**
     * Displays an error message to the operator.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.err.println("EditCommentView Error: " + message);
    }

    /**
     * Placeholder for displaying a general success.
     * In a real UI, this would show a success dialog.
     */
    public void displaySuccess() {
        System.out.println("EditCommentView: Operation successful!");
    }

    /**
     * Placeholder for displaying a general error.
     * In a real UI, this would show an error dialog.
     */
    public void displayError() {
        System.err.println("EditCommentView: An error occurred during comment modification.");
    }
}