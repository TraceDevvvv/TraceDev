package com.example.commentediting.view;

/**
 * Simple console-based implementation of ICommentView for demonstration purposes.
 * Displays messages directly to the console.
 */
public class CommentView implements ICommentView {
    @Override
    public void showSuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    @Override
    public void showError(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }

    @Override
    public void showConfirmationPrompt(String message) {
        System.out.println("PROMPT: " + message);
    }

    @Override
    public void showCommentEditForm(String commentId, String currentText) {
        System.out.println("--- EDIT COMMENT FORM ---");
        System.out.println("Comment ID: " + commentId);
        System.out.println("Current Text: \"" + currentText + "\"");
        System.out.println("Please enter new text.");
        System.out.println("-------------------------");
    }
}