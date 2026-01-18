package com.example.service;

import com.example.model.SearchCriteria;
import com.example.model.Site;
import com.example.model.SiteSearchResult;
import com.example.repository.SiteRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Service layer for site search operations.
 */
public class SiteSearchService {
    private SiteRepository siteRepository;

    public SiteSearchService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    /**
     * Executes a search based on the given criteria.
     *
     * @param searchCriteria the search criteria
     * @return SiteSearchResult containing results or error
     */
    public SiteSearchResult executeSearch(SearchCriteria searchCriteria) {
        long startTime = System.currentTimeMillis();
        try {
            List<Site> sites = siteRepository.findAllByCriteria(searchCriteria);
            long executionTime = System.currentTimeMillis() - startTime;
            SiteSearchResult result = processSearchLogic(searchCriteria);
            // Typically we would combine the sites and execution time into the result
            // For simplicity, we create a new result with the sites and time.
            return new SiteSearchResult(sites, executionTime);
        } catch (SQLException e) {
            // Simulating ETOURConnectionInterruptedException (as per sequence diagram)
            // In a real system, we would have a custom exception type.
            long executionTime = System.currentTimeMillis() - startTime;
            return createErrorResult();
        }
    }

    /**
     * Processes search logic (placeholder for business logic).
     *
     * @param searchCriteria the search criteria
     * @return SiteSearchResult (simplified - in real scenario might apply additional logic)
     */
    protected SiteSearchResult processSearchLogic(SearchCriteria searchCriteria) {
        // Placeholder: additional business logic can be added here.
        // For now, returns an empty result; actual result is built in executeSearch.
        return new SiteSearchResult("", 0);
    }

    /**
     * Creates a SiteSearchResult for successful search (as per class diagram).
     *
     * @return a successful SiteSearchResult
     */
    public SiteSearchResult createSiteSearchResult() {
        return new SiteSearchResult(null, 0);
    }

    /**
     * Creates an error result for failed search.
     *
     * @return error SiteSearchResult
     */
    public SiteSearchResult createErrorResult() {
        return new SiteSearchResult("Database connection failed or query execution interrupted.");
    }
}