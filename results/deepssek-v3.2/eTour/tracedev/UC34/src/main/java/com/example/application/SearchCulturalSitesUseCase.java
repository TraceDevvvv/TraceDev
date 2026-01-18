package com.example.application;

import com.example.domain.CulturalSite;
import com.example.infrastructure.CulturalSiteRepository;
import com.example.infrastructure.LocationService;
import com.example.quality.RelevanceFilter;
import java.util.List;

/**
 * Use Case Interactor/Controller.
 */
public class SearchCulturalSitesUseCase {
    private CulturalSiteRepository culturalSiteRepository;
    private LocationService locationService;
    private RelevanceFilter relevanceFilter;

    public SearchCulturalSitesUseCase(CulturalSiteRepository repository,
                                      LocationService locService,
                                      RelevanceFilter filter) {
        this.culturalSiteRepository = repository;
        this.locationService = locService;
        this.relevanceFilter = filter;
    }

    public List<CulturalSite> execute(SearchCriteria searchCriteria) throws UseCaseExecutionException {
        try {
            // Query repository with criteria
            List<CulturalSite> sites = culturalSiteRepository.findByCriteria(searchCriteria);

            // Process location‑based search (explicit step, satisfies Flow of Events: 5)
            sites = processLocationBasedSearch(sites,
                    searchCriteria.getUserLatitude(),
                    searchCriteria.getUserLongitude(),
                    searchCriteria.getSearchRadiusKm());

            // Relevance filtering and ranking
            sites = relevanceFilter.filterAndRank(sites, searchCriteria);

            return sites;
        } catch (Exception e) {
            throw new UseCaseExecutionException("Unexpected error: " + e.getMessage(), 1002);
        }
    }

    public List<CulturalSite> processLocationBasedSearch(List<CulturalSite> sites,
                                                         double userLat, double userLon, double radius) {
        // Filter sites by radius and calculate distance from user.
        // Remove sites outside the radius, and calculate distance for those inside.
        List<CulturalSite> filtered = new java.util.ArrayList<>();
        for (CulturalSite site : sites) {
            double distance = site.calculateDistanceFrom(userLat, userLon);
            if (distance <= radius) {
                // Optionally store distance in the domain object if needed.
                filtered.add(site);
            }
        }
        return filtered;
    }
}