package com.example.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class to demonstrate the search functionality based on the UML diagrams.
 * This class simulates user interaction and orchestrates the components.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Search Application Simulation ---");

        // 1. Initialize core components (Dependency Injection)
        PerformanceMonitor performanceMonitor = new PerformanceMonitor();
        SiteRepository siteRepository = new SiteDatabaseRepository();
        SearchService searchService = new SearchService(siteRepository, performanceMonitor);
        SearchView searchView = new SearchView();
        SearchController searchController = new SearchController(searchService, searchView);

        // --- Simulate User Interaction ---

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Sequence Diagram: User -> SearchView : activateSearch() (Modified to satisfy R4)
        System.out.println("\n[User] activating search...");
        searchView.activateSearch();
        // SearchView displays form, waiting for user input.

        do {
            System.out.print("User Input (e.g., name=ruins; or forceConnectionError=true; or forceTimeout=true,timeoutDelay=600; or exit): ");
            userInput = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            }

            Map<String, String> criteria = parseUserInput(userInput);

            if (criteria.isEmpty() && !userInput.isEmpty() && !userInput.equals("forceConnectionError=true") && !userInput.startsWith("forceTimeout=true")) {
                System.out.println("Invalid input format. Please use 'key=value' pairs separated by ';'.");
                continue;
            }

            // Sequence Diagram: User -> SearchView : submitSearchForm(criteria) (Modified to satisfy R7)
            System.out.println("\n[User] submitting search with criteria: " + criteria);
            searchView.submitSearchForm(criteria);
            // After submit, the controller takes over and eventually updates the view, which prints results/errors.

        } while (true);

        System.out.println("\n--- Search Application Simulation Ended ---");
        scanner.close();
    }

    /**
     * Parses user input string into a Map of criteria.
     * Example input: "name=ruins;location=greece"
     * Supports "forceConnectionError=true" and "forceTimeout=true,timeoutDelay=X" for testing.
     * @param inputString The raw string input from the user.
     * @return A Map representing the search criteria.
     */
    private static Map<String, String> parseUserInput(String inputString) {
        Map<String, String> criteria = new HashMap<>();
        if (inputString == null || inputString.isEmpty()) {
            return criteria;
        }

        String[] pairs = inputString.split("(?<!\\\\);"); // Split by semicolon, but not if escaped
        for (String pair : pairs) {
            String trimmedPair = pair.trim();
            if (trimmedPair.isEmpty()) continue;

            String[] keyValue = trimmedPair.split("=", 2);
            if (keyValue.length == 2) {
                criteria.put(keyValue[0].trim(), keyValue[1].trim());
            } else {
                // Handle cases like "forceTimeout=true,timeoutDelay=600"
                if (trimmedPair.startsWith("forceTimeout=true")) {
                    String[] timeoutDetails = trimmedPair.split(",", 2);
                    criteria.put("forceTimeout", "true");
                    if (timeoutDetails.length > 1) {
                        String delayPart = timeoutDetails[1].trim();
                        if (delayPart.startsWith("timeoutDelay=")) {
                            criteria.put("timeoutDelay", delayPart.substring("timeoutDelay=".length()).trim());
                        }
                    }
                } else if (!trimmedPair.isEmpty()) { // Only print warning if it's not an empty string after trim
                    System.out.println("Warning: Malformed criteria part '" + trimmedPair + "'. Skipping.");
                }
            }
        }
        return criteria;
    }
}