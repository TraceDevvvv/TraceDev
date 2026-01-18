package com.example;

import java.util.List;

/**
 * Service that implements the SearchQuery interface to perform searches.
 */
public class SearchQueryService implements SearchQuery {
    private LocationService locationService;
    private SiteRepository siteRepository;

    /**
     * Constructor for SearchQueryService.
     * Initializes with default serv.
     */
    public SearchQueryService() {
        this.locationService = new GeolocationService();
        this.siteRepository = new SiteDatabaseRepository();
    }

    /**
     * Executes the search query by getting current location and retrieving sites.
     * Implements the sequence diagram flow.
     * @param criteria the search criteria.
     * @param location the provided location (if null, uses current location).
     * @return the search result containing matching sites.
     */
    @Override
    public SearchResult execute(SearchCriteria criteria, Location location) {
        // If location is not provided, get current position.
        Location currentLocation = location;
        if (currentLocation == null) {
            currentLocation = locationService.getCurrentPosition();
        }

        // Retrieve sites based on criteria and location.
        List<Site> sites = siteRepository.findByCriteria(criteria, currentLocation);

        // Create and return search result.
        long executionTime = 0; // Execution time is calculated in controller.
        SearchResult result = new SearchResult(sites, sites.size(), executionTime);
        return result;
    }

    /**
     * Sets the location service (for dependency injection).
     * @param locationService the location service.
     */
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Sets the site repository (for dependency injection).
     * @param siteRepository the site repository.
     */
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }
}