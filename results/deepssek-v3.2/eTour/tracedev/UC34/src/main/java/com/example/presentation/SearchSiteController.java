package com.example.presentation;

import com.example.auth.AuthenticationService;
import com.example.domain.CulturalSite;
import com.example.dto.CulturalSiteDTO;
import com.example.dto.SearchFormDTO;
import com.example.application.SearchCulturalSitesUseCase;
import com.example.infrastructure.LocationService;
import java.util.ArrayList;
import java.util.List;

/**
 * View Controller (MVC Controller).
 * Added convertToDTO() to satisfy consistency requirement.
 */
public class SearchSiteController {
    private CulturalSiteSearchView searchView;
    private SearchCulturalSitesUseCase searchSitesUseCase;
    private AuthenticationService authService;

    public SearchSiteController(CulturalSiteSearchView view, SearchCulturalSitesUseCase useCase,
                                AuthenticationService authService) {
        this.searchView = view;
        this.searchSitesUseCase = useCase;
        this.authService = authService;
    }

    public void handleSearchRequest(SearchFormDTO searchFormData) {
        try {
            // Authentication check (as per sequence diagram)
            // Assuming userId is obtained from somewhere; for simplicity, we use "guest".
            if (!authService.isUserLoggedIn("guest")) {
                searchView.displayError("User not logged in.");
                return;
            }

            // Location retrieval (steps 5 & 6 combined)
            LocationService locationService = new com.example.infrastructure.GeolocationService();
            com.example.domain.GeographicCoordinate userLocation = locationService.getCurrentPosition();
            double userLat = userLocation.getLatitude();
            double userLon = userLocation.getLongitude();

            // Create SearchCriteria from DTO + location (satisfies Flow of Events: 5 & 6)
            com.example.application.SearchCriteria criteria = new com.example.application.SearchCriteria(
                    searchFormData.getSiteNameKeyword(),
                    searchFormData.getSiteTypeFilter(),
                    userLat,
                    userLon,
                    searchFormData.getRadiusFilter(),
                    searchFormData.getStartDate(),
                    searchFormData.getEndDate(),
                    searchFormData.getMinRating()
            );

            // Invoke the use case
            List<CulturalSite> culturalSites = searchSitesUseCase.execute(criteria);

            // Convert CulturalSite to CulturalSiteDTO
            List<CulturalSiteDTO> dtos = new ArrayList<>();
            for (CulturalSite site : culturalSites) {
                dtos.add(convertToDTO(site));
            }

            // Update view with results
            searchView.displaySearchResults(dtos);
        } catch (com.example.application.UseCaseExecutionException e) {
            searchView.displayError(e.getMessage());
        } catch (Exception e) {
            searchView.displayError("Unexpected error: " + e.getMessage());
        }
    }

    public void handleUserLocationDetected(double latitude, double longitude) {
        // This method can be used if location is detected asynchronously.
        System.out.println("User location detected: lat=" + latitude + ", lon=" + longitude);
    }

    /**
     * Convert CulturalSite (domain object) to CulturalSiteDTO (presentation object).
     */
    public CulturalSiteDTO convertToDTO(CulturalSite culturalSite) {
        CulturalSiteDTO dto = new CulturalSiteDTO();
        dto.setId(culturalSite.getId().getValue());
        dto.setName(culturalSite.getName());
        dto.setDescription(culturalSite.getDescription());
        dto.setType(culturalSite.getType().name());
        dto.setLatitude(culturalSite.getLocation().getLatitude());
        dto.setLongitude(culturalSite.getLocation().getLongitude());
        // Distance and relevance score can be set later if needed.
        return dto;
    }
}