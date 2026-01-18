
package com.example;

import java.util.List;

/**
 * Main class to demonstrate the Favorites system.
 * This class sets up the dependencies and simulates the user (Tourist) interaction
 * as described in the sequence diagram.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Favorites Application Demo...");

        // 1. Setup the infrastructure (Dependency Injection)
        ETOURServerConnection etourServerConnection = new ETOURServerConnection();
        IFavoritesRepository favoritesRepository = new ETOURFavoritesRepository(etourServerConnection);
        FavoritesService favoritesService = new FavoritesService(favoritesRepository);
        
        // The View needs a reference to the Controller, and the Controller needs a reference to the View.\n        // This is a common pattern where one creates the other, or they are injected.\n        // For simplicity, we'll create the view first, then pass it to the controller,\n        // and finally set the controller back to the view.
        FavoritesView favoritesView = new FavoritesView();
        FavoritesController favoritesController = new FavoritesController(favoritesService, favoritesView);
        favoritesView.setController(favoritesController); // Link view back to controller

        // --- Simulate the Use Case: Tourist views personal favorites ---

        System.out.println("\n--- Scenario 1: Successful retrieval of favorites ---");
        // Simulate a tourist selecting "Display Favorites"
        // This will use the default touristId='tourist123' configured in FavoritesController
        favoritesView.selectsDisplayFavoritesFeature();
        // The view will display the favorites and then internally display a message to the tourist
        favoritesView.displayMessageToTourist("Displays list of bookmarks promptly.");


        // --- Simulate an error scenario: ETOUR Server connection interrupted ---
        System.out.println("\n--- Scenario 2: ETOUR Server Connection Error ---");
        // To simulate a connection error, we can temporarily change the tourist ID
        // or modify the ETOURServerConnection's behavior if it were more flexible.
        // For this demo, let's inject a new repository that will cause a connection error.
        System.out.println("Simulating an error by changing the 'touristId' to trigger an error pattern.");
        // We'll create a temporary controller instance for this scenario that uses a query triggering error
        // In a real application, the ETOURServerConnection might have a configurable error state.
        
        // Directly call the controller with a tourist ID that will cause a ConnectionError in fetchData
        // (Assuming 'touristId' is passed or determined dynamically)
        // For this example, let's temporarily override the constant for demonstration purposes,\n        // or ideally, the controller's action method would take the touristId as a parameter.
        
        // Re-wiring for error simulation: This is a bit clunky for demonstration.
        // A better approach would be to make ETOURServerConnection configurable for errors.
        // For the sake of following the sequence diagram, let's create new instances that lead to error.

        // Force a connection error by querying an ID that ETOURServerConnection recognizes as an error trigger
        // This relies on the current simulation logic in ETOURServerConnection.fetchData()
        // which throws ConnectionError if "error_connection" is in the query.
        System.out.println("\nInitiating displayFavoritesAction which will trigger a ConnectionError in ETOURServerConnection.");
        
        // This part needs to simulate a different call path or state.
        // The easiest way is to mock a different tourist ID that would map to an error state.
        // Since FavoritesController hardcodes `AUTHENTICATED_TOURIST_ID`, let's make it configurable
        // for `displayFavoritesAction` if we want to simulate different tourists from the outside.
        // For now, let's assume the controller can be told to use a specific tourist ID for a test run.
        // (Making an assumption here for the demo's flexibility).
        
        // Recreating controller to simulate a different session/user for error path:
        FavoritesController errorController = new FavoritesController(favoritesService, favoritesView) {
            @Override
            public void displayFavoritesAction() {
                String touristIdWithError = "error_connection_user"; // This ID will trigger ConnectionError
                System.out.println("FavoritesController: displayFavoritesAction initiated for error scenario (touristId: " + touristIdWithError + ").");
                try {
                    List<Bookmark> favorites = favoritesService.getPersonalFavorites(touristIdWithError);
                    // This path should not be reached in an error scenario
                    favoritesView.displayFavorites(favorites);
                } catch (FavoritesRetrievalError e) {
                    System.err.println("FavoritesController: Caught expected FavoritesRetrievalError: " + e.getMessage());
                    favoritesView.displayError("Failed to load personal favorites. " + e.getMessage());
                    favoritesView.displayMessageToTourist("Displaying error message to tourist.");
                }
            }
        };
        // Now, trigger the error scenario
        errorController.displayFavoritesAction();

        // --- Simulate an empty favorites list ---
        System.out.println("\n--- Scenario 3: Empty favorites list ---");
        // Similar to the error scenario, let's simulate an empty list result.
        FavoritesController emptyFavoritesController = new FavoritesController(favoritesService, favoritesView) {
            @Override
            public void displayFavoritesAction() {
                String emptyTouristId = "emptyUser"; // This ID will trigger an empty JSON array in fetchData
                System.out.println("FavoritesController: displayFavoritesAction initiated for empty favorites (touristId: " + emptyTouristId + ").");
                try {
                    List<Bookmark> favorites = favoritesService.getPersonalFavorites(emptyTouristId);
                    favoritesView.displayFavorites(favorites);
                    favoritesView.displayMessageToTourist("Displays empty list of bookmarks.");
                } catch (FavoritesRetrievalError e) {
                    System.err.println("FavoritesController: Caught unexpected FavoritesRetrievalError for empty list: " + e.getMessage());
                    favoritesView.displayError("Failed to load personal favorites for empty list. " + e.getMessage());
                }
            }
        };
        emptyFavoritesController.displayFavoritesAction();


        System.out.println("\nFavorites Application Demo Finished.");
    }
}
