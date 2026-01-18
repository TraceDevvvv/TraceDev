package com.etour.ui;

import com.etour.controller.InsertSiteController;

/**
 * Mobile app user interface.
 */
public class MobileAppUI {
    private InsertSiteController controller;

    public MobileAppUI(InsertSiteController controller) {
        this.controller = controller;
    }

    /**
     * Activates "Add to Bookmarks" feature (as per sequence diagram).
     */
    public void activateAddToBookmarks() {
        // This would be triggered by UI event.
        System.out.println("UI: Add to Bookmarks feature activated.");
    }

    public void displaySuccessMessage() {
        System.out.println("UI: Site added to favorites!");
    }

    public void displayErrorMessage() {
        System.out.println("UI: Operation failed. Please try again.");
    }

    public void displayConnectionError() {
        System.out.println("UI: Connection lost. Please check network.");
    }

    /**
     * Returns "Site added to favorites!" message (as per sequence diagram message m16).
     * @return the success message
     */
    public String getSuccessMessage() {
        return "Site added to favorites!";
    }

    /**
     * Returns "Operation failed. Please try again." message (as per sequence diagram message m19).
     * @return the failure message
     */
    public String getErrorMessage() {
        return "Operation failed. Please try again.";
    }

    /**
     * Returns "Connection lost. Please check network." message (as per sequence diagram message m24).
     * @return the connection error message
     */
    public String getConnectionErrorMessage() {
        return "Connection lost. Please check network.";
    }
}