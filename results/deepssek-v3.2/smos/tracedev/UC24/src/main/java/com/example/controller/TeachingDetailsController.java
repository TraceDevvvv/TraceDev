package com.example.controller;

import com.example.authentication.AuthenticationService;
import com.example.dto.TeachingDetailsDTO;
import com.example.usecase.ViewTeachingDetailsUseCase;

/**
 * Controller for the teaching details screen.
 * Implements UC-ViewTeachingDetails traceability.
 */
public class TeachingDetailsController {
    private ViewTeachingDetailsUseCase useCase;
    private AuthenticationService authService;
    private String currentUserId; // Assumed from session

    public TeachingDetailsController(ViewTeachingDetailsUseCase useCase, AuthenticationService authService) {
        this.useCase = useCase;
        this.authService = authService;
        this.currentUserId = "admin123"; // hardcoded for example; in real app, set from session.
    }

    public void loadTeachingDetails(int teachingId) {
        // Verify authentication (pre-condition)
        if (!verifyAuthentication()) {
            // In a real scenario, redirect to login or show error.
            System.err.println("Error: Administrator not logged in.");
            return;
        }

        TeachingDetailsDTO details = useCase.execute(teachingId);
        if (details == null) {
            // Connection error or teaching not found is handled by useCase.
            // We rely on the UI to show the error from useCase (or we could handle here).
            return;
        }
        // In a real app, we would pass details to the UI via callback or observer.
        // For simplicity, we print a success message.
        System.out.println("Teaching details loaded successfully for ID: " + teachingId);
    }

    private boolean verifyAuthentication() {
        // Validates pre-condition: Administrator IS logged in
        return authService.validateSession(currentUserId);
    }
}