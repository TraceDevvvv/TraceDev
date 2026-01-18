package com.example.tourist;

/**
 * Service to handle displaying site card details.
 */
public class SiteCardService {
    private final SiteDatabase database;

    public SiteCardService(SiteDatabase database) {
        this.database = database;
    }

    /**
     * Displays the details of a selected site.
     * @param siteId the ID of the site to display
     * @return the Site object with details, or null if not found
     * @throws SiteDisplayException if there is an interruption (simulating server disconnection)
     */
    public Site displaySiteCard(String siteId) throws SiteDisplayException {
        // Check if the site ID is valid
        if (siteId == null || siteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Site ID cannot be null or empty.");
        }

        // Retrieve site from database
        Site site = database.getSiteById(siteId);

        // Simulate server interruption (randomly for demonstration)
        if (Math.random() < 0.1) { // 10% chance to simulate interruption
            throw new SiteDisplayException("Connection to the server ETOUR is interrupted.");
        }

        return site;
    }
}