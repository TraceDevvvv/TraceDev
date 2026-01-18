package com.example;

public class SearchController {
    private SearchService searchService;
    private AuthService authService;

    public SearchController() {
        this.searchService = new SearchService();
        this.authService = new AuthService();
    }

    public SearchController(SearchService searchService, AuthService authService) {
        this.searchService = searchService;
        this.authService = authService;
    }

    public CulturalObjectDTO[] search(SearchCriteriaDTO criteria) {
        if (!authService.validateEntryConditions()) {
            throw new SecurityException("Error: Authentication required");
        }

        try {
            CulturalObjectDTO[] results = searchService.executeSearch(criteria);
            return results;
        } catch (ETOURConnectionException e) {
            ErrorDTO errorDto = new ErrorDTO("CONNECTION_ERROR", "Connection to server failed");
            throw new RuntimeException(errorDto.toString());
        }
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}