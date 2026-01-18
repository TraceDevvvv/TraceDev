package com.example.usecases;

import com.example.dtos.EditCommentRequestDTO;
import com.example.dtos.EditCommentResponseDTO;

/**
 * Input boundary (interface) for the Edit Comment use case.
 * Part of the application layer.
 */
public interface EditCommentInputBoundary {
    /**
     * Executes the edit comment use case.
     *
     * @param request the request data
     * @return the response data
     */
    EditCommentResponseDTO execute(EditCommentRequestDTO request);
}