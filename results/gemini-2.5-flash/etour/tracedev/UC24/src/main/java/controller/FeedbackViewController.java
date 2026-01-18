package com.example.controller;

import com.example.model.Feedback;
import com.example.model.Site;
import com.example.service.AuthenticationService;
import com.example.service.FeedbackService;
import com.example.service.NetworkConnectionException;
import com.example.view.FeedbackView;
import com.example.view.SiteListView;

import java.util.List;

/**
 * Controller for managing feedback-related interactions from the user interface.
 * It coordinates between the views and the application service.
 */
public class FeedbackViewController {
    private FeedbackService feedbackService;
    private SiteListView siteListView;
    private FeedbackView feedbackView;
    private AuthenticationService authenticationService; // Added to satisfy requirement Entry Conditions: Agency IS logged in

    // Internal state to keep track of the selected site, if needed for complex flows.
    // For this sequence, siteId is passed directly, so this might be redundant but useful for other interactions.
    private String currentlySelectedSiteId;

    /**
     * Constructs a FeedbackViewController with necessary serv and views.
     * @param service The FeedbackService instance.
     * @param siteView The SiteListView instance.
     * @param feedbackView The FeedbackView instance.
     * @param authService The AuthenticationService instance.
     */
    public FeedbackViewController(FeedbackService service, SiteListView siteView, FeedbackView feedbackView, AuthenticationService authService) {
        this.feedbackService = service;
        this.siteListView = siteView;
        this.feedbackView = feedbackView;
        this.authenticationService = authService;
        System.out.println("FeedbackViewController: Initialized.");

        // Register the site selection handler from the view
        this.siteListView.onSiteSelected(this::selectSite);
    }

    /**
     * Handles the request to display the list of sites.
     * This method includes authentication check as per sequence diagram.
     */
    public void requestSiteList() {
        System.out.println("FeedbackViewController: Received request to display site list.");
        // Entry Conditions: Agency IS logged in
        if (!authenticationService.isAuthenticated()) {
            System.out.println("FeedbackViewController: User not authenticated.");
            feedbackView.displayError("Please log in first to view site list.");
            return;
        }

        System.out.println("FeedbackViewController: User authenticated. Fetching site list.");
        try {
            List<Site> siteList = feedbackService.getAllSites();
            siteListView.displaySites(siteList);
        } catch (NetworkConnectionException e) {
            // handleRepositoryError for site list
            handleRepositoryError(e);
            feedbackView.displayError("Failed to retrieve site list due to connection issue.");
        }
    }

    /**
     * Handles the selection of a site from the SiteListView.
     * As per the sequence diagram, this method is called by SLV and then implicitly the AO
     * calls `viewFeedbackForSelectedSite`. For a more integrated UI flow,
     * this method could automatically trigger `viewFeedbackForSelectedSite(siteId)`.
     * Let's implement it to set the currently selected site and possibly trigger feedback view.
     *
     * @param siteId The ID of the selected site.
     */
    public void selectSite(String siteId) {
        System.out.println("FeedbackViewController: Site '" + siteId + "' selected. Storing for potential later use.");
        this.currentlySelectedSiteId = siteId;
        // The sequence diagram shows AO separately calling viewFeedbackForSelectedSite(siteId)
        // However, a typical UI flow might automatically display feedback upon selection.
        // For strict adherence to the sequence, I'll keep them separate, but this method can store the ID.
        // If we want to strictly follow the sequence where AO explicitly calls viewFeedbackForSelectedSite,
        // then this method might just store the ID or not exist as a public controller method that AO calls.
        // The diagram has `SLV -> FVC : selectSite(siteId)` and then `AO -> FVC : viewFeedbackForSelectedSite(siteId)`.
        // To make it runnable for demonstration, we will assume `selectSite` is an internal callback
        // from the `SiteListView` that updates `currentlySelectedSiteId`.
        // The `viewFeedbackForSelectedSite` will then be called explicitly by a hypothetical "AO".
        // Or, for a more direct interpretation of a UI where selecting a site immediately shows its feedback:
        // this.viewFeedbackForSelectedSite(siteId); // Uncomment for immediate feedback display upon selection
    }

    /**
     * Handles the request to view feedback for a specific site.
     * This method assumes a site has been selected or its ID is provided.
     * @param siteId The ID of the site whose feedback is to be viewed.
     */
    public void viewFeedbackForSelectedSite(String siteId) {
        System.out.println("FeedbackViewController: Received request to view feedback for site ID: " + siteId);
        // Entry Conditions: Agency IS logged in - (implicitly handled by requestSiteList, but good to check again for robustness)
        if (!authenticationService.isAuthenticated()) {
            System.out.println("FeedbackViewController: User not authenticated.");
            feedbackView.displayError("Please log in first to view site feedback.");
            return;
        }

        try {
            List<Feedback> feedbackList = feedbackService.viewSiteFeedback(siteId);
            feedbackView.displayFeedback(feedbackList);
        } catch (NetworkConnectionException e) {
            // handleRepositoryError for feedback
            handleRepositoryError(e);
            feedbackView.displayError("Failed to retrieve feedback due to connection issue.");
        }
    }

    /**
     * Handles repository-level exceptions, typically network-related.
     * This method is called when a NetworkConnectionException occurs in the service layer.
     * @param exception The NetworkConnectionException that occurred.
     */
    public void handleRepositoryError(Exception exception) {
        System.err.println("FeedbackViewController: Repository error occurred: " + exception.getMessage());
        // In a real application, logging, analytics, or more sophisticated error handling would happen here.
        // For this example, it just prints to console.
    }
}