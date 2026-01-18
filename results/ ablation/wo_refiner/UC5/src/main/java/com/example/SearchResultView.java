package com.example;

import java.util.List;

/**
 * Boundary class for displaying search results.
 * Implements interactions for REQ-005 and REQ-006.
 */
public class SearchResultView {
    private CulturalHeritageController culturalHeritageController;

    public SearchResultView() {
        this.culturalHeritageController = new CulturalHeritageController();
    }

    public SearchResultView(CulturalHeritageController culturalHeritageController) {
        this.culturalHeritageController = culturalHeritageController;
    }

    /**
     * Performs search and displays results.
     * Corresponds to REQ-005 and REQ-006.
     */
    public void performSearch(String searchCriteria) {
        List<CulturalHeritage> results = culturalHeritageController.search(searchCriteria);
        displaySearchResults(results);
    }

    /**
     * Displays the search results.
     */
    public void displaySearchResults(List<CulturalHeritage> results) {
        System.out.println("=== Search Results ===");
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (CulturalHeritage item : results) {
                System.out.println("- " + item.getName() + " (" + item.getId() + ")");
            }
        }
        System.out.println("=====================");
    }

    /**
     * Highlights a selected item in the results.
     */
    public void highlightSelectedItem(String itemId) {
        System.out.println("Highlighting item: " + itemId);
    }

    /**
     * Called when an item is selected by the user.
     * This triggers the viewDetails flow from the sequence diagram.
     */
    public void onItemSelected(String culturalHeritageId) {
        highlightSelectedItem(culturalHeritageId);
        // The actual display is handled by CulturalHeritageCardView
        // In a real UI, this would trigger navigation to the detail view
        System.out.println("Item selected: " + culturalHeritageId);
    }

    public CulturalHeritageController getCulturalHeritageController() {
        return culturalHeritageController;
    }

    public void setCulturalHeritageController(CulturalHeritageController culturalHeritageController) {
        this.culturalHeritageController = culturalHeritageController;
    }
}