package com.example.controllers;

import com.example.usecases.EditCommentInputBoundary;
import com.example.dtos.EditCommentRequestDTO;

/**
 * Controller for the edit comment use case.
 * Part of the interface adapters layer.
 */
public class EditCommentController {
    private EditCommentInputBoundary interactor;

    /**
     * Constructs the controller with the interactor (use case input boundary).
     *
     * @param interactor the interactor to delegate to
     */
    public EditCommentController(EditCommentInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the edit comment request from the tourist (actor).
     * Creates the request DTO and passes it to the interactor.
     *
     * @param request the request data
     */
    public void editComment(EditCommentRequestDTO request) {
        interactor.execute(request);
    }
}