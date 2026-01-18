package com.example.advancedsearch;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class to simulate the Advanced Search use case flow.
 * This class orchestrates the interaction between the Tourist, AdvancedSearchForm,
 * and AdvancedSearchService to demonstrate the advanced search functionality.
 */
public class AdvancedSearchApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tourist tourist = new Tourist("JohnDoe");
        AdvancedSearchService searchService = new AdvancedSearchService();

        System.out.println("--- Advanced Search Use Case Simulation ---");

        // Entry condition: The Tourist has successfully authenticated to the system.
        if (!tourist.authenticate()) {
            System.out.println("Authentication failed. Exiting application.");
            scanner.close();
            return;
        }

        // Simulate setting tourist's current location
        // For demonstration, let's set a location in Paris
        tourist.setCurrentLocation(new Location(48.8566, 2.3522)); // Paris, France
        System.out.println("Tourist's current location set to: " + tourist.getCurrentLocation());

        // 1. Enable the advanced search feature from your personal area.
        System.out.println("\nTourist '" + tourist.getUsername() + "' enables the advanced search feature.");

        // 2. View the advanced search form.
        System.out.println("\nDisplaying advanced search form...");
        AdvancedSearchForm searchForm = new AdvancedSearchForm();

        // 3. Fill in the form of advanced search and submit.
        System.out.println("Please fill in the advanced search criteria (leave blank for no filter):");

        System.out.print("Keyword (e.g., 'tower', 'museum'): ");
        String keyword = scanner.nextLine();
        searchForm.setKeyword(keyword);

        System.out.print("Category (e.g., 'Landmark', 'Museum', 'Park'): ");
        String category = scanner.nextLine();
        searchForm.setCategory(category);

        System.out.print("Maximum distance from current location in KM (e.g., 10.0, enter 0 for no limit): ");
        double maxDistance = 0;
        try {
            String distanceInput = scanner.nextLine();
            if (!distanceInput.trim().isEmpty() && Double.parseDouble(distanceInput) > 0) {
                maxDistance = Double.parseDouble(distanceInput);
                searchForm.setMaxDistanceKm(maxDistance);
            } else {
                searchForm.setMaxDistanceKm(Double.MAX_VALUE); // No limit
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid distance input. No maximum distance set.");
            searchForm.setMaxDistanceKm(Double.MAX_VALUE);
        }

        System.out.print("Minimum average rating (0.0 to 5.0, enter 0 for no minimum): ");
        double minRating = 0.0;
        try {
            String ratingInput = scanner.nextLine();
            if (!ratingInput.trim().isEmpty()) {
                minRating = Double.parseDouble(ratingInput);
                searchForm.setMinRating(minRating);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating input. No minimum rating set.");
            searchForm.setMinRating(0.0);
        }

        System.out.print("Desired amenities (comma-separated, e.g., 'wifi,restaurant'): ");
        String amenitiesInput = scanner.nextLine();
        if (!amenitiesInput.trim().isEmpty()) {
            List<String> amenities = Arrays.asList(amenitiesInput.split(",")).stream()
                                            .map(String::trim)
                                            .filter(s -> !s.isEmpty())
                                            .collect(java.util.ArrayList::new, java.util.ArrayList::add, java.util.ArrayList::addAll);
            searchForm.setAmenities(amenities);
        }

        System.out.print("Requires accessibility (yes/no): ");
        String accessibilityInput = scanner.nextLine();
        searchForm.setHasAccessibility(accessibilityInput.equalsIgnoreCase("yes"));

        System.out.println("\nSubmitting search form: " + searchForm);

        // 4. Gets the position of relying on the tourist event of the use location and process the request.
        System.out.println("Processing search request using tourist's location...");
        List<Site> searchResults = null;
        try {
            searchResults = searchService.performAdvancedSearch(searchForm, tourist.getCurrentLocation());

            // Exit conditions: The system displays a list of results.
            if (searchResults.isEmpty()) {
                System.out.println("\nNo sites found matching your criteria.");
            } else {
                System.out.println("\n--- Search Results ---");
                for (int i = 0; i < searchResults.size(); i++) {
                    Site site = searchResults.get(i);
                    System.out.println((i + 1) + ". " + site.getName() + " (" + site.getCategory() + ")");
                    System.out.println("   Description: " + site.getDescription());
                    System.out.println("   Rating: " + site.getAverageRating() + "/5.0");
                    System.out.println("   Amenities: " + site.getAmenities());
                    System.out.println("   Accessible: " + site.hasAccessibility());
                    // Calculate and display distance if maxDistanceKm was set
                    if (searchForm.getMaxDistanceKm() != Double.MAX_VALUE && tourist.getCurrentLocation() != null) {
                        double distance = tourist.getCurrentLocation().calculateDistance(site.getLocation()) * 111; // Rough km conversion
                        System.out.printf("   Distance from you: %.2f km\n", distance);
                    }
                    System.out.println("--------------------");
                }
            }
        } catch (AdvancedSearchService.ETOURConnectionException e) {
            // Interruption of the connection to the server ETOUR.
            System.err.println("\nError: " + e.getMessage());
            System.err.println("Search could not be completed due to server connection issues.");
        } catch (AdvancedSearchService.SearchTimeoutException e) {
            // Quality requirement: The system requirements into the transaction in more than 15 seconds.
            System.err.println("\nError: " + e.getMessage());
            System.err.println("The search operation timed out. Please try again.");
        } catch (Exception e) {
            System.err.println("\nAn unexpected error occurred during search: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            System.out.println("\n--- Simulation End ---");
        }
    }
}