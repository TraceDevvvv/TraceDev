package com.example;

/**
 * Repository interface for tourist and site related operations.
 */
public interface TouristSiteRepository {
    boolean addToVisitedSites(String touristId, String siteId);
    boolean isValidSite(String siteId);
    boolean getTouristCardStatus(String touristId);
}