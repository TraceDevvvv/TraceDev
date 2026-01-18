package com.example.bookmarking.presentation;

import com.example.bookmarking.application.BookmarkApplicationService;
import com.example.bookmarking.domain.Bookmark;
import com.example.bookmarking.domain.Site;

/**
 * Represents the User Interface for the Tourist, handling interactions
 * related to bookmarking sites.
 */
public class TouristUI {
    /** The currently selected site, representing the TouristCard's location (R3). */
    public Site currentSite;
    /** The application service responsible for bookmark operations. */
    private BookmarkApplicationService bookmarkApplicationService;

    /**
     * Constructs a TouristUI instance with a dependency on BookmarkApplicationService.\n     *
     * @param bookmarkApplicationService The application service to handle bookmark logic.
     */
    public TouristUI(BookmarkApplicationService bookmarkApplicationService) {
        this.bookmarkApplicationService = bookmarkApplicationService;
    }

    /**
     * Activates the bookmark insertion feature for a given site.
     * This method is triggered when a tourist indicates interest in bookmarking a site (R4).
     *\n     * @param site The site object that the tourist wishes to bookmark.
     */
    public void activateInsertionFeature(Site site) {
        this.currentSite = site; // Store the site for subsequent actions
        System.out.println("\nTouristUI: activateInsertionFeature called for site: " + site.name);
        // Step 1: UI displays a prompt to the user
        displayBookmarkInsertionPrompt();
    }

    /**
     * Displays a prompt to the tourist, asking for confirmation to insert a bookmark.
     */
    public void displayBookmarkInsertionPrompt() {
        System.out.println("TouristUI: Do you want to bookmark '" + currentSite.name + "'? (Y/N)");
    }

    /**
     * Confirms the bookmark insertion based on tourist's input.
     * This method simulates the tourist's confirmation and initiates the bookmarking process (R6).
     *\n     * @param touristId The ID of the currently logged-in tourist.
     */
    public void confirmBookmarkInsertion(String touristId) {
        System.out.println("TouristUI: Tourist confirmed bookmark insertion for site: " + currentSite.name);
        System.out.println("TouristUI: Tourist's ID is available: " + touristId); // Note from SD

        // Delegate the actual bookmark creation and persistence to the Application Service
        Bookmark newBookmark = bookmarkApplicationService.addBookmark(currentSite.getSiteId(), touristId);

        // Display confirmation to the user
        displayInsertionConfirmation(newBookmark);
        displayNotification("Bookmark saved!"); // Exit Condition from SD
    }

    /**
     * Displays a confirmation message to the tourist after a bookmark has been inserted.
     *\n     * @param bookmark The bookmark that was successfully created.
     */
    public void displayInsertionConfirmation(Bookmark bookmark) {
        System.out.println("TouristUI: Successfully bookmarked '" + currentSite.name + "'!");
        System.out.println("TouristUI: Bookmark Details: " + bookmark);
        System.out.println("TouristUI: Notification about the insertion site to favorites is displayed (Exit Condition).");
    }

    /**
     * Displays a general notification message to the tourist.
     *\n     * @param message The message to be displayed.
     */
    public void displayNotification(String message) {
        System.out.println("TouristUI: [NOTIFICATION] " + message);
    }
}