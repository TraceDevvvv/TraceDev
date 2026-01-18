package com.example.touristapp.presentation;

import com.example.touristapp.application.SiteService;
import com.example.touristapp.dataaccess.NetworkConnectionException;
import com.example.touristapp.dto.SiteFeedbackDto;

import java.util.List;

/**
 * The controller for handling site-related requests from the UI.
 * It acts as an intermediary between the UI and the application service layer.
 */
public class SiteController {
    private SiteService siteService;
    private TouristAppUI touristAppUI; // Controller "uses" UI to display results/errors

    /**
     * Constructs a SiteController with its dependencies.
     *
     * @param siteService The {@link SiteService} to interact with the application layer.
     * @param touristAppUI The {@link TouristAppUI} to display results or errors.
     */
    public SiteController(SiteService siteService, TouristAppUI touristAppUI) {
        this.siteService = siteService;
        this.touristAppUI = touristAppUI;
    }

    /**
     * Handles the request from the UI to display visited sites with feedback.
     * This method assumes the user is authenticated (Satisfies REQ-003).
     * Handles REQ-004: Tourist selects feature.
     * It orchestrates the call to the service layer and handles potential network errors.
     *
     * @param touristId The ID of the authenticated tourist.
     */
    public void handleDisplayVisitedSitesRequest(String touristId) {
        System.out.println("DEBUG: SiteController: Handling display visited sites request for tourist: " + touristId);
        try {
            // Sequence Diagram: Controller -> Service: getVisitedSitesWithFeedback
            List<SiteFeedbackDto> siteFeedbackDtos = siteService.getVisitedSitesWithFeedback(touristId);

            // Sequence Diagram: Controller -> UI: displayVisitedSites
            touristAppUI.displayVisitedSites(siteFeedbackDtos);
        } catch (NetworkConnectionException e) {
            // Sequence Diagram: Service --x Controller: throws NetworkConnectionException
            // Sequence Diagram: Controller -> Controller: catch(NetworkConnectionException e)
            System.err.println("DEBUG: SiteController caught NetworkConnectionException: " + e.getMessage());
            // Sequence Diagram: Controller -> UI: handleNetworkError(e)
            handleNetworkError(e); // Delegate error display to UI
        }
    }

    /**
     * Handles network connection errors by delegating to the UI for display.
     * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
     * This error handling contributes to REQ-008: Ensuring reliable display.
     *
     * @param exception The {@link NetworkConnectionException} that occurred.
     */
    public void handleNetworkError(NetworkConnectionException exception) {
        System.err.println("DEBUG: SiteController calling UI to display network error.");
        // Sequence Diagram: UI -> UI: displayErrorMessage("Network connection lost. Please try again.")
        touristAppUI.displayErrorMessage("Network connection lost. Please try again. Details: " + exception.getMessage());
    }
}