package com.example.quality;

import com.example.domain.CulturalSite;
import com.example.application.SearchCriteria;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of RelevanceFilter.
 * Added to satisfy quality requirement: accurate and relevant search results
 */
public class CulturalSiteRelevanceFilter implements RelevanceFilter {
    @Override
    public double calculateRelevance(CulturalSite site, SearchCriteria criteria) {
        // Simplified relevance calculation.
        // In a real system, this would consider keywords, type, rating, distance, etc.
        double relevance = 0.0;
        if (criteria.getKeywords() != null && site.getName().toLowerCase()
                .contains(criteria.getKeywords().toLowerCase())) {
            relevance += 0.5;
        }
        if (criteria.getSiteType() != null && site.getType().name().equals(criteria.getSiteType())) {
            relevance += 0.3;
        }
        relevance += (site.getAverageRating() / 10.0); // assume rating out of 10
        // Distance factor: closer sites get higher relevance
        double distance = site.calculateDistanceFrom(criteria.getUserLatitude(), criteria.getUserLongitude());
        if (distance < 1.0) {
            relevance += 0.2;
        } else if (distance < 5.0) {
            relevance += 0.1;
        }
        return relevance;
    }

    @Override
    public List<CulturalSite> filterAndRank(List<CulturalSite> sites, SearchCriteria criteria) {
        // First, filter by minimum rating if specified.
        List<CulturalSite> filtered = sites.stream()
                .filter(site -> site.getAverageRating() >= criteria.getMinRating())
                .collect(Collectors.toList());

        // Then, calculate relevance for each site and sort descending.
        return filtered.stream()
                .sorted(Comparator.comparingDouble((CulturalSite s) -> calculateRelevance(s, criteria)).reversed())
                .collect(Collectors.toList());
    }
}