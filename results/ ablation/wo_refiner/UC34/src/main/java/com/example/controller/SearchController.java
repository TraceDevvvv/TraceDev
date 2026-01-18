package com.example.controller;

import com.example.dto.SearchCriteriaDTO;
import com.example.dto.SiteDTO;
import com.example.exception.ETOURConnectionException;
import com.example.model.SearchModel;
import com.example.service.ISearchService;
import com.example.view.SearchFormView;
import java.util.List;

/**
 * Controller component in MVC, orchestrating search flow.
 * Handles errors and processes form submissions.
 */
public class SearchController {
    private ISearchService searchService;
    private SearchModel searchModel;
    private SearchFormView searchFormView;

    public SearchController(ISearchService service) {
        this.searchService = service;
        this.searchModel = new SearchModel();
        this.searchFormView = new SearchFormView();
    }

    /**
     * Handles HTTP search request (simplified).
     */
    public void handleSearchRequest(HttpRequest request) {
        // For simulation, we directly call processFormSubmission.
        // In a real web app, this would extract criteria from request.
        SearchCriteriaDTO criteria = searchFormView.getSearchCriteria();
        processFormSubmission(criteria);
    }

    /**
     * Processes form submission as per sequence diagram.
     */
    public void processFormSubmission(SearchCriteriaDTO criteria) {
        searchModel.setCriteria(criteria);
        try {
            List<SiteDTO> results = searchService.searchSites(criteria);
            searchModel.setResults(results);
            searchFormView.displayResults(results);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof ETOURConnectionException) {
                handleSearchError((ETOURConnectionException) e.getCause());
            } else {
                searchFormView.showErrorMessage("Search failed: " + e.getMessage());
            }
        }
    }

    /**
     * Handles search errors, especially ETOUR connection exceptions (REQ-011).
     */
    public void handleSearchError(ETOURConnectionException exception) {
        System.err.println("Search error handled: " + exception.getMessage());
        searchFormView.showErrorMessage("Connection lost to ETOUR server");
        searchFormView.handleConnectionError();
    }

    // Helper class to simulate HttpRequest (not defined in UML)
    public static class HttpRequest {
        // placeholder
    }
}