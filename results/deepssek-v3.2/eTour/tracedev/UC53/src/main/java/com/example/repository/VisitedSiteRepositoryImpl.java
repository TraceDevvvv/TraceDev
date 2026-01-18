package com.example.repository;

import com.example.model.VisitedSite;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of IVisitedSiteRepository.
 */
public class VisitedSiteRepositoryImpl implements IVisitedSiteRepository {
    private List<VisitedSite> visitedStore = new ArrayList<>();

    @Override
    public void addVisitedSite(VisitedSite visitedSite) {
        visitedStore.add(visitedSite);
        // Simulate database insert
        System.out.println("VisitedSite saved to database: " + visitedSite.getVisitedId());
    }

    @Override
    public boolean exists(String touristId, String siteId) {
        return visitedStore.stream()
                .anyMatch(v -> v.getTouristId().equals(touristId) && v.getSiteId().equals(siteId));
    }
}