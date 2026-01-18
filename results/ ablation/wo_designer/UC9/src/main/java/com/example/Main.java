package com.example;

import com.example.controller.RestPointController;
import com.example.dto.RestPoint;
import com.example.dto.RestPointSearchCriteria;
import com.example.ui.SearchForm;
import java.util.List;

/**
 * Main class to run the application.
 * Simulates the complete use case flow.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Point of Rest Search Application...\n");

        // Step 1 & 2: User activates search and system shows form
        SearchForm form = new SearchForm();
        RestPointSearchCriteria criteria = form.showForm();

        System.out.println("\nProcessing your search...");

        // Step 3-5: System processes the request
        RestPointController controller = new RestPointController();
        List<RestPoint> results = controller.handleSearchRequest(criteria);

        // Exit condition: Display results
        displayResults(results);

        System.out.println("\nThank you for using the service.");
    }

    private static void displayResults(List<RestPoint> results) {
        if (results.isEmpty()) {
            System.out.println("No matching points of rest found.");
        } else {
            System.out.println("\nFound " + results.size() + " point(s) of rest:");
            for (RestPoint rp : results) {
                System.out.println("  - " + rp.getName() + " at " + rp.getLocation() +
                        " (Shelter: " + (rp.isHasShelter() ? "Yes" : "No") +
                        ", Water: " + (rp.isHasWater() ? "Yes" : "No") +
                        ", Rating: " + rp.getRating() + "/5)");
            }
        }
    }
}