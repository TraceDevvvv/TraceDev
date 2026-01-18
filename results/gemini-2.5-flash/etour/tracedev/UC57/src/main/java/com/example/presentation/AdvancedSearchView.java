package com.example.presentation;

import com.example.dto.AdvancedSearchRequestDTO;
import com.example.dto.SiteResultDTO;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * View component for advanced search functionality.
 * Responsible for displaying forms, results, and capturing user input.
 * In a real application, this would be a UI framework component (e.g., Spring MVC View, JavaFX, etc.)
 */
public class AdvancedSearchView {
    private AdvancedSearchController controller; // Controller for handling UI events
    private AdvancedSearchFormModel currentFormModel; // Model for form data
    private SearchResultModel searchResultModel; // Model for search results

    /**
     * Sets the controller for this view.
     * @param controller The {@link AdvancedSearchController} instance.
     */
    public void setController(AdvancedSearchController controller) {
        this.controller = controller;
    }

    /**
     * Simulates the user enabling the advanced search feature.
     * Calls the controller to initiate the process.
     */
    public void enableAdvancedSearch() {
        System.out.println("\nAdvancedSearchView: User enabled advanced search.");
        if (controller != null) {
            controller.requestAdvancedSearchForm();
        }
    }

    /**
     * Displays the advanced search form to the user.
     *
     * @param form The {@link AdvancedSearchFormModel} containing initial form data or defaults.
     */
    public void displayAdvancedSearchForm(AdvancedSearchFormModel form) {
        System.out.println("\n--- Advanced Search Form ---");
        System.out.println("Keyword: " + (form.keyword != null ? form.keyword : ""));
        System.out.println("Category: " + (form.category != null ? form.category : ""));
        System.out.println("Max Distance (km): " + (form.maxDistanceKm > 0 ? form.maxDistanceKm : ""));
        System.out.println("Other Criteria: " + form.otherCriteria);
        System.out.println("--------------------------");
        this.currentFormModel = form; // Store the form model for later filling/submission
    }

    /**
     * Simulates the user filling in the advanced search form.
     * Updates the internal {@link AdvancedSearchFormModel}.
     *
     * @param criteria The {@link AdvancedSearchFormModel} with user-provided data.
     */
    public void fillAdvancedSearchForm(AdvancedSearchFormModel criteria) {
        System.out.println("AdvancedSearchView: User filling advanced search form with: " + criteria);
        this.currentFormModel = criteria; // Update the form model
    }

    /**
     * Simulates the user submitting the form.
     * Converts the {@link AdvancedSearchFormModel} to {@link AdvancedSearchRequestDTO} and calls the controller.
     */
    public void submitForm() {
        System.out.println("\nAdvancedSearchView: User submitting advanced search form.");
        if (controller != null && currentFormModel != null) {
            // Convert AdvancedSearchFormModel to AdvancedSearchRequestDTO for the application layer
            AdvancedSearchRequestDTO requestDTO = new AdvancedSearchRequestDTO(
                currentFormModel.keyword,
                currentFormModel.category,
                currentFormModel.maxDistanceKm,
                currentFormModel.otherCriteria
            );
            controller.submitAdvancedSearch(requestDTO);
        } else {
            System.err.println("Error: Controller or FormModel not set. Cannot submit form.");
        }
    }

    /**
     * Displays the search results to the user.
     *
     * @param results A list of {@link SiteResultDTO} to be displayed.
     */
    public void displaySearchResults(List<SiteResultDTO> results) {
        System.out.println("\n--- Advanced Search Results ---");
        this.searchResultModel = new SearchResultModel(results); // Update the result model
        if (results == null || results.isEmpty()) {
            System.out.println("No results found for your search criteria.");
        } else {
            results.forEach(System.out::println);
        }
        System.out.println("-------------------------------");
    }

    /**
     * Displays an error message to the user.
     * @param errorMessage The message describing the error.
     */
    public void displayError(String errorMessage) {
        System.err.println("\n--- ERROR ---");
        System.err.println(errorMessage);
        System.err.println("-------------");
    }
}