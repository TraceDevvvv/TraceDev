package com.example.presentation;

import com.example.application.model.Site;

/**
 * View in MVC pattern.
 * Displays site information and handles user interactions.
 */
public class SiteDetailsView {
    private SiteDetailsController controller;

    /**
     * Constructor.
     *
     * @param controller the controller to handle user actions
     */
    public SiteDetailsView(SiteDetailsController controller) {
        this.controller = controller;
    }

    /**
     * Simulates the tourist selecting a site card.
     * This method is called by the UI framework (or test) when a site is selected.
     *
     * @param siteId the id of the selected site
     */
    public void selectSiteCard(String siteId) {
        // Show loading indicator as per sequence diagram
        showLoading();
        // Delegate to controller
        Site site = controller.onSiteSelected(siteId);
        if (site != null) {
            displaySiteCard(site);
        } else {
            showError("Connection failed");
        }
    }

    /**
     * Displays the site card with site details.
     *
     * @param site the Site object to display
     */
    public void displaySiteCard(Site site) {
        System.out.println("Displaying site card:");
        System.out.println("ID: " + site.getId());
        System.out.println("Name: " + site.getName());
        System.out.println("Description: " + site.getDescription());
        System.out.println("Location: " + site.getLocation());
        System.out.println("Image URL: " + site.getImageUrl());
    }

    /**
     * Shows a loading indicator.
     */
    public void showLoading() {
        System.out.println("Loading...");
    }

    /**
     * Shows an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }
}