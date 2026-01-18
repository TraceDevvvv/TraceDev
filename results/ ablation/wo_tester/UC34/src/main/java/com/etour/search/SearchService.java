package com.etour.search;

import java.util.List;

/**
 * Service class that performs site search operations.
 * Uses GeolocationService and SiteRepository.
 * Implements health check for availability requirement.
 */
public class SearchService {
    private GeolocationService geolocationService;
    private ISiteRepository siteRepository;

    public SearchService(GeolocationService geolocationService, ISiteRepository siteRepository) {
        this.geolocationService = geolocationService;
        this.siteRepository = siteRepository;
    }

    public GeolocationService getGeolocationService() {
        return geolocationService;
    }

    public void setGeolocationService(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    public ISiteRepository getSiteRepository() {
        return siteRepository;
    }

    public void setSiteRepository(ISiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    /**
     * Searches for sites based on the given criteria.
     * @param criteria Search criteria including keywords, category, range, etc.
     * @return List of sites matching the criteria.
     */
    public List<Site> searchSites(SearchCriteria criteria) {
        System.out.println("Searching sites with criteria: " + criteria.getSearchString());
        
        // Get current user location
        Location location = getCurrentPosition();
        
        // Validate criteria
        if (!criteria.validate()) {
            throw new IllegalArgumentException("Invalid search criteria");
        }
        
        // Perform search through repository
        try {
            List<Site> sites = siteRepository.findByCriteria(criteria, location);
            System.out.println("Found " + sites.size() + " sites.");
            return sites;
        } catch (ConnectionError e) {
            throw e; // Re-throw connection errors
        }
    }

    /**
     * Gets the current position of the user.
     * @return Location object with latitude and longitude.
     */
    public Location getCurrentPosition() {
        if (!geolocationService.isGeolocationAvailable()) {
            throw new IllegalStateException("Geolocation is not available");
        }
        return geolocationService.getUserLocation();
    }

    /**
     * Creates a search result object from a list of sites.
     * @param sites List of sites to include in the result.
     * @return SearchResult object.
     */
    public SearchResult createSearchResult(List<Site> sites) {
        return new SearchResult(sites);
    }

    /**
     * Performs a health check of the service and its dependencies.
     * Quality Requirement: availability
     * @return true if all dependencies are healthy, false otherwise.
     */
    public boolean healthCheck() {
        try {
            boolean geoAvailable = geolocationService.isGeolocationAvailable();
            boolean repoAvailable = siteRepository.testConnection();
            return geoAvailable && repoAvailable;
        } catch (Exception e) {
            System.err.println("Health check failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gets location from GeolocationService and returns it.
     * @return location:Location
     */
    public Location getLocationFromGeolocationService() {
        System.out.println("Getting location from GeolocationService...");
        Location location = geolocationService.getLocation();
        System.out.println("Returning location to SearchController");
        return location;
    }

    /**
     * Gets sites from SiteRepository based on criteria.
     * @param criteria Search criteria.
     * @return sites:List<Site>
     */
    public List<Site> getSitesFromRepository(SearchCriteria criteria) {
        System.out.println("Getting sites from repository for criteria: " + criteria.getSearchString());
        try {
            // For sequence diagram: SR calls DB.fetchSiteData(criteria)
            ETOURSiteRepository etourRepo = (ETOURSiteRepository) siteRepository;
            List<Site> sites = etourRepo.fetchSiteData(criteria);
            System.out.println("Returning " + sites.size() + " sites to SearchController");
            return sites;
        } catch (ConnectionError e) {
            System.err.println("Connection error in getSitesFromRepository: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Unexpected error in getSitesFromRepository: " + e.getMessage());
            throw new ConnectionError("Repository error: " + e.getMessage(), 1003);
        }
    }

    /**
     * Returns exception to SearchController when connection fails.
     * @return exception:ConnectionError
     */
    public ConnectionError reportConnectionError() {
        System.out.println("Reporting connection error...");
        String errorMessage = "Connection to ETOUR server failed";
        int errorCode = 1001;
        ConnectionError exception = new ConnectionError(errorMessage, errorCode);
        System.out.println("Returning exception: " + exception.getErrorMessage());
        return exception;
    }

    /**
     * Checks repository connection status.
     * @return connectionStatus:boolean
     */
    public boolean checkRepositoryConnection() {
        System.out.println("Checking repository connection...");
        boolean connectionStatus = siteRepository.testConnection();
        System.out.println("Repository connection status: " + connectionStatus);
        return connectionStatus;
    }
}