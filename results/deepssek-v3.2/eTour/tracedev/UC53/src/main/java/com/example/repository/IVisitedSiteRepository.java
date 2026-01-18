package com.example.repository;

import com.example.model.VisitedSite;

/**
 * Repository interface for VisitedSite entities.
 */
public interface IVisitedSiteRepository {
    void addVisitedSite(VisitedSite visitedSite);
    boolean exists(String touristId, String siteId);
}