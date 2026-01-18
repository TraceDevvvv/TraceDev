package com.etour.presentation;

import com.etour.dto.CommentResultDTO;
import com.etour.application.EditCommentController;

/**
 * UI boundary class (Presentation Layer) as per sequence diagram.
 */
public class PresentationLayer {
    private EditCommentController controller;

    public PresentationLayer(EditCommentController controller) {
        this.controller = controller;
    }

    /**
     * Simulates the UI event where tourist chooses to edit a comment.
     * Corresponds to sequence message "choose to edit comment".
     * @param commentId the comment ID.
     * @param newContent the new content.
     * @return the result DTO.
     */
    public CommentResultDTO chooseToEditComment(String commentId, String newContent) {
        // Delegates to controller
        return controller.editComment(commentId, newContent);
    }

    /**
     * Simulates displaying an error response.
     * Corresponds to sequence message "display error message".
     * @param errorMessage the error message.
     */
    public void displayErrorMessage(String errorMessage) {
        System.out.println("UI Error: " + errorMessage);
    }

    /**
     * Simulates displaying a success response.
     * Corresponds to sequence message "display confirmation and updated comment".
     * @param resultDTO the success result DTO.
     */
    public void displayConfirmation(CommentResultDTO resultDTO) {
        System.out.println("UI Success: " + resultDTO.getMessage());
        System.out.println("Updated comment: " + resultDTO.getUpdatedContent());
    }
}