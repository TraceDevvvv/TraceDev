package com.etour.presentation;

import com.etour.application.dto.SiteDto;
import com.etour.application.usecase.ViewVisitedSitesUseCase;
import com.etour.infrastructure.exception.ConnectionError;
import java.util.List;

/**
 * Controller handling the "View Visited Sites" feature.
 * Manages authentication, delegates to use case, and renders results.
 */
public class ViewVisitedSitesController {
    private ViewVisitedSitesUseCase useCase;
    private AuthenticationService authService;

    public ViewVisitedSitesController(ViewVisitedSitesUseCase useCase, AuthenticationService authService) {
        this.useCase = useCase;
        this.authService = authService;
    }

    /**
     * Entry point for the tourist to view visited sites.
     * Corresponds to sequence diagram start.
     */
    public void selectFeatureToDisplayVisitedSites() {
        // For demonstration, we assume the current tourist ID is known.
        // In a real system, this might come from session or user input.
        String currentTouristId = "tourist123";

        if (validateAuthentication()) {
            try {
                List<SiteDto> sites = useCase.execute(currentTouristId);
                renderSiteList(sites);
                // In a real UI, this would update the view
                System.out.println("Visited sites displayed successfully.");
            } catch (ConnectionError e) {
                showErrorMessage("Connection lost");
                System.out.println("Display error message to tourist: Failed to load sites");
            } catch (Exception e) {
                showErrorMessage("Unexpected error: " + e.getMessage());
                System.out.println("Display error message to tourist: Failed to load sites");
            }
        } else {
            showAuthenticationError();
            System.out.println("Display authentication error to tourist.");
        }
    }

    /**
     * Validates the tourist's authentication.
     * @return true if authenticated, false otherwise.
     */
    public boolean validateAuthentication() {
        // For simplicity, we use a hardcoded tourist ID; real implementation would get it from context.
        String currentTouristId = "tourist123";
        return authService.authenticate(currentTouristId);
    }

    /**
     * Renders the list of visited sites for display.
     * @param sites List of SiteDto objects
     */
    public void renderSiteList(List<SiteDto> sites) {
        // Format and prepare site list for display (REQ-008)
        System.out.println("=== Visited Sites List ===");
        for (SiteDto site : sites) {
            System.out.println("Site: " + site.getName() + " at " + site.getLocation());
        }
        System.out.println("=========================");
    }

    /**
     * Shows an error message (e.g., connection lost).
     * @param message Error message to display.
     */
    public void showErrorMessage(String message) {
        // In a real application, this would update the UI error state.
        System.err.println("Error: " + message);
    }

    /**
     * Shows authentication error.
     */
    public void showAuthenticationError() {
        // In a real application, this would show an authentication failure message.
        System.err.println("Authentication failed. Please log in.");
    }

    /**
     * Display visited sites for a given tourist ID.
     * @param touristId ID of the tourist
     */
    public void displayVisitedSites(String touristId) {
        if (validateAuthentication()) {
            try {
                List<SiteDto> sites = useCase.execute(touristId);
                renderSiteList(sites);
                System.out.println("Visited sites displayed successfully for tourist: " + touristId);
            } catch (ConnectionError e) {
                showErrorMessage("Connection lost");
                System.out.println("Display error message to tourist: Failed to load sites");
            } catch (Exception e) {
                showErrorMessage("Unexpected error: " + e.getMessage());
                System.out.println("Display error message to tourist: Failed to load sites");
            }
        } else {
            showAuthenticationError();
            System.out.println("Display authentication error to tourist.");
        }
    }

    public void displayVisitedSitesList() {
        String currentTouristId = "tourist123";
        displayVisitedSites(currentTouristId);
    }
}