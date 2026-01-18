package com.example;

import com.example.model.CulturalObject;
import com.example.model.SearchCriteria;
import com.example.service.CulturalObjectService;

import java.util.List;
import java.util.Scanner;

/**
 * Main class that simulates the search flow for cultural objects.
 * Follows the use case steps:
 * 1. User activates search functionality (simulated by running the program).
 * 2. System shows the form for research (prints search options).
 * 3. User fills in the search form (enters criteria via console).
 * 4. User submits the search form.
 * 5. System processes the request (calls service).
 * 6. System returns a list of cultural goods that meet the search criteria.
 */
public class Main {

    // Simulates connection to server ETOUR. If false, exit condition "connection interrupted" is triggered.
    private static boolean connectionToServerActive = true;

    public static void main(String[] args) {
        System.out.println("=== Cultural Object Search System ===");

        // Entry condition: User must be logged in (simulated by a simple check)
        if (!isUserLoggedIn()) {
            System.out.println("Error: User is not logged in. Exiting.");
            return;
        }

        // Entry condition: System must be operational (simulated)
        if (!isSystemOperational()) {
            System.out.println("Error: System is not operational. Exiting.");
            return;
        }

        // Entry condition: Connection to server ETOUR must be active
        if (!isConnectionToServerActive()) {
            System.out.println("Error: Connection to server ETOUR is not active. Exiting.");
            return;
        }

        // Step 1: User activates the search functionality (implicit by running the program)
        System.out.println("Search functionality activated.");

        // Step 2: System shows the form for research
        showSearchFormInstructions();

        // Step 3 & 4: User fills and submits the search form
        SearchCriteria criteria = getUserSearchCriteria();

        // Simulate possible connection interruption (exit condition)
        if (!connectionToServerActive) {
            System.out.println("Exit Condition: Connection to server ETOUR interrupted. Exiting.");
            return;
        }

        // Step 5: System processes the request
        System.out.println("Processing request...");
        CulturalObjectService service = new CulturalObjectService();
        long startTime = System.currentTimeMillis(); // For quality requirement (response time)
        List<CulturalObject> results = service.searchCulturalObjects(criteria);
        long endTime = System.currentTimeMillis();

        // Step 6: System returns a list of cultural goods that meet the search criteria
        displayResults(results);

        // Quality requirement: Show time taken
        System.out.println("Search completed in " + (endTime - startTime) + " ms.");
    }

    /**
     * Simulates user login check.
     * @return true if user is considered logged in
     */
    private static boolean isUserLoggedIn() {
        // In a real system, this would check session or authentication token.
        // For simulation, we assume user is logged in.
        return true;
    }

    /**
     * Simulates system operational check.
     * @return true if system is operational
     */
    private static boolean isSystemOperational() {
        // For simulation, always true.
        return true;
    }

    /**
     * Checks if connection to server ETOUR is active.
     * @return true if connection is active
     */
    private static boolean isConnectionToServerActive() {
        // For simulation, we can randomly interrupt, but here we keep it simple.
        return connectionToServerActive;
    }

    /**
     * Displays instructions for the search form.
     */
    private static void showSearchFormInstructions() {
        System.out.println("\n--- Search Form ---");
        System.out.println("Enter search criteria (press Enter to skip a field).");
        System.out.println("Fields are case-insensitive and support partial matches for name and location.");
    }

    /**
     * Collects search criteria from the user via console input.
     * @return SearchCriteria object populated with user input
     */
    private static SearchCriteria getUserSearchCriteria() {
        Scanner scanner = new Scanner(System.in);
        SearchCriteria criteria = new SearchCriteria();

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            criteria.setName(name);
        }

        System.out.print("Type (e.g., Painting, Sculpture, Architecture): ");
        String type = scanner.nextLine().trim();
        if (!type.isEmpty()) {
            criteria.setType(type);
        }

        System.out.print("Location: ");
        String location = scanner.nextLine().trim();
        if (!location.isEmpty()) {
            criteria.setLocation(location);
        }

        System.out.print("Era (e.g., Ancient, Renaissance, Modern): ");
        String era = scanner.nextLine().trim();
        if (!era.isEmpty()) {
            criteria.setEra(era);
        }

        // Simulate a small chance of connection interruption after form submission
        // For demonstration, we keep it static; in real scenario could be a network check.
        // Uncomment next line to simulate interruption:
        // connectionToServerActive = false;

        return criteria;
    }

    /**
     * Displays the search results to the user.
     * @param results list of cultural objects matching the criteria
     */
    private static void displayResults(List<CulturalObject> results) {
        if (results.isEmpty()) {
            System.out.println("\nNo cultural objects found matching the criteria.");
        } else {
            System.out.println("\nFound " + results.size() + " cultural object(s):");
            for (CulturalObject obj : results) {
                System.out.println(obj);
            }
        }
    }
}