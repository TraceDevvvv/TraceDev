package com.etour.search;

import java.util.List;

/**
 * Controller class that handles search requests and coordinates between form and service.
 */
public class SearchController {
    private SearchService searchService;
    private AuthenticationService authService;

    public SearchController(SearchService searchService, AuthenticationService authService) {
        this.searchService = searchService;
        this.authService = authService;
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    public AuthenticationService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthenticationService authService) {
        this.authService = authService;
    }

    /**
     * Handles a search request from the user.
     * @param request The search request containing criteria and user info.
     * @return List of sites matching the search criteria.
     */
    public List<Site> handleSearchRequest(SearchRequest request) {
        if (!request.isValid()) {
            throw new IllegalArgumentException("Invalid search request");
        }
        return searchService.searchSites(request.getCriteria());
    }

    /**
     * Processes form submission from the search form.
     * @param criteria Search criteria submitted by the user.
     * @return SearchResult containing sites