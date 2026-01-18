package com.example.commentediting.output;

/**
 * Interface for the output port of the Edit Comment Use Case.
 * This interface defines how the results and feedback of the use case
 * are presented to the user interface or other external systems.
 */
public interface IEditCommentOutputPort {
    /**
     * Presents a success message to the user.
     *
     * @param message The success message.
     */
    void presentSuccess(String message);

    /**
     * Presents a validation error message to the user.
     *
     * @param errorMessage The validation error message.
     */
    void presentValidationError(String errorMessage);

    /**
     * Presents a confirmation prompt to the user, asking for further action.
     *
     * @param message The confirmation message.
     */
    void presentConfirmationPrompt(String message);

    /**
     * Presents a general error message to the user.
     *
     * @param errorMessage The error message.
     */
    void presentError(String errorMessage);

    /**
     * Presents the comment edit form to the user.
     * This method is used when a comment is selected for editing,
     * allowing the UI to display the current text for modification. (R4)
     *
     * @param commentId The ID of the comment being edited.
     * @param currentText The current text content of the comment.
     */
    void presentCommentEditForm(String commentId, String currentText);
}