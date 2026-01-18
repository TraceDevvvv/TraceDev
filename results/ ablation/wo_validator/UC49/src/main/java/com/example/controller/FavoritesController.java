package com.example.controller;

import com.example.dto.BookmarkDTO;
import com.example.usecase.ViewFavoritesUseCase;
import com.example.service.UserAuthenticationService;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for favorites operations.
 * Handles main flow and alternative flows from sequence diagrams.
 */
public class FavoritesController {
    private ViewFavoritesUseCase viewFavoritesUseCase;
    private UserAuthenticationService userAuthenticationService;
    
    /**
     * Constructor.
     * @param useCase the use case
     * @param authService the authentication service
     */
    public FavoritesController(ViewFavoritesUseCase useCase, UserAuthenticationService authService) {
        this.viewFavoritesUseCase = useCase;
        this.userAuthenticationService = authService;
    }
    
    /**
     * Gets favorites for a user.
     * Implements all three sequence diagram flows: main, not authenticated, and connection interrupted.
     * @param userId the user ID
     * @return list of bookmarks, empty list if not authenticated, or error handling in real scenario
     */
    public List<BookmarkDTO> getFavorites(String userId) {
        // Main and alternative flow: authentication check
        boolean isAuthenticated = userAuthenticationService.isAuthenticated(userId);
        if (!isAuthenticated) {
            // Alternative flow: Not Authenticated
            System.out.println("Authentication Error (Access Denied) for user: " + userId);
            return new ArrayList<>(); // Returning empty list as simplified error handling
            // In real implementation, might throw an exception or return error response
        }
        
        try {
            // Main flow: execute use case
            return viewFavoritesUseCase.execute(userId);
        } catch (Exception e) {
            // Alternative flow: Connection Interrupted
            System.out.println("Error Message (Connection Failed): " + e.getMessage());
            return new ArrayList<>(); // Returning empty list as simplified error handling
        }
    }
}