package com.example.controller;

import com.example.model.Site;
import com.example.model.Tourist;
import com.example.model.SiteFeatures;
import com.example.repository.IBookmarkRepository;
import com.example.serv.INotificationService;
import com.example.serv.SiteSelectionService;
import com.example.serv.LoggingService;

import java.util.List;

/**
 * Controller coordinating the deletion flow.
 */
public class DeleteSiteFromPreferencesController {
    private IBookmarkRepository bookmarkRepository;
    private SiteSelectionService siteSelectionService;
    private INotificationService notificationService;
    private LoggingService loggingService;

    public DeleteSiteFromPreferencesController(IBookmarkRepository repository,
                                              SiteSelectionService selectionService,
                                              INotificationService notificationService) {
        this.bookmarkRepository = repository;
        this.siteSelectionService = selectionService;
        this.notificationService = notificationService;
        // Assuming LoggingService is available; could be injected if needed.
        this.loggingService = new LoggingService();
    }

    /**
     * Main flow for deleting a site from preferences.
     * Follows the sequence diagram steps.
     * @param touristId ID of the tourist
     * @param features Features to identify the site
     * @return true if deletion succeeded, false otherwise
     */
    public boolean executeDeletionFlow(String touristId, SiteFeatures features) {
        loggingService.logOperation("executeDeletionFlow started", touristId);
        // Connection validation is performed by the repository (implicit via validateConnection).
        // We assume connection is valid; otherwise, repository will throw or handle.

        // Get available bookmarked sites (REQ-006)
        List<Site> availableSites = bookmarkRepository.getBookmarkedSites(touristId);
        if (availableSites == null || availableSites.isEmpty()) {
            System.out.println("No bookmarked sites found.");
            return false;
        }

        // Find site ID by features
        String siteId = bookmarkRepository.findSiteIdByFeatures(touristId, features);
        if (siteId == null || siteId.isEmpty()) {
            System.out.println("No site found matching the features.");
            return false;
        }

        // Select site by features using SiteSelectionService
        Site selectedSite = siteSelectionService.selectSiteByFeatures(availableSites, features);
        if (selectedSite == null) {
            System.out.println("Site selection failed.");
            return false;
        }

        // Display removal prompt (REQ-007)
        displayRemovalPrompt(selectedSite);

        // Simulate user interaction: promptForRemoval (sequence diagram m6)
        Tourist tourist = new Tourist(touristId);
        boolean userProceeds = tourist.promptForRemoval(); // Sequence diagram message
        if (!userProceeds) {
            tourist.cancelOperation();
            loggingService.logOperation("User cancelled after prompt", touristId);
            return false;
        }

        // Simulate user confirmation.
        boolean userConfirmed = tourist.confirmRemoval();

        if (userConfirmed) {
            boolean success = bookmarkRepository.removeSiteFromBookmarks(touristId, siteId);
            if (success) {
                notificationService.sendRemovalNotification(touristId, siteId);
                loggingService.logOperation("Site removed successfully", touristId);
                return true;
            } else {
                loggingService.logError("Failed to remove site from bookmarks.");
                return false;
            }
        } else {
            tourist.cancelOperation();
            loggingService.logOperation("User cancelled removal", touristId);
            return false;
        }
    }

    /**
     * Handles connection interruption (rollback and logging) as per sequence diagram note m19.
     */
    private void handleConnectionInterruption() {
        loggingService.logError("Connection to server interrupted.");
        // Perform any rollback if needed.
        System.out.println("Log error and notify system administrator");
        System.out.println("Rollback if needed");
    }

    /**
     * Displays removal prompt with site details (REQ-007).
     * In a real system, this would update UI.
     */
    public void displayRemovalPrompt(Site site) {
        System.out.println("Display removal prompt for site:");
        System.out.println("Name: " + site.getName());
        System.out.println("Description: " + site.getDescription());
        System.out.println("Features: " + site.getFeatures());
        // UI would show a confirmation dialog.
    }
}