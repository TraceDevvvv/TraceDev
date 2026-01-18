package com.etour.ui;

import com.etour.site.Site;

/**
 * Handles the display logic for a site card.
 * This class is responsible for formatting and presenting the details of a Site object to the user.
 */
public class SiteCardViewer {

    /**
     * Displays the detailed information of a given Site object.
     * This method formats the site's properties into a readable output.
     *
     * @param site The Site object whose details are to be displayed.
     */
    public void displaySiteDetails(Site site) {
        if (site == null) {
            System.out.println("No site details to display.");
            return;
        }

        System.out.println("\n--- Site Card Details ---");
        System.out.println("Site ID: " + site.getSiteId());
        System.out.println("Name: " + site.getName());
        System.out.println("Location: " + site.getLocation());
        System.out.println("Description: " + site.getDescription());
        System.out.printf("Coordinates: (%.4f, %.4f)\n", site.getLatitude(), site.getLongitude());
        System.out.println("Image URL: " + site.getImageUrl());
        System.out.printf("Rating: %.1f/5.0 (%d reviews)\n", site.getRating(), site.getNumberOfReviews());
        System.out.println("-------------------------\n");
    }
}