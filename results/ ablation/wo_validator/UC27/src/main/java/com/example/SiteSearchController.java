
package com.example;

import java.util.List;

/**
 * Controller that handles site search requests.
 */
public class SiteSearchController {
    private SiteRepository siteRepository;

    public SiteSearchController() {
        this.siteRepository = new SiteRepository();
    }

    /**
     * Main search method: validates request, searches repository, and returns response.
     * @param searchData the search request data
     * @return SiteSearchResponse with results or error
     */
    public SiteSearchResponse searchSite(SiteSearchRequest searchData) {
        // Validate request
        if (!validateRequest(searchData)) {
            SiteSearchResponse errorResponse = new SiteSearchResponse();
            errorResponse.setStatus(SearchStatus.ERROR);
            errorResponse.setMessage("Invalid search request.");
            return errorResponse;
        }

        // Request is valid, perform search
        SearchCriteria criteria = searchData.getSearchCriteria();
        if (criteria == null) {
            criteria = new SearchCriteria();
            criteria.setSearchTerm(searchData.getSearchTerm());
        }

        List<Site> sites = siteRepository.findByCriteria(criteria);

        SiteSearchResponse response = new SiteSearchResponse();
        if (sites.isEmpty()) {
            response.setStatus(SearchStatus.NOT_FOUND);
            response.setMessage("No sites found matching criteria.");
        } else {
            response.setStatus(SearchStatus.SUCCESS);
            response.setMessage("Search completed successfully.");
        }
        response.setSites(sites);

        // On exit, disconnect (as per sequence diagram opt block)
        siteRepository.disconnect();

        return response;
    }

    /**
     * Validates the search request.
     * Assumption: At least search term or criteria must be present.
     * @param searchData the request to validate
     * @return true if valid, false otherwise
     */
    private boolean validateRequest(SiteSearchRequest searchData) {
        if (searchData == null) return false;
        if (searchData.getSearchTerm() == null || searchData.getSearchTerm().trim().isEmpty()) {
            // Check if criteria has search term
            if (searchData.getSearchCriteria() == null || 
                searchData.getSearchCriteria().getSearchTerm() == null || 
                searchData.getSearchCriteria().getSearchTerm().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
