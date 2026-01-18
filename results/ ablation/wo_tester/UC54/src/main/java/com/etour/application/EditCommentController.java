package com.etour.application;

import com.etour.dto.EditCommentRequestDTO;
import com.etour.dto.CommentResultDTO;
import com.etour.usecase.EditCommentUseCase;

/**
 * Use case controller for editing comments.
 */
public class EditCommentController {
    private EditCommentUseCase editCommentUseCase;

    public EditCommentController(EditCommentUseCase editCommentUseCase) {
        this.editCommentUseCase = editCommentUseCase;
    }

    /**
     * Initiates the edit comment flow as per sequence diagram.
     * @param commentId the ID of the comment to edit.
     * @param newContent the new content.
     * @return the result DTO.
     */
    public CommentResultDTO editComment(String commentId, String newContent) {
        // In a real scenario, touristId would be obtained from session/context.
        // We assume a placeholder touristId for simplicity.
        String touristId = "tourist-123";
        EditCommentRequestDTO requestDTO = new EditCommentRequestDTO(touristId, commentId, newContent);
        return editCommentUseCase.execute(requestDTO);
    }
}