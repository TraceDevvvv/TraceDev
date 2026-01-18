package com.example.search.application;

import com.example.search.domain.CulturalObject;
import com.example.search.domain.SearchCriteria;
import com.example.search.infrastructure.CulturalObjectRepository;
import com.example.search.infrastructure.ETOURConnectionException;

import java.util.List;

/**
 * Implements the core search logic for cultural objects.
 * This service orchestrates the retrieval of data from the repository.
 * Requirement R10: Implements core search logic; performance critical.
 * (In a real system, this would contain optimizations like caching, complex business rules, etc.)
 */
public class SearchService {

    private final CulturalObjectRepository culturalObjectRepository;

    /**
     * Constructs a SearchService with a dependency on CulturalObjectRepository.
     *
     * @param culturalObjectRepository The repository used to access cultural object data.
     */
    public SearchService(CulturalObjectRepository culturalObjectRepository) {
        this.culturalObjectRepository = culturalObjectRepository;
    }

    /**
     * Searches for cultural objects based on the provided criteria.
     * This method delegates the data retrieval to the CulturalObjectRepository.
     * As per the sequence diagram, this is a critical point for performance.
     *
     * @param criteria The search criteria.
     * @return A list of CulturalObject instances matching the criteria.
     * @throws ServiceConnectionException if there's an underlying infrastructure connection issue.
     */
    public List<CulturalObject> searchCulturalObjects(SearchCriteria criteria) {
        System.out.println("Service: Searching cultural objects with criteria: " + criteria.getKeyword());
        try {
            // Delegate to the repository to find objects by criteria.
            return culturalObjectRepository.findByCriteria(criteria);
        } catch (ETOURConnectionException e) {
            // Catch infrastructure-specific exception and re-throw as a service-level exception.
            // This prevents infrastructure details from leaking to the application layer.
            System.err.println("Service: Error during search due to ETOUR connection: " + e.getMessage());
            throw new ServiceConnectionException("Failed to connect to external data source during search.", e);
        }
    }
}