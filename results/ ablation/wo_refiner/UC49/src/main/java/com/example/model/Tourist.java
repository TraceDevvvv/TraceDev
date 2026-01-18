
package com.example.model;

import com.example.controller.ViewPreferredSiteController;

/**
 * Represents a Tourist user.
 * This class models a tourist who can initiate the view preferred site feature.
 */
public class Tourist {
    private String userId;

    public Tourist(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Checks if the tourist is authenticated.
     * For simplicity, returns true by default. In a real system, this would
     * involve checking against an authentication service.
     *
     * @return true if authenticated, false otherwise.
     */
    public boolean authenticated() {
        // Assumption: Always returns true for demonstration.
        // In a real implementation, this would check actual authentication.
        return true;
    }

    /**
     * Initiates the view preferred site feature.
     * This method is called when the tourist selects the "View Preferred Sites" option.
     */
    public void selectViewPreferredSiteFeature() {
        System.out.println("Tourist " + userId + " selects View Preferred Site feature.");
        ViewPreferredSiteController controller = new ViewPreferredSiteController();
        controller.initiateViewPreferredSite(userId);
    }

    /**
     * Displays the bookmarks list.
     */
    public void displayBookmarksList() {
        System.out.println("Tourist: Display bookmarks list");
    }

    /**
     * Displays "No favorites found" message.
     */
    public void displayNoFavoritesFound() {
        System.out.println("Tourist: Display \"No favorites found\"");
    }

    /**
     * Handles authentication error.
     */
    public void handleAuthenticationError() {
        System.out.println("Tourist: Authentication error");
    }

    /**
     * Displays "Authentication required" message.
     */
    public void displayAuthenticationRequired() {
        System.out.println("Tourist: Display \"Authentication required\"");
    }
}
