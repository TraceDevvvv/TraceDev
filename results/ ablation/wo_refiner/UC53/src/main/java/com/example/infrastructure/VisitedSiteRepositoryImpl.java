package com.example.infrastructure;

import com.example.application.VisitedSiteRepository;

/**
 * Implementation of VisitedSiteRepository.
 */
public class VisitedSiteRepositoryImpl implements VisitedSiteRepository {
    private final DatabaseService databaseService;

    public VisitedSiteRepositoryImpl(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public boolean add(String touristId, String siteId) {
        // Simulate insertion into visited sites table
        return databaseService.save("VisitedSite(touristId=" + touristId + ", siteId=" + siteId + ")");
    }
}