package com.example.culturalassets.controller;

import com.example.culturalassets.dto.CulturalAssetDetailsDTO;
import com.example.culturalassets.usecase.ViewCulturalAssetDetailsUseCase;
import com.example.culturalassets.view.CulturalAssetView;
import com.example.culturalassets.service.AuthenticationService;
import com.example.culturalassets.exception.ConnectionFailedException; // Explicitly import if needed for specific catch

/**
 * Controller for handling requests related to cultural assets.
 * It coordinates interaction between the presentation layer (view),
 * application layer (use case), and external serv (authentication).
 */
public class CulturalAssetController {
    private ViewCulturalAssetDetailsUseCase viewCulturalAssetDetailsUseCase;
    private CulturalAssetView culturalAssetView;
    private AuthenticationService authenticationService;

    /**
     * Constructs a CulturalAssetController with necessary dependencies.
     * @param viewCulturalAssetDetailsUseCase The use case for viewing asset details.
     * @param culturalAssetView The view component for displaying information.
     * @param authenticationService The service for authenticating users.
     */
    public CulturalAssetController(ViewCulturalAssetDetailsUseCase viewCulturalAssetDetailsUseCase,
                                   CulturalAssetView culturalAssetView,
                                   AuthenticationService authenticationService) {
        this.viewCulturalAssetDetailsUseCase = viewCulturalAssetDetailsUseCase;
        this.culturalAssetView = culturalAssetView;
        this.authenticationService = authenticationService;
    }

    /**
     * Handles a request from the Agency Operator to view cultural asset details.
     * This method includes authentication and error handling as per the sequence diagram.
     * @param assetId The ID of the cultural asset to view.
     * @param sessionId The session ID of the requesting Agency Operator for authentication.
     */
    public void handleViewDetailsRequest(String assetId, String sessionId) {
        System.out.println("\nController: Received request to view details for asset ID: " + assetId);

        // REQ-001: Entry Conditions: 'Agency IS logged in'
        // Sequence Diagram Opt: Agency Operator is not logged in
        if (!authenticationService.isAuthenticated(sessionId)) {
            System.out.println("Controller: Authentication failed for session " + sessionId);
            // Simulate redirection to login page
            redirectToLoginPage();
            return;
        }

        try {
            // Delegate to the use case to get the asset details
            CulturalAssetDetailsDTO details = viewCulturalAssetDetailsUseCase.execute(assetId);

            // Display the details using the view
            culturalAssetView.displayAssetDetails(details);

        } catch (ConnectionFailedException e) {
            // Handle specific connection interruption as per Sequence Diagram
            // REQ-003: Exit Conditions: 'Interruption of the connection to the server ETOUR.'
            String errorMessage = "Failed to retrieve cultural asset details: Connection to ETOUR server interrupted. " + e.getMessage();
            System.err.println("Controller: Caught ConnectionFailedException. " + errorMessage);
            culturalAssetView.showErrorMessage(errorMessage);
        } catch (Exception e) {
            // Handle any other generic errors
            String errorMessage = "Failed to retrieve cultural asset details: An unexpected error occurred. " + e.getMessage();
            System.err.println("Controller: Caught generic Exception. " + errorMessage);
            culturalAssetView.showErrorMessage(errorMessage);
        }
    }

    /**
     * Simulates redirecting an unauthenticated user to a login page.
     */
    private void redirectToLoginPage() {
        System.out.println("Controller: Redirecting Agency Operator to login page...");
        // In a web application, this would be an HTTP redirect.
        // In a console application, this is just a message.
    }
}