package com.etour.controller;

import com.etour.dto.BookmarkDTO;
import com.etour.exception.ConnectionException;
import com.etour.service.BookmarkService;
import com.etour.auth.TouristAuthenticator;
import java.util.List;

/**
 * Controller for handling favorite bookmarks display.
 * Orchestrates the flow as per sequence diagram.
 */
public class FavoritesController {
    private BookmarkService service;
    private TouristAuthenticator authenticator;

    // Constructor with service and authenticator
    public FavoritesController(BookmarkService service, TouristAuthenticator authenticator) {
        this.service = service;
        this.authenticator = authenticator;
    }

    /**
     * Entry point called by Tourist. Displays favorites if user is authenticated and connection is available.
     */
    public void selectDisplayFavorites() {
        // Step 1: Get authenticated user ID
        int userId = authenticator.getAuthenticatedUserId();
        
        // Step 2: Check authentication status
        boolean authenticationStatus = authenticator.isAuthenticated();
        
        if (authenticationStatus) {
            // User is authenticated
            // Check connection availability (simulated via system property)
            String connectionAvailable = System.getProperty("connection.available", "true");
            if (!Boolean.parseBoolean(connectionAvailable)) {
                showError("Connection not available");
                return;
            }
            
            try {
                // Call service to get favorites
                List<BookmarkDTO> bookmarks = service.getFavoritesForUser(userId);
                // Show the favorites list
                showFavorites(bookmarks);
            } catch (ConnectionException e) {
                // Connection failed or interrupted
                showError("Connection failed");
            } catch (SecurityException e) {
                // Should not happen because we already checked authentication, but handle anyway
                showError("Authentication required");
            } catch (Exception e) {
                // Any other exception
                showError("Unexpected error: " + e.getMessage());
            }
        } else {
            // User not authenticated
            showError("Authentication required");
        }
    }

    /**
     * Displays the list of favorites (simulated by printing to console).
     */
    public void showFavorites(List<BookmarkDTO> bookmarks) {
        System.out.println("=== Favorites List ===");
        if (bookmarks.isEmpty()) {
            System.out.println("No bookmarks found.");
        } else {
            for (BookmarkDTO b : bookmarks) {
                System.out.println(b);
            }
        }
        System.out.println("======================");
    }

    /**
     * Displays an error message (simulated by printing to console).
     */
    public void showError(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Additional method to handle the uploadBookmarks message from sequence diagram.
     * This corresponds to m6 in the sequence diagram.
     */
    public void uploadBookmarks(int userId) {
        service.uploadBookmarks(userId);
    }
}