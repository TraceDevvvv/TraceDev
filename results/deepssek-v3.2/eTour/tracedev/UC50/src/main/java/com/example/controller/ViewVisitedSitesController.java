package com.example.controller;

import com.example.dto.ViewVisitedSitesRequestDTO;
import com.example.dto.VisitedSitesResponseDTO;
import com.example.service.AuthenticationService;
import com.example.usecase.ViewVisitedSitesUseCase;
import com.example.exception.ConnectionLostException;

/**
 * Controller handling requests for visited sites.
 */
public class ViewVisitedSitesController {
    private ViewVisitedSitesUseCase useCase;
    private AuthenticationService authService;

    public ViewVisitedSitesController(ViewVisitedSitesUseCase useCase, AuthenticationService authService) {
        this.useCase = useCase;
        this.authService = authService;
    }

    /**
     * Handles the request from Tourist.
     * R1-Description: Tourist accesses personal feedback history.
     * R2-Entry Conditions: Tourist HAS successfully authenticated.
     */
    public VisitedSitesResponseDTO handleRequest(String touristId, String authToken) {
        // Verify authentication
        if (!verifyAuthentication(touristId)) {
            // R2-Exit: Authentication failed, access denied
            throw new SecurityException("Authentication failed");
        }
        // Authentication successful, proceed with use case
        ViewVisitedSitesRequestDTO request = new ViewVisitedSitesRequestDTO(touristId);
        try {
            return useCase.execute(request);
        } catch (ConnectionLostException e) {
            // R5-Exit Conditions: Connection to the server ETOUR is interrupted
            throw e;
        }
    }

    /**
     * Verifies authentication for the given tourist.
     * Added to satisfy requirement Entry Conditions.
     */
    public boolean verifyAuthentication(String touristId) {
        return authService.isAuthenticated(touristId);
    }
}