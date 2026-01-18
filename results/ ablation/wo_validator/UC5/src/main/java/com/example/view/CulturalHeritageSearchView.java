package com.example.view;

import com.example.dto.CulturalHeritageSearchDTO;
import java.util.List;

/**
 * View responsible for displaying search results of cultural heritage items.
 * Also responsible for capturing user selection.
 */
public class CulturalHeritageSearchView {

    private String selectedHeritageId;

    /**
     * Displays a list of cultural heritage search results.
     * @param results the list of CulturalHeritageSearchDTO to display
     */
    public void displaySearchResults(List<CulturalHeritageSearchDTO> results) {
        System.out.println("=== Cultural Heritage Search Results ===");
        if (results == null || results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (CulturalHeritageSearchDTO dto : results) {
                System.out.println("ID: " + dto.getId() + " | Name: " + dto.getName() + " | Brief: " + dto.getBriefInfo());
            }
        }
        System.out.println("========================================");
    }

    /**
     * Gets the ID of the cultural heritage selected by the user.
     * This method simulates the user selecting an item (e.g., from UI).
     * @return the selected heritage ID
     */
    public String getSelectedHeritageId() {
        // In a real application, this would be obtained from user interaction.
        // For simulation, we return a stored value.
        return selectedHeritageId;
    }

    /**
     * Sets the selected heritage ID (for simulation purposes).
     * @param id the ID to set as selected
     */
    public void setSelectedHeritageId(String id) {
        this.selectedHeritageId = id;
    }
}