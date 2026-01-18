package com.example;

import com.example.feature.FavoritesViewer;
import com.example.model.Tourist;
import com.example.service.AuthenticationService;
import com.example.service.BookmarkService;

/**
 * Main class to demonstrate the use case.
 */
public class Main {
    public static void main(String[] args) {
        // Create a sample authenticated Tourist
        Tourist tourist = new Tourist("tourist1", "john_doe");

        // Initialize serv
        AuthenticationService authService = new AuthenticationService();
        BookmarkService bookmarkService = new BookmarkService();
        FavoritesViewer favoritesViewer = new FavoritesViewer(authService, bookmarkService);

        // Execute the use case: Tourist views favorites
        System.out.println("=== Tourist Viewing Favorites ===");
        favoritesViewer.viewFavorites(tourist);

        // Demonstrate with an unauthenticated tourist (edge case)
        System.out.println("\n=== Unauthenticated Tourist Attempt ===");
        Tourist nullTourist = null;
        favoritesViewer.viewFavorites(nullTourist);
    }
}