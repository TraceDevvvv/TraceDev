package com.example;

import com.example.model.SearchCriteria;
import com.example.model.Site;
import com.example.service.LocationService;
import com.example.service.SearchService;
import com.example.ui.SearchForm;
import java.util.List;

/**
 * Main application class orchestrating the search use case.
 * Guest User is assumed to be logged on (Entry Condition).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Guest User logged on.");

        // Step 1 & 2: Activate search functionality and display form
        SearchForm form = new SearchForm();

        // Step 3 & 4: User fills and submits form
        SearchCriteria criteria = form.displayAndCapture();
        form.close();

        // Step 5: Get user's location
        LocationService locationService = new LocationService();
        LocationService.UserLocation userLoc = locationService.getCurrentUserLocation();
        System.out.println("User location detected: Latitude " + userLoc.latitude + ", Longitude " + userLoc.longitude);

        // Step 6: Process research
        SearchService searchService = new SearchService();
        try {
            List<Site> results = searchService.searchSites(criteria, userLoc.latitude, userLoc.longitude);

            // Exit condition: display list of sites
            if (results.isEmpty()) {
                System.out.println("No sites found matching your criteria.");
            } else {
                System.out.println("\nFound " + results.size() + " site(s):");
                for (Site site : results) {
                    System.out.println(" - " + site.getName() + " (" + site.getCategory() + ")");
                }
            }
        } catch (RuntimeException e) {
            // Handle interruption of connection to server ETOUR
            System.err.println("Error: " + e.getMessage());
            System.out.println("Please try again later.");
        }

        System.out.println("\nSearch completed.");
    }
}