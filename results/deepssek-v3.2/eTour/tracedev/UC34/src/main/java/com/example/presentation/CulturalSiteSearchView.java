package com.example.presentation;

import com.example.dto.CulturalSiteDTO;
import com.example.dto.SearchFormDTO;
import java.util.List;

/**
 * View Component in MVC.
 */
public class CulturalSiteSearchView {
    public void displaySearchForm(SearchFormDTO searchFormData) {
        // In a real GUI application, this would update the UI.
        System.out.println("Displaying search form with data: " + searchFormData);
    }

    public void displaySearchResults(List<CulturalSiteDTO> results) {
        // Display search results in the UI.
        System.out.println("Displaying search results: " + results.size() + " sites found.");
        for (CulturalSiteDTO dto : results) {
            System.out.println(" - " + dto.getName() + " (Relevance: " + dto.getRelevanceScore() + ")");
        }
    }

    public SearchFormDTO getSearchInput() {
        // Simulate retrieving input from the UI (e.g., form fields).
        SearchFormDTO dto = new SearchFormDTO();
        dto.setSiteNameKeyword("Museum");
        dto.setSiteTypeFilter("MUSEUM");
        dto.setRadiusFilter(10.0);
        // Dates and rating set to default for simplicity
        return dto;
    }

    public void displayError(String message) {
        // Display error message in the UI.
        System.err.println("Error: " + message);
    }
}