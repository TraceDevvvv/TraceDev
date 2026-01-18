package com.example.commentediting.view;

/**
 * Interface for the Comment View.
 * Defines methods for displaying various messages and forms related to comments
 * in the user interface.
 */
public interface ICommentView {
    /**
     * Displays a success message to the user.
     *
     * @param message The success message.
     */
    void showSuccess(String message);

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message.
     */
    void showError(String errorMessage);

    /**
     * Displays a confirmation prompt to the user.
     *
     * @param message The confirmation prompt message.
     */
    void showConfirmationPrompt(String message);

    /**
     * Displays the comment edit form, pre-filled with current comment details.
     * (R4)
     *
     * @param commentId The ID of the comment to edit.
     * @param currentText The current text of the comment.
     */
    void showCommentEditForm(String commentId, String currentText);
}