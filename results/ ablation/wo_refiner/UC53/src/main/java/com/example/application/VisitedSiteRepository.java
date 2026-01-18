package com.example.application;

/**
 * Repository interface for visited sites.
 */
public interface VisitedSiteRepository {
    boolean add(String touristId, String siteId);
}