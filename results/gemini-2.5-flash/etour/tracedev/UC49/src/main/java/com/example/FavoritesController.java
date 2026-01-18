package com.example;

import java.util.List;
import java.util.Collections; // For Collections.emptyList()

/**
 * Controller responsible for handling user requests related to favorites.
 * It coordinates between the View and the Service layers.
 * (Satisfies R1, R6 by managing Bookmark lists).
 */
public class FavoritesController {
    private FavoritesService favoritesService;
    private FavoritesView favoritesView; // Reference to the view for callbacks
    private List<Bookmark> currentFavorites; // Stores the currently displayed favorites

    // Assumed for demonstration; in a real app, this would come from an authenticated session
    private static final String AUTHENTICATED_TOURIST_ID = "tourist123";

    /**
     * Constructs a FavoritesController.
     * @param favoritesService The service layer for favorites operations.
     * @param favoritesView The view responsible for displaying results and errors.
     */
    public FavoritesController(FavoritesService favoritesService, FavoritesView favoritesView) {
        this.favoritesService = favoritesService;
        this.favoritesView = favoritesView;
        this.currentFavorites = Collections.emptyList(); // Initialize with an empty list
    }

    /**
     * Initiates the action to display personal favorites.
     * This method acts as the entry point for handling the 'display favorites' request from the view.
     */
    public void displayFavoritesAction() {
        System.out.println("FavoritesController: displayFavoritesAction initiated.");
        // Note: "Authenticated Tourist ID is known." - using a hardcoded ID for simulation
        String touristId = AUTHENTICATED_TOURIST_ID; // Or pass as param if from view

        try {
            // Call the service to get the personal favorites
            List<Bookmark> favorites = favoritesService.getPersonalFavorites(touristId);
            this.currentFavorites = favorites; // Update currentFavorites
            System.out.println("FavoritesController: Successfully retrieved favorites. Displaying to view.");
            // If successful, display the favorites in the view
            favoritesView.displayFavorites(favorites);
        } catch (FavoritesRetrievalError e) {
            // If an error occurs during retrieval, display an error message in the view (R7)
            System.err.println("FavoritesController: Error retrieving favorites: " + e.getMessage());
            favoritesView.displayError("Failed to load personal favorites. " + e.getMessage());
        }
    }

    /**
     * Gets the list of currently managed favorites.
     * @return An unmodifiable list of current favorites.
     */
    public List<Bookmark> getCurrentFavorites() {
        return Collections.unmodifiableList(currentFavorites);
    }
}