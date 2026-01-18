package com.etour.presentation;

import com.etour.application.FavoritesController;
import com.etour.application.AuthenticationService;
import com.etour.infrastructure.FavoriteRepository;
import com.etour.infrastructure.ETOURServerConnector;
import com.etour.infrastructure.Database;
import com.etour.dto.FavoriteDTO;
import com.etour.domain.ConnectionException;
import java.util.List;

/**
 * UI component for displaying favorites.
 * Called by Tourist actor as per sequence diagram.
 */
public class FavoritesView {
    private FavoritesController controller;

    public FavoritesView(FavoritesController controller) {
        this.controller = controller;
    }

    // This method corresponds to sequence diagram message m1: Tourist -> UI "Select 'View Favorites'"
    public void handleViewFavoritesSelection(String touristId) {
        try {
            // Validate session via AuthenticationService (as per sequence diagram)
            AuthenticationService authService = new AuthenticationService();
            if (!authService.validateSession(touristId)) {
                displayError("Invalid session");
                return;
            }

            // Call controller to get favorites
            List<FavoriteDTO> favorites = controller.displayFavorites(touristId);

            // Render the list (simulate display) - corresponds to message m13: UI -> UI "Render favorites list"
            renderFavoritesList(favorites);

        } catch (ConnectionException e) {
            displayError("Connection failed");
        } catch (Exception e) {
            displayError("Error: " + e.getMessage());
        }
    }

    /**
     * Called when Tourist selects "View Favorites".
     * Implements the sequence diagram flow.
     */
    public void onViewFavoritesSelected(String touristId) {
        // Delegate to the handler method that corresponds to the sequence diagram message
        handleViewFavoritesSelection(touristId);
    }

    /**
     * Displays the list of favorites.
     * Quality requirement: displayed within 2 seconds.
     * Corresponds to message m13: UI -> UI "Render favorites list"
     */
    public void renderFavoritesList(List<FavoriteDTO> favorites) {
        System.out.println("=== Favorites List ===");
        for (FavoriteDTO dto : favorites) {
            System.out.println("Site: " + dto.getSiteName() + " (ID: " + dto.getSiteId() + ") added on " + dto.getAddedDate());
        }
        // Note: In a real UI, this would update the view.
        // After rendering, the flow returns to Tourist with message m14: UI -> Tourist "Display favorites"
        returnDisplayFavorites();
    }

    // This method corresponds to sequence diagram message m14: UI -> Tourist "Display favorites"
    private void returnDisplayFavorites() {
        // In a real UI, this would be the completion of the UI update.
        // For simulation, we assume the display is complete.
    }

    // This method corresponds to sequence diagram message m15: UI -> UI note "Displayed within 2 seconds"
    public void noteDisplayedWithin2Seconds() {
        // This is a note in the sequence diagram, not a method call.
        // In implementation, it's a quality requirement that the display happens within 2 seconds.
        // Log or ensure the timing requirement is met.
    }

    public void showFavorites(List<FavoriteDTO> favorites) {
        // This method is kept for backward compatibility but delegates to the render method
        renderFavoritesList(favorites);
    }

    public void displayError(String message) {
        System.err.println("Error: " + message);
        // In a real UI, this would show an error dialog.
        // This could correspond to sequence diagram lost message m12: Controller -> UI "Error message"
        // The error message is displayed to the user.
    }

    // For demonstration, a simple main method to simulate the sequence
    public static void main(String[] args) {
        // Setup infrastructure
        ETOURServerConnector connector = new ETOURServerConnector();
        Database db = new Database();
        FavoriteRepository repo = new FavoriteRepository(connector, db);
        AuthenticationService auth = new AuthenticationService();
        FavoritesController controller = new FavoritesController(repo, auth);
        FavoritesView view = new FavoritesView(controller);

        // Simulate Tourist with ID "tourist123"
        Tourist tourist = new Tourist("tourist123", "John Doe", "john@example.com");

        // Simulate selecting "View Favorites" - message m1
        System.out.println("Tourist selects 'View Favorites'");
        view.onViewFavoritesSelected(tourist.getId());
    }
}