package com.etour.model;

/**
 * Represents a tourist user.
 * Requirement R4: Validate location before displaying site card.
 */
public class Tourist {
    private String currentArea;

    public Tourist(String currentArea) {
        this.currentArea = currentArea;
    }

    /**
     * Authenticates the tourist.
     *
     * @return true if authenticated, false otherwise
     */
    public boolean authenticate() {
        // For simulation, always return true.
        return true;
    }

    /**
     * Selects a site (delegates to UI).
     *
     * @param siteId the site ID
     */
    public void selectSite(String siteId) {
        // In a real scenario, this would trigger the UI.
        // For simplicity, we just print a message.
        System.out.println("Tourist selecting site: " + siteId);
    }

    /**
     * Validates the current location.
     * Requirement R4: Validate location before displaying site card.
     *
     * @param area the area to validate
     * @return true if location is valid, false otherwise
     */
    public boolean validateLocation(String area) {
        // For simulation, compare with the tourist's current area.
        return currentArea != null && currentArea.equals(area);
    }
}