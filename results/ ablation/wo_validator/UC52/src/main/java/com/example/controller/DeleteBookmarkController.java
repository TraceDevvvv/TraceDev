package com.example.controller;

import com.example.usecase.DeleteBookmarkUseCase;
import com.example.dto.DeleteBookmarkRequest;
import com.example.dto.DeleteBookmarkResponse;
import com.example.dto.DeleteBookmarkCommand;

/**
 * Controller for handling delete bookmark requests.
 */
public class DeleteBookmarkController {
    private DeleteBookmarkUseCase deleteBookmarkUseCase;

    public DeleteBookmarkController(DeleteBookmarkUseCase useCase) {
        this.deleteBookmarkUseCase = useCase;
    }

    public DeleteBookmarkResponse handleRequest(DeleteBookmarkRequest request) {
        // Create command from request
        DeleteBookmarkCommand command = new DeleteBookmarkCommand(
            request.getUserId(),
            request.getSiteId(),
            request.getConfirmationToken()
        );
        
        // Execute use case
        com.example.dto.DeleteBookmarkResult result = deleteBookmarkUseCase.execute(command);
        
        // Create and return response
        return new DeleteBookmarkResponse(result.isSuccess(), result.getMessage());
    }
}