
package com.example.controller;

import com.example.auth.AuthenticationService;
import com.example.dto.SearchTouristRequest;
import com.example.dto.SearchTouristResponse;
import com.example.usecase.ITouristSearchUseCase;
import java.util.Map;

/**
 * Controller orchestrating the search tourist flow.
 * Stereotype <<R5>>: Flow of events User System.
 */
public class SearchTouristController {
    private ITouristSearchUseCase touristSearchUseCase;
    private AuthenticationService authService;

    public SearchTouristController(ITouristSearchUseCase useCase, AuthenticationService authService) {
        this.touristSearchUseCase = useCase;
        this.authService = authService;
    }

    /**
     * Main search method called by UI.
     */
    public SearchTouristResponse search(SearchTouristRequest request) {
        // Convert request to criteria
        SearchTouristRequest criteria = convertToCriteria(request);
        // Execute use case
        var accounts = touristSearchUseCase.execute(criteria);
        // Build response
        SearchTouristResponse response = new SearchTouristResponse();
        response.setTouristAccounts(accounts);
        response.setSuccess(true);
        return response;
    }

    /**
     * Converts UI form data to a request object.
     */
    public SearchTouristRequest createRequest(Map<String, Object> formData) {
        SearchTouristRequest request = new SearchTouristRequest();
        // Simplified mapping; real implementation would extract each field.
        if (formData.containsKey("name")) {
            request.setName((String) formData.get("name"));
        }
        if (formData.containsKey("email")) {
            request.setEmail((String) formData.get("email"));
        }
        if (formData.containsKey("nationality")) {
            request.setNationality((String) formData.get("nationality"));
        }
        // Date fields would require proper parsing; omitted for brevity.
        return request;
    }

    /**
     * Converts a request to domain search criteria.
     */
    public SearchTouristRequest convertToCriteria(SearchTouristRequest request) {
        return request;
    }
}
