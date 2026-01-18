package com.example.adapter.in.web;

import com.example.application.port.in.SearchUseCase;
import com.example.application.port.out.SearchFormRenderer;
import com.example.domain.CulturalObject;
import com.example.ui.SearchForm;
import com.example.ui.SearchResult;
import java.util.List;
import java.util.Map;

/**
 * Web controller for handling search functionality.
 * Acts as the primary adapter for user interactions.
 */
public class SearchController {
    private final SearchUseCase searchUseCase;
    private final SearchFormRenderer formRenderer;

    public SearchController(SearchUseCase searchUseCase, SearchFormRenderer formRenderer) {
        this.searchUseCase = searchUseCase;
        this.formRenderer = formRenderer;
    }

    /**
     * Renders the search form.
     * Corresponds to Flow of Events 1: User activates search functionality.
     * @return the rendered search form
     */
    public SearchForm showSearchForm() {
        return formRenderer.render();
    }

    /**
     * Processes search form submission.
     * Corresponds to Flow of Events 4: User submits search form.
     * @param formData form field data from user submission
     * @return search results
     */
    public SearchResult handleSearchRequest(Map<String, String> formData) {
        try {
            // Process form data into search criteria
            Object criteria = formRenderer.processSubmission(formData);
            
            // Execute search - cast criteria to the appropriate type expected by SearchUseCase
            List<CulturalObject> objects = searchUseCase.execute((com.example.domain.SearchCriteria) criteria);
            
            // Create search result with timing (simulated)
            long searchTime = System.currentTimeMillis() % 1000; // Simulated time
            SearchResult result = new SearchResult(objects, searchTime);
            
            // Display results (in real app, this would return to view)
            if (objects.isEmpty()) {
                System.out.println("No results found for the given criteria.");
            } else {
                System.out.println("Found " + objects.size() + " cultural object(s):");
                for (CulturalObject obj : objects) {
                    System.out.println("  - " + obj.getTitle() + " by " + obj.getCreator());
                }
            }
            
            return result;
        } catch (Exception e) {
            System.out.println("Error during search: " + e.getMessage());
            // Return empty result on error
            return new SearchResult(List.of(), 0);
        }
    }

    /**
     * Activates search functionality - renders the search form.
     * Corresponds to Flow of Events 1: User activates search functionality.
     */
    public void activateSearchFunctionality() {
        // In a real application, this would typically return a view/model
        // For simplicity, we'll just indicate the form is rendered
        System.out.println("Search functionality activated. Form should be rendered.");
    }

    /**
     * Processes search form submission.
     * Corresponds to Flow of Events 4: User submits search form.
     * @param formData form field data from user submission
     * @return search results
     */
    public SearchResult submitSearchForm(Map<String, String> formData) {
        return handleSearchRequest(formData);
    }
}