package com.example.application;

import com.example.exceptions.ApplicationServiceException;
import com.example.exceptions.DataAccessException;
import com.example.model.PointOfRest;
import com.example.model.SearchCriteria;
import com.example.repository.IPointOfRestRepository;

import java.util.List;

/**
 * Application service for searching points of rest.
 * Coordinates between the presentation layer and the domain/infrastructure layers.
 */
public class PointOfRestSearchService {

    private final IPointOfRestRepository pointOfRestRepository;

    /**
     * Constructs a PointOfRestSearchService with a dependency on IPointOfRestRepository.
     *
     * @param pointOfRestRepository The repository to use for data access.
     */
    public PointOfRestSearchService(IPointOfRestRepository pointOfRestRepository) {
        this.pointOfRestRepository = pointOfRestRepository;
    }

    /**
     * Processes a search request for points of rest.
     *
     * @param criteria The search criteria provided by the user.
     * @return A list of PointOfRest objects matching the criteria.
     * @throws ApplicationServiceException if a problem occurs during the search process.
     */
    public List<PointOfRest> processSearchRequest(SearchCriteria criteria) throws ApplicationServiceException {
        System.out.println("AppService: Processing search request for criteria: " + criteria);
        try {
            // Delegate the data retrieval to the repository
            List<PointOfRest> results = pointOfRestRepository.findByCriteria(criteria);
            System.out.println("AppService: Found " + results.size() + " points of rest.");
            return results;
        } catch (DataAccessException e) {
            // Wrap lower-level data access exception into an application service exception
            throw new ApplicationServiceException("Failed to process search request due to data access error.", e);
        }
    }
}