package com.example.search.application;

import com.example.search.domain.CulturalObject;
import com.example.search.domain.SearchCriteria;
import com.example.search.domain.SearchResultDTO;

import java.util.List;

/**
 * Controller for the Search Use Case, orchestrating interactions between the
 * Presentation Layer and the Application Layer's SearchService.
 * It acts as an entry point for search-related business logic.
 */
public class SearchUseCaseController {

    private final SearchService searchService;

    /**
     * Constructs a SearchUseCaseController with a dependency on SearchService.
     *
     * @param searchService The service responsible for core search logic.
     */
    public SearchUseCaseController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Performs a search for cultural objects based on the provided criteria.
     * This method delegates to the SearchService and wraps the result in a DTO.
     * It also handles exceptions from the service layer.
     *
     * @param criteria The search criteria.
     * @return A SearchResultDTO containing the found cultural objects.
     * @throws SearchUseCaseException if an error occurs during the search process.
     */
    public SearchResultDTO performSearch(SearchCriteria criteria) {
        System.out.println("UCC: Performing search for criteria: " + criteria.getKeyword());
        try {
            // Delegate search to the SearchService
            List<CulturalObject> culturalObjects = searchService.searchCulturalObjects(criteria);

            // Wrap the list of cultural objects into a SearchResultDTO
            return new SearchResultDTO(culturalObjects);
        } catch (ServiceConnectionException e) {
            // Catch service-level exception and re-throw as a use case specific exception
            // This allows the presentation layer to handle generic search errors without
            // knowing specific service or infrastructure details.
            System.err.println("UCC: Error performing search: " + e.getMessage());
            throw new SearchUseCaseException("Failed to perform search due to a connection error.", e);
        }
    }
}