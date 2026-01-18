package com.example.application;

import com.example.application.interfaces.ISiteRepository;
import com.example.application.interfaces.ILocationService;
import com.example.domain.SearchCriteria;
import com.example.domain.Site;
import com.example.domain.SiteList;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Controller (Interactor) for advanced search use case.
 * Processes search requests from tourists and returns results.
 */
public class AdvancedSearchController {
    private ISiteRepository siteRepository;
    private ILocationService locationService;

    public AdvancedSearchController(ISiteRepository repo, ILocationService locService) {
        this.siteRepository = repo;
        this.locationService = locService;
    }

    /**
     * Main method to execute search as per sequence diagram.
     * Implements the flow: get user location, query repository, filter, sort, return DTO.
     */
    public SearchResultDTO executeSearch(String userId, SearchCriteria criteria) {
        long startTime = System.currentTimeMillis();

        // Check for timeout (quality requirement: <=15 seconds)
        // We'll simulate by checking elapsed time at the end.

        // Step: Get current location
        String userLocation = null;
        try {
            userLocation = locationService.getCurrentLocation(userId);
        } catch (Exception e) {
            System.out.println("Failed to get user location: " + e.getMessage());
        }

        SiteList results;
        if (userLocation != null && !userLocation.isEmpty()) {
            // Process search with location
            results = processSearch(userLocation, criteria);
        } else {
            // Process search without location
            results = processSearch(null, criteria);
        }

        // Convert SiteList to DTOs
        List<SiteDTO> siteDTOs = convertToDTO(results);

        // Create SearchResultDTO
        long searchTime = System.currentTimeMillis() - startTime;
        SearchResultDTO dto = new SearchResultDTO();
        dto.setResults(siteDTOs);
        dto.setSearchTime(searchTime);
        dto.setTotalResults(siteDTOs.size());
        dto.setResponseStatus("SUCCESS");

        // Simulate timeout check
        if (searchTime > 15000) {
            dto.setResponseStatus("TIMEOUT_ERROR");
            System.out.println("Search exceeded 15 seconds.");
        }

        // Simulate connection interruption (optional exit condition)
        // In a real system, this would be triggered by an external event.
        boolean connectionInterrupted = false; // simulated flag
        if (connectionInterrupted) {
            return handleConnectionError();
        }

        return dto;
    }

    /**
     * Internal method to process search criteria and location.
     */
    private SiteList processSearch(String userLocation, SearchCriteria criteria) {
        List<Site> sites = new ArrayList<>();

        // Query repository by criteria
        List<Site> byCriteria = siteRepository.findByCriteria(criteria);
        sites.addAll(byCriteria);

        // If location available, also query by location
        if (userLocation != null && userLocation.contains(",")) {
            String[] parts = userLocation.split(",");
            double lat = Double.parseDouble(parts[0]);
            double lon = Double.parseDouble(parts[1]);
            double radius = criteria.getDistanceFromUser();
            List<Site> byLocation = siteRepository.findByLocation(lat, lon, radius);
            // Merge results, avoiding duplicates
            for (Site site : byLocation) {
                if (!sites.contains(site)) {
                    sites.add(site);
                }
            }
        }

        SiteList siteList = new SiteList(sites);
        // Filter by criteria (again for merged list)
        siteList = siteList.filterByCriteria(criteria);
        // Sort by distance if location available
        if (userLocation != null) {
            siteList.sortByDistance(userLocation);
        }
        return siteList;
    }

    /**
     * Converts a SiteList to a list of SiteDTOs.
     */
    private List<SiteDTO> convertToDTO(SiteList siteList) {
        return siteList.getSites().stream()
                .map(this::convertSiteToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a single Site to SiteDTO.
     */
    private SiteDTO convertSiteToDTO(Site site) {
        SiteDTO dto = new SiteDTO();
        dto.setId(site.getId());
        dto.setName(site.getName());
        dto.setCategory(site.getCategory());
        // distanceFromUser is not calculated here; would require user location.
        dto.setDistanceFromUser(0.0); // placeholder
        dto.setRating(site.getRating());
        dto.setAmenities(site.getAmenities());
        return dto;
    }

    /**
     * Handles connection error as per exit condition.
     */
    public SearchResultDTO handleConnectionError() {
        SearchResultDTO dto = new SearchResultDTO();
        dto.setResults(new ArrayList<>());
        dto.setSearchTime(0);
        dto.setTotalResults(0);
        dto.setResponseStatus("CONNECTION_ERROR");
        System.out.println("Connection interrupted.");
        return dto;
    }

    // Sequence diagram messages implementation

    public List<Site> findByCriteria(SearchCriteria criteria) {
        return siteRepository.findByCriteria(criteria);
    }

    public List<Site> findByLocation(double lat, double lon, double radius) {
        return siteRepository.findByLocation(lat, lon, radius);
    }

    public SiteList newSiteList(List<Site> sites) {
        return new SiteList(sites);
    }

    public SiteList newSiteList(SiteList sites) {
        return new SiteList(sites.getSites());
    }

    public SearchResultDTO createSearchResultDTO() {
        SearchResultDTO dto = new SearchResultDTO();
        // You can initialize with default values
        return dto;
    }

    public SearchResultDTO createSearchResultDTOWithTimeoutError() {
        SearchResultDTO dto = new SearchResultDTO();
        dto.setResponseStatus("TIMEOUT_ERROR");
        return dto;
    }

    public SearchResultDTO createSearchResultDTOWithErrorStatus() {
        SearchResultDTO dto = new SearchResultDTO();
        dto.setResponseStatus("ERROR");
        return dto;
    }
}