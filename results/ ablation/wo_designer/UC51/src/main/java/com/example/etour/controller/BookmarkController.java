package com.example.etour.controller;

import com.example.etour.model.Site;
import com.example.etour.model.Tourist;
import com.example.etour.service.EtourService;

/**
 * Controller handling the bookmark insertion use case.
 */
public class BookmarkController {
    private final EtourService etourService;
    private Tourist tourist;

    public BookmarkController(EtourService etourService) {
        this.etourService = etourService;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    /**
     * Main flow for inserting a site into bookmarks.
     * Steps:
     * 1. Tourist activates insertion (implicit by calling this method).
     * 2. System prompts inclusion (handled by promptInclusion).
     * 3. Tourist confirms (simulated by confirmation flag).
     * 4. System inserts the site.
     * 5. Notify tourist and disconnect.
     */
    public boolean insertSiteToBookmarks(boolean touristConfirmation) {
        // Entry condition check: Tourist card IS in a particular site
        if (tourist == null) {
            System.out.println("Error: Tourist not set.");
            return false;
        }
        Site currentSite = tourist.getCurrentSite();
        if (currentSite == null) {
            System.out.println("Error: Tourist is not at any site.");
            return false;
        }

        // Step 1: Activation is done by calling this method.

        // Step 2: System prompts the inclusion.
        promptInclusion(currentSite);

        // Step 3: Tourist confirms the input.
        if (!touristConfirmation) {
            System.out.println("Tourist cancelled the insertion.");
            return false;
        }

        // Step 4: System inserts the selected site in the list of bookmarks.
        boolean inserted = tourist.addBookmark(currentSite);
        if (!inserted) {
            System.out.println("Site is already bookmarked.");
            return false;
        }

        // Exit conditions:
        // Notify tourist about the insertion.
        etourService.notifyBookmarkAdded(tourist, currentSite);

        // Interrupt the connection to the server ETOUR.
        etourService.disconnect();

        System.out.println("Site successfully recorded in bookmarks.");
        return true;
    }

    private void promptInclusion(Site site) {
        System.out.println("System prompt: Add site '" + site.getName() + "' to bookmarks? (Confirm in the next step)");
    }
}