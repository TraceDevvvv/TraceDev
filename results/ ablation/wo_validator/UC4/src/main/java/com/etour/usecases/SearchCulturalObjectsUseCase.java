package com.etour.usecases;

import com.etour.adapters.SearchQuery;
import com.etour.adapters.SearchResult;
import com.etour.entities.CulturalObject;
import com.etour.repositories.CulturalObjectRepository;
import java.util.List;

/**
 * Implementation of the search use case.
 * Orchestrates the search process by interacting with the repository.
 */
public class SearchCulturalObjectsUseCase implements SearchUseCase {
    private final CulturalObjectRepository repository;

    public SearchCulturalObjectsUseCase(CulturalObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public SearchResult execute(SearchQuery query) {
        try {
            // 1. Retrieve cultural objects matching criteria
            List<CulturalObject> objects = repository.findByCriteria(query);
            // 2. Retrieve total count (might be same as objects.size() if no pagination, but we follow sequence diagram)
            int totalCount = repository.countByCriteria(query);

            // 3. Create and return result
            SearchResult result = new SearchResult();
            result.setCulturalObjects(objects);
            result.setTotalCount(totalCount);
            return result;
        } catch (ServerConnectionException e) {
            // Handle connection failure as depicted in sequence diagram's else branch
            System.err.println("Connection error in use case: " + e.getMessage());
            // Return empty result as per sequence diagram
            SearchResult emptyResult = new SearchResult();
            emptyResult.setCulturalObjects(List.of());
            emptyResult.setTotalCount(0);
            return emptyResult;
        }
    }

    // Inner exception class for representing server connection failures
    public static class ServerConnectionException extends RuntimeException {
        public ServerConnectionException(String message) {
            super(message);
        }
    }
}