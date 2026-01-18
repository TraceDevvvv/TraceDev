package com.example.application;

import com.example.domain.Location;
import com.example.domain.Site;
import com.example.dto.AdvancedSearchRequestDTO;
import com.example.dto.SiteResultDTO;
import com.example.exceptions.ETOURServiceException;
import com.example.infrastructure.ILocationService;
import com.example.infrastructure.ISiteRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Application Service for handling advanced search operations.
 * Coordinates between the Presentation Layer (via Controller) and Infrastructure/Domain Layers.
 */
public class AdvancedSearchService {
    private final ISiteRepository siteRepository;
    private final ILocationService locationService;

    /**
     * Constructs an AdvancedSearchService with necessary dependencies.
     * @param siteRepository The repository for accessing site data.
     * @param locationService The service for retrieving user location.
     */
    public AdvancedSearchService(ISiteRepository siteRepository, ILocationService locationService) {
        this.siteRepository = siteRepository;
        this.locationService = locationService;
    }

    /**
     * Processes an advanced search request.
     * This method orchestrates getting the user's location and then querying for sites.
     * It transforms domain entities into DTOs for the presentation layer.
     *
     * @param request The {@link AdvancedSearchRequestDTO} containing search parameters.
     * @param touristEventContext A map containing context information for location service.
     * @return A list of {@link SiteResultDTO} representing the search results.
     * @throws ETOURServiceException if the location service encounters an error (R11).
     */
    public List<SiteResultDTO> processAdvancedSearch(AdvancedSearchRequestDTO request, Map<String, String> touristEventContext) throws ETOURServiceException {
        System.out.println("AdvancedSearchService: Processing advanced search for request: " + request);

        // 1. Get user's current location from the infrastructure service
        Location userLocation = locationService.requestLocationData(touristEventContext);
        System.out.println("AdvancedSearchService: User location retrieved: " + userLocation);

        // 2. Use the repository to find sites based on criteria and user's location
        List<Site> sites = siteRepository.querySites(request, userLocation);
        System.out.println("AdvancedSearchService: Found " + sites.size() + " domain sites.");

        // 3. Transform domain Site entities into SiteResultDTOs for presentation
        List<SiteResultDTO> results = sites.stream()
                .map(site -> new SiteResultDTO(
                        site.getId(),
                        site.getName(),
                        site.getDescription(),
                        site.getDistance(userLocation) // Calculate distance from the retrieved user location
                ))
                .collect(Collectors.toList());

        System.out.println("AdvancedSearchService: Transformed " + results.size() + " sites to DTOs.");
        return results;
    }
}