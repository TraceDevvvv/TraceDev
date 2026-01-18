package com.example.presenters;

import com.example.usecases.EditCommentOutputBoundary;
import com.example.dtos.EditCommentResponseDTO;
import java.util.List;

/**
 * Presenter for the edit comment use case.
 * Implements the output boundary and handles presentation logic.
 * In a real application, this would update a UI; here we simulate by printing to console.
 */
public class EditCommentPresenter implements EditCommentOutputBoundary {
    /**
     * Presents a successful response by printing to console.
     *
     * @param response the response DTO
     */
    @Override
    public void present(EditCommentResponseDTO response) {
        System.out.println("Success: " + response.getMessage());
        System.out.println("Comment ID: " + response.getCommentId());
        System.out.println("Updated content: " + response.getUpdatedContent());
    }

    /**
     * Presents validation errors by printing them to console.
     *
     * @param errors list of error messages
     */
    @Override
    public void presentValidationError(List<String> errors) {
        System.out.println("Validation errors:");
        for (String error : errors) {
            System.out.println(" - " + error);
        }
    }

    /**
     * Presents a server connection error by printing to console.
     */
    @Override
    public void presentServerConnectionError() {
        System.out.println("Error: Unable to connect to the server. Please try again later.");
    }
}