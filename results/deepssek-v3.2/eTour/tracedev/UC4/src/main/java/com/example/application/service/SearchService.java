package com.example.application.service;

import com.example.application.port.in.SearchUseCase;
import com.example.application.port.out.CulturalObjectRepository;
import com.example.domain.CulturalObject;
import com.example.domain.SearchCriteria;
import com.example.exception.SearchException;
import com.example.ui.SearchResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Search service implementing the search use case.
 * Contains business logic for searching cultural objects.
 */
public class SearchService implements SearchUseCase {
    private final CulturalObjectRepository repository;
    private static final long MAX_SEARCH_TIME_MS = 5000;

    public SearchService(CulturalObjectRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes a search based on criteria.
     * Implements quality requirement: Results within set time (5000ms).
     * @param criteria the search criteria
     * @return list of matching cultural objects
     * @throws SearchException if search fails
     */
    @Override
    public List<CulturalObject> execute(SearchCriteria criteria) throws SearchException {
        long startTime = System.currentTimeMillis();
        
        try {
            // Validate criteria
            if (criteria == null || !criteria.hasCriteria()) {
                System.out.println("No valid search criteria provided.");
                return new ArrayList<>();
            }
            
            if (!criteria.isValid()) {
                System.out.println("Invalid search criteria.");
                return new ArrayList<>();
            }
            
            // Execute repository search
            List<CulturalObject> objects = repository.findByCriteria(criteria);
            
            long searchTime = System.currentTimeMillis() - startTime;
            
            // Validate timing requirement
            if (!validateTiming(searchTime)) {
                System.out.println("Warning: Search took " + searchTime + "ms, exceeding optimal performance.");
            }
            
            return objects;
        } catch (Exception e) {
            long searchTime = System.currentTimeMillis() - startTime;
            throw new SearchException("Search failed after " + searchTime + "ms", e);
        }
    }

    /**
     * Validates if search completed within time limit.
     * @param searchTime actual search time in milliseconds
     * @return true if search time is within limit
     */
    public boolean validateTiming(long searchTime) {
        return searchTime <= MAX_SEARCH_TIME_MS;
    }
}