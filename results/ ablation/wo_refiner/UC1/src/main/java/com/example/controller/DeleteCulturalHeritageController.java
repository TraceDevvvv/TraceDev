package com.example.controller;

import com.example.service.AuthenticationService;
import com.example.interactor.DeleteCulturalHeritageInteractor;
import com.example.request.DeleteCulturalHeritageRequest;
import com.example.response.DeleteCulturalHeritageResponse;
import com.example.view.DeleteCulturalHeritageView;

/**
 * Controller that orchestrates the delete cultural heritage use case.
 */
public class DeleteCulturalHeritageController {
    private DeleteCulturalHeritageInteractor interactor;
    private AuthenticationService authService;

    public DeleteCulturalHeritageController(DeleteCulturalHeritageInteractor interactor, AuthenticationService authService) {
        this.interactor = interactor;
        this.authService = authService;
    }

    public void execute(DeleteCulturalHeritageRequest request) {
        // The view should have validated session before calling controller,
        // but we double-check here for safety.
        if (!authService.validateSession("dummySessionId")) {
            // In a real app, we would propagate the error to the view.
            System.err.println("Session invalid in controller.");
            return;
        }
        DeleteCulturalHeritageResponse response = interactor.execute(request);
        // The view will handle displaying the response.
    }

    // Added method for the "Select CulturalHeritage and activate delete function" message.
    public void selectCulturalHeritageAndActivateDeleteFunction(int culturalHeritageId) {
        System.out.println("Selected CulturalHeritage ID: " + culturalHeritageId + " and activated delete function.");
    }

    // Added method for the "Submit confirmation with token" message.
    public void submitConfirmationWithToken(String token) {
        System.out.println("Submitted confirmation with token: " + token);
    }

    // Added method for the "Cancel operation" message.
    public void cancelOperation() {
        System.out.println("Operation cancelled.");
    }
}