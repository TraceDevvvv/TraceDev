package com.example.tourism.presentation;

import com.example.tourism.application.SiteService;
import com.example.tourism.application.ETOURConnectionException;
import com.example.tourism.dto.SiteDetailsDTO;
import com.example.tourism.session.UserSession;
import com.example.tourism.domain.SiteSourceArea;

/**
 * Presentation Layer: Represents the UI component for displaying site details.
 * It interacts with the SiteService to retrieve data and with UserSession for authentication checks.
 */
public class SiteDetailsView {
    // Dependency on the SiteService for business logic and data retrieval
    private SiteService siteService;
    // Represents the area from which the site was selected, for contextual checks
    public SiteSourceArea sourceArea; // Public as per class diagram

    /**
     * Constructor for SiteDetailsView.
     * @param siteService The service layer dependency.
     * @param sourceArea The area from which the site selection originated.
     */
    public SiteDetailsView(SiteService siteService, SiteSourceArea sourceArea) {
        this.siteService = siteService;
        this.sourceArea = sourceArea;
    }

    /**
     * Displays the details of a site on the UI.
     * @param siteDetailsDTO Data Transfer Object containing site details.
     */
    public void displaySiteDetails(SiteDetailsDTO siteDetailsDTO) {
        System.out.println("-------------------------------------");
        System.out.println("Displaying Site Details:");
        System.out.println("ID: " + siteDetailsDTO.id);
        System.out.println("Name: " + siteDetailsDTO.name);
        System.out.println("Description: " + siteDetailsDTO.description);
        System.out.println("Location: " + siteDetailsDTO.location);
        System.out.println("Image URL: " + siteDetailsDTO.imageUrl);
        System.out.println("-------------------------------------");
    }

    /**
     * Handles the user's action of selecting a site.
     * This method orchestrates the interaction with the service layer and error handling.
     * Implements logic for REQ-EC01, REQ-EC02, REQ-EC03, REQ-EC04, REQ-FE03, REQ-EXC01.
     *
     * @param siteId The unique identifier of the selected site.
     */
    public void selectSite(String siteId) {
        System.out.println("\nTouristUI: Site selected with ID: " + siteId);

        // REQ-EC01: Check if Tourist is authenticated
        // REQ-EC02, REQ-EC03, REQ-EC04: Check if Tourist is in a valid source area
        if (!UserSession.isAuthenticated()) { // Using static method for simplicity for UserSession
            displayErrorMessage("User not authenticated. Please log in to view site details.");
            return;
        }

        // Validate source area
        if (sourceArea == null || !(sourceArea == SiteSourceArea.RESEARCH_RESULTS ||
                                   sourceArea == SiteSourceArea.VISITED_SITES ||
                                   sourceArea == SiteSourceArea.FAVORITES)) {
            displayErrorMessage("Invalid source area for site selection. Access denied.");
            return;
        }

        try {
            // Call the service layer to get site details
            System.out.println("TouristUI -> SiteService: getSiteDetails(" + siteId + ")");
            SiteDetailsDTO siteDetailsDTO = siteService.getSiteDetails(siteId);

            // Display the retrieved site details
            System.out.println("TouristUI <- SiteService: siteDetailsDTO received");
            displaySiteDetails(siteDetailsDTO);

        } catch (ETOURConnectionException e) {
            // REQ-EXC01, REQ-EXC02: Handle connection interruption
            System.err.println("TouristUI <- SiteService: ConnectionInterruptedException caught");
            displayErrorMessage("Connection to ETOUR interrupted: " + e.getMessage());
        } catch (Exception e) {
            // General error handling
            displayErrorMessage("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Displays an error message on the UI.
     * Implemented to satisfy Audit Recommendation for REQ-EXC02.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("--- ERROR ---");
        System.err.println("TouristUI: " + message);
        System.err.println("-------------");
    }
}