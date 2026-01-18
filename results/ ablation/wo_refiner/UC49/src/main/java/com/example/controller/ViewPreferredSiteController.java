package com.example.controller;

import com.example.dto.SiteDTO;
import com.example.repository.SiteRepository;
import com.example.repository.SiteRepositoryImpl;
import com.example.display.SiteDisplay;
import java.util.List;

/**
 * Controller for the View Preferred Site use case.
 * Manages the flow of viewing preferred sites for a tourist.
 */
public class ViewPreferredSiteController {
    private SiteRepository siteRepository;
    private SiteDisplay siteDisplay;

    public ViewPreferredSiteController() {
        this.siteRepository = new SiteRepositoryImpl();
        this.siteDisplay = new SiteDisplay();
    }

    /**
     * Main execution method to get preferred sites for a user.
     * Includes latency requirement: target 2 seconds.
     *
     * @param userId the ID of the tourist
     * @return List of SiteDTOs representing preferred sites
     */
    public List<SiteDTO> execute(String userId) {
        long startTime = System.currentTimeMillis();
        // Verify authentication first
        if (!verifyAuthentication(userId)) {
            siteDisplay.displayNoFavoritesMessage();
            // Return empty list if not authenticated
            return List.of();
        }

        // Fetch bookmarks from repository
        List<SiteDTO> sites = siteRepository.uploadBookmarks(userId);

        // Display the results
        if (sites.isEmpty()) {
            siteDisplay.displayNoFavoritesMessage();
        } else {
            siteDisplay.displayBookmarks(sites);
        }

        long endTime = System.currentTimeMillis();
        long latency = endTime - startTime;
        System.out.println("Controller execution latency: " + latency + " ms");
        // Latency requirement check (2 seconds)
        if (latency > 2000) {
            System.out.println("Warning: Latency exceeds 2 seconds requirement.");
        }
        return sites;
    }

    /**
     * Verifies that the user is authenticated.
     * This is a stub implementation; in reality, would call an authentication service.
     *
     * @param userId the ID of the tourist
     * @return true if authenticated, false otherwise
     */
    public boolean verifyAuthentication(String userId) {
        // Assumption: For demonstration, we consider a user authenticated if userId is not null/empty.
        return userId != null && !userId.trim().isEmpty();
    }

    /**
     * Initiates the view preferred site flow.
     * This method is called by the Tourist to start the process.
     *
     * @param userId the ID of the tourist
     */
    public void initiateViewPreferredSite(String userId) {
        System.out.println("Initiating view preferred site for user: " + userId);
        execute(userId);
    }

    /**
     * Handles connection interruption.
     */
    public void handleConnectionInterruption() {
        System.out.println("Controller: Handle connection interruption");
    }

    /**
     * Returns an empty List<SiteDTO>.
     *
     * @return empty List<SiteDTO>
     */
    public List<SiteDTO> returnEmptyList() {
        return List.of();
    }
}