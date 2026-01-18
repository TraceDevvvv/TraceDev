package com.etour.ui;

import com.etour.dto.ErrorDTO;
import com.etour.dto.SiteDTO;
import com.etour.presentation.SitePresentationController;

/**
 * Boundary class representing the user interface for site operations.
 */
public class SiteUI {
    private SitePresentationController presentationController;

    public SiteUI(SitePresentationController presentationController) {
        this.presentationController = presentationController;
    }

    /**
     * Initiates the site selection process.
     *
     * @param siteId the site ID
     */
    public void selectSite(String siteId) {
        try {
            SiteDTO siteDTO = presentationController.displaySiteCard(siteId);
            if (siteDTO != null) {
                displaySiteDetails(siteDTO);
            } else {
                showErrorMessage("Site not found");
            }
        } catch (Exception e) {
            // Requirement R9: Show connection error.
            showConnectionError();
        }
    }

    /**
     * Displays the site details to the user.
     *
     * @param siteDTO the site data
     */
    public void displaySiteDetails(SiteDTO siteDTO) {
        System.out.println("Site Details:");
        System.out.println("ID: " + siteDTO.getId());
        System.out.println("Name: " + siteDTO.getName());
        System.out.println("Description: " + siteDTO.getDescription());
        System.out.println("Location: " + siteDTO.getLocation());
        System.out.println("Rating: " + siteDTO.getRating());
    }

    /**
     * Shows an error message to the user.
     *
     * @param message the error message
     */
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Shows a connection error message.
     * Requirement R9: Server disconnection handling.
     */
    public void showConnectionError() {
        System.err.println("Connection error: Unable to connect to server. Please try again later.");
    }
}