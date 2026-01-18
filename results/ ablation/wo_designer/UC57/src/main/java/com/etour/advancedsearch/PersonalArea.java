package com.etour.advancedsearch;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the tourist's personal area where advanced search is initiated.
 * Simulates the UI interaction flow.
 */
public class PersonalArea {
    private Tourist tourist;
    private AdvancedSearchService searchService;
    private Scanner scanner;

    public PersonalArea(Tourist tourist) {
        this.tourist = tourist;
        this.searchService = new AdvancedSearchService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the advanced search flow as described in the use case.
     */
    public void startAdvancedSearch() {
        System.out.println("=== Advanced Search Feature ===");
        System.out.println("Tourist: " + tourist.getUsername());

        // Step 1: Tourist enables the advanced search feature from the personal area.
        System.out.println("\n1. Enabling advanced search feature...");

        // Step 2: Tourist views the advanced search form.
        System.out.println("2. Displaying advanced search form...");

        // Step 3: Tourist fills in the advanced search form.
        AdvancedSearchForm form = fillAdvancedSearchForm();

        // Step 4: Tourist submits the advanced search form.
        System.out.println("4. Submitting form...");

        // Step 5: System gets the position of the tourist.
        Location touristLocation = getTouristLocation();
        tourist.setCurrentLocation(touristLocation);
        System.out.println("5. Tourist location acquired: " + touristLocation);

        // Step 6: System processes the request.
        System.out.println("6. Processing request...");
        try {
            List<Site> results = searchService.performAdvancedSearch(form, touristLocation);

            // Exit Conditions: The system displays a list of results.
            displayResults(results);

            // Simulate possible interruption (second exit condition)
            simulateInterruption();

        } catch (InterruptedException e) {
            System.err.println("Search was interrupted: " + e.getMessage());
            // Handle interruption (e.g., show error message)
        }
    }

    private AdvancedSearchForm fillAdvancedSearchForm() {
        System.out.println("3. Please fill the advanced search form:");
        AdvancedSearchForm form = new AdvancedSearchForm();

        System.out.print("Name keyword (or press Enter to skip): ");
        String nameKeyword = scanner.nextLine();
        if (!nameKeyword.trim().isEmpty()) {
            form.setNameKeyword(nameKeyword);
        }

        System.out.print("Category (Landmark, Museum, Religious, Park, Monument) or press Enter: ");
        String category = scanner.nextLine();
        if (!category.trim().isEmpty()) {
            form.setCategory(category);
        }

        System.out.print("Minimum rating (0.0 - 5.0) or press Enter: ");
        String ratingInput = scanner.nextLine();
        if (!ratingInput.trim().isEmpty()) {
            try {
                form.setMinRating(Double.parseDouble(ratingInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating, using default.");
            }
        }

        System.out.print("Maximum distance in km (integer) or press Enter: ");
        String distanceInput = scanner.nextLine();
        if (!distanceInput.trim().isEmpty()) {
            try {
                form.setMaxDistance(Integer.parseInt(distanceInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid distance, using default.");
            }
        }

        System.out.println("Form filled: " + form);
        return form;
    }

    private Location getTouristLocation() {
        // In a real app, this would come from GPS or IP geolocation
        // For simulation, we use a fixed location (Paris coordinates)
        return new Location(48.8566, 2.3522); // Paris center
    }

    private void displayResults(List<Site> results) {
        System.out.println("\n=== Search Results ===");
        if (results.isEmpty()) {
            System.out.println("No sites found matching your criteria.");
        } else {
            System.out.println("Found " + results.size() + " site(s):");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }

    private void simulateInterruption() {
        // Simulate a random chance of server interruption (ETOUR)
        double chance = Math.random();
        if (chance < 0.1) { // 10% chance
            System.err.println("\n[Error] The connection to the server ETOUR is interrupted.");
            // In a real application, you would handle reconnection or show an error message.
        }
    }

    public void close() {
        scanner.close();
    }
}