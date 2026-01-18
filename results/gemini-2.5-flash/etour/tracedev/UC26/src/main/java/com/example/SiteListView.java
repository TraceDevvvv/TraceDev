package com.example;

import java.util.List;

/**
 * Represents the view responsible for displaying a list of sites.
 * It provides methods to show site data and simulate user selection.
 */
public class SiteListView {
    private CommentModificationController controller;

    /**
     * Sets the controller that this view will interact with.
     * @param controller The CommentModificationController instance.
     */
    public void setController(CommentModificationController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of sites to the user.
     * @param sites A list of SiteDTO objects to display.
     */
    public void displaySites(List<SiteDTO> sites) {
        System.out.println("\n--- Available Sites ---");
        if (sites == null || sites.isEmpty()) {
            System.out.println("No sites found.");
            return;
        }
        for (SiteDTO site : sites) {
            System.out.println("ID: " + site.id + ", Name: " + site.name);
        }
        System.out.println("-----------------------");
    }

    /**
     * Simulates showing the site list to the operator.
     * In a real UI, this would render a screen.
     */
    public void showSiteList() {
        System.out.println("Please select a site ID from the list to view feedback.");
    }

    /**
     * Simulates the operator selecting a site.
     * This method would typically be called by UI event handlers.
     * @param siteId The ID of the selected site.
     */
    public void selectSite(String siteId) {
        System.out.println("SiteListView: User selected site ID: " + siteId);
        // Delegate to the controller to handle the selection
        if (controller != null) {
            controller.selectSite(siteId);
        } else {
            System.err.println("Error: Controller not set for SiteListView.");
        }
    }

    /**
     * Displays an error message to the operator.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.err.println("SiteListView Error: " + message);
    }

    /**
     * Placeholder for displaying a general error.
     * In a real UI, this would show an error dialog.
     */
    public void displayError() {
        System.err.println("SiteListView: An error occurred while displaying sites.");
    }
}