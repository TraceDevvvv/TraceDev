package com.example.usecases;

import com.example.dtos.EditCommentResponseDTO;
import java.util.List;

/**
 * Output boundary (interface) for the Edit Comment use case.
 * Defines methods for presenting results, validation errors, and connection errors.
 */
public interface EditCommentOutputBoundary {
    /**
     * Presents a successful response.
     *
     * @param response the response DTO
     */
    void present(EditCommentResponseDTO response);

    /**
     * Presents validation errors.
     *
     * @param errors list of error messages
     */
    void presentValidationError(List<String> errors);

    /**
     * Presents a server connection error.
     */
    void presentServerConnectionError();
}