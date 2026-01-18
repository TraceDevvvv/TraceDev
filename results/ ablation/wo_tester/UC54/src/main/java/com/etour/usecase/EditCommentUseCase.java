package com.etour.usecase;

import com.etour.dto.EditCommentRequestDTO;
import com.etour.dto.CommentResultDTO;

/**
 * Interface for the Edit Comment use case.
 */
public interface EditCommentUseCase {
    /**
     * Executes the edit comment use case.
     * @param request the request DTO.
     * @return the result DTO.
     */
    CommentResultDTO execute(EditCommentRequestDTO request);
}