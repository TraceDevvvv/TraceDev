package com.example.ui;

import com.example.dto.SearchTouristResponse;
import java.util.Map;

/**
 * Presentation layer UI for searching tourists.
 * Displays forms and results.
 */
public class SearchTouristUI {
    private Map<String, Object> formData;

    public void displaySearchForm() {
        System.out.println("Displaying search form with empty fields.");
    }

    public void displayResults(SearchTouristResponse response) {
        if (response.isSuccess()) {
            System.out.println("Displaying search results: " + response.getTouristAccounts().size() + " tourists found.");
        } else {
            System.out.println("Error: " + response.getErrorMessage());
        }
    }

    public Map<String, Object> getFormData() {
        // In a real UI, this would capture form input.
        return formData;
    }

    public void setFormData(Map<String, Object> formData) {
        this.formData = formData;
    }
}