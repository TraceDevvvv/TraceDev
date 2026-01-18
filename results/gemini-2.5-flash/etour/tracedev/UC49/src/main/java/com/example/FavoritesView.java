package com.example;

import java.util.List;

/**
 * Represents the user interface for displaying and interacting with favorite bookmarks.
 * This class is responsible for presenting data to the user and capturing user input.
 * (Satisfies R4, R7 by including onSelectDisplayFavorites and displayError).
 */
public class FavoritesView {
    // Controller instance to delegate user actions
    private FavoritesController controller;

    /**
     * Sets the controller for this view. This allows the view to trigger actions
     * in the controller based on user interaction.
     * @param controller The FavoritesController instance.
     */
    public void setController(FavoritesController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of favorite bookmarks to the user.
     * @param favorites The list of Bookmark objects to display.
     */
    public void displayFavorites(List<Bookmark> favorites) {
        System.out.println("\n--- Displaying Personal Favorites ---");
        if (favorites == null || favorites.isEmpty()) {
            System.out.println("No favorites found.");
            return;
        }
        for (int i = 0; i < favorites.size(); i++) {
            Bookmark b = favorites.get(i);
            System.out.printf("%d. %s (ID: %s) - %s%n", (i + 1), b.getName(), b.getId(), b.getUrl());
        }
        System.out.println("-----------------------------------\n");
    }

    /**
     * Simulates a user selecting to display their favorites.
     * This method acts as the entry point from the 'Tourist' actor in the sequence diagram.
     */
    public void selectsDisplayFavoritesFeature() {
        System.out.println("FavoritesView: Tourist selected 'Display Favorites'.");
        if (controller != null) {
            // Delegate the action to the controller
            controller.displayFavoritesAction();
        } else {
            System.err.println("FavoritesView: Controller is not set. Cannot display favorites.");
        }
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to be displayed.
     */
    public void displayError(String message) {
        System.err.println("\n!!! ERROR !!!");
        System.err.println("Message: " + message);
        System.err.println("!!! ERROR !!!\n");
    }

    /**
     * Simulates the view displaying a message to the tourist.
     * (Corresponds to "View --> T : displays list of bookmarks promptly" and
     * "View --> T : displayErrorMessage(\"Failed to load personal favorites.\")" in sequence diagram).
     * @param message The message to display.
     */
    public void displayMessageToTourist(String message) {
        System.out.println("Message to Tourist: " + message);
    }
}