package com.etour.controller;

import com.etour.model.Tourist;
import com.etour.model.Site;
import com.etour.model.Bookmark;
import com.etour.model.TouristInfo;
import com.etour.model.SiteInfo;
import com.etour.repository.SiteRepository;
import com.etour.service.NotificationService;
import com.etour.ui.MobileAppUI;

/**
 * <<trace(R-FR-2)>> Core business logic for favorite sites
 * Quality Requirement: {time ≤ 5s}
 */
public class InsertSiteController {
    private SiteRepository siteRepository;
    private SiteController siteController; // created as per diagram
    private NotificationService notificationService;

    public InsertSiteController(SiteRepository repository) {
        this.siteRepository = repository;
        this.siteController = new SiteController(repository);
        this.notificationService = new NotificationService();
    }

    /**
     * Inserts a site to favorites for a tourist.
     * Sequence diagram interaction: insertSiteToFavorites(tourist, site)
     * @param tourist the tourist
     * @param site the site to bookmark
     * @return true if insertion successful, false otherwise
     */
    public boolean insertSiteToFavorites(Tourist tourist, Site site) {
        // Quality requirement: operation should complete within 5 seconds.
        long startTime = System.currentTimeMillis();

        // Validate tourist and site (as per sequence diagram alt condition)
        if (tourist == null || site == null || 
            tourist.getUserId() == null || site.getSiteId() == null) {
            notificationService.showInsertionFailure();
            return false;
        }

        // Get touristInfo (as per sequence diagram message m4)
        TouristInfo touristInfo = tourist.getTouristDetails(tourist.getUserId());
        // Get siteInfo (as per sequence diagram message m6)
        SiteInfo siteInfo = site.getSiteDetails(site.getSiteId());

        // Create a new Bookmark
        Bookmark bookmark = new Bookmark(tourist.getUserId(), site.getSiteId());

        // Save bookmark via repository
        try {
            siteRepository.saveBookmark(bookmark);
        } catch (Exception e) {
            // Connection or persistence error
            notificationService.showConnectionError();
            return false;
        }

        // On success
        notificationService.showInsertionSuccess();
        
        long endTime = System.currentTimeMillis();
        if (endTime - startTime > 5000) {
            System.out.println("Warning: Operation exceeded 5-second quality requirement.");
        }
        return true;
    }
}