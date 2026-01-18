package com.example.culturalobjects;

import java.util.List;

/**
 * APPLICATION SERVICE: Orchestrates business logic for searching cultural objects.
 * Acts as an intermediary between the Controller and the Repository.
 * Quality Requirement REQ-010: Performance of search results within set time. This is implicitly handled
 * by ensuring efficient calls to the repository and gateway, and proper data structures.
 * For explicit measurement, external monitoring tools would be used.
 */
public class SearchService {

    // Dependency: ICulturalObjectRepository for data access
    private final ICulturalObjectRepository culturalObjectRepository;

    /**
     * Constructor for SearchService.
     * @param culturalObjectRepository The repository instance to use for data access.
     */
    public SearchService(ICulturalObjectRepository culturalObjectRepository) {
        this.culturalObjectRepository = culturalObjectRepository;
    }

    /**
     * Searches for cultural objects based on the provided criteria.
     * This method retrieves data from the repository and transforms it into a SearchResultDTO
     * or propagates an ErrorResultDTO if an error occurred during data retrieval.
     *
     * @param criteria The search criteria.
     * @return An Object which is either a SearchResultDTO containing the search results
     *         or an ErrorResultDTO if an error, like an ETOUR connection failure, occurred.
     */
    public Object searchCulturalObjects(SearchCriteria criteria) {
        System.out.println("[Service] Initiating search for cultural objects with criteria: " + criteria);

        // REQ-010: Performance: Results within set time.
        // This note serves as a reminder for performance considerations.
        // In a real system, performance monitoring would be integrated here.

        // Delegate to the repository to find cultural objects
        Object repositoryResponse = culturalObjectRepository.findByCriteria(criteria);

        // Check the type of the response from the repository
        if (repositoryResponse instanceof ErrorResultDTO) {
            // If the repository returned an error, propagate it
            System.out.println("[Service] Repository returned an error: " + ((ErrorResultDTO) repositoryResponse).getMessage());
            return repositoryResponse;
        } else if (repositoryResponse instanceof List) {
            // If the repository returned a list of cultural objects, create a SearchResultDTO
            @SuppressWarnings("unchecked") // Safe cast due to instanceof check
            List<CulturalObject> culturalObjects = (List<CulturalObject>) repositoryResponse;

            System.out.println("[Service] Repository returned " + culturalObjects.size() + " cultural objects. Creating SearchResultDTO.");
            return new SearchResultDTO(culturalObjects);
        } else {
            // Unexpected response type from repository
            System.err.println("[Service] Unexpected response type from repository: " + repositoryResponse.getClass().getName());
            return new ErrorResultDTO("UNEXPECTED_RESPONSE", "An unexpected error occurred during search.");
        }
    }
}