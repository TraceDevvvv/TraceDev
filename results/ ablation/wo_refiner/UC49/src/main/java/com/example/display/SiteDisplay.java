package com.example.display;

import com.example.dto.SiteDTO;
import java.util.List;

/**
 * Displays site information to the tourist.
 * Added per requirement 8.
 */
public class SiteDisplay {
    /**
     * Displays the list of bookmarked sites.
     *
     * @param sites List of SiteDTOs to display
     */
    public void displayBookmarks(List<SiteDTO> sites) {
        System.out.println("=== Favorite Sites ===");
        for (SiteDTO site : sites) {
            System.out.println("Site: " + site.name + " (" + site.siteId + ")");
            System.out.println("Description: " + site.description);
            System.out.println();
        }
    }

    /**
     * Displays a message when no favorites are found or authentication fails.
     */
    public void displayNoFavoritesMessage() {
        System.out.println("No favorite sites found or authentication required.");
    }

    /**
     * Displays "Authentication required" message.
     */
    public void displayAuthenticationRequired() {
        System.out.println("Authentication required");
    }

    /**
     * Displays an empty list of sites.
     */
    public void displayEmptyList() {
        System.out.println("Empty List<SiteDTO>");
    }
}