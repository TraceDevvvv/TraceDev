
package com.example.presentation;

import com.example.application.PointOfRestSearchService;
import com.example.exceptions.ApplicationServiceException;
import com.example.infrastructure.AuthenticationService;
import com.example.infrastructure.Session;
import com.example.infrastructure.SystemStatus;
import com.example.infrastructure.EtourApiAdapter;
import com.example.application.EtourPointOfRestRepository; // Corrected import to application
import com.example.application.IPointOfRestRepository; // Corrected import to application
import com.example.model.PointOfRest;
import com.example.model.SearchCriteria;

import java.util.List;
import java.util.Scanner;

/**
 * Presentation layer component representing the user interface.
 * Handles user input and displays results.
 */
public class UserInterface {

    private final PointOfRestSearchService pointOfRestSearchService;
    private final SystemStatus systemStatus;
    private final AuthenticationService authenticationService;
    private Session currentUserSession; // Represents the current user's session

    /**
     * Constructs the UserInterface with its dependencies.
     *
     * @param pointOfRestSearchService The application service for search operations.
     * @param systemStatus The system status component to check functionality availability.
     * @param authenticationService The authentication service to check user authentication.
     */
    public UserInterface(PointOfRestSearchService pointOfRestSearchService,
                         SystemStatus systemStatus,
                         AuthenticationService authenticationService) {
        this.pointOfRestSearchService = pointOfRestSearchService;
        this.systemStatus = systemStatus;
        this.authenticationService = authenticationService;
        // For simulation, let's assume a dummy authenticated session
        this.currentUserSession = new Session("dummySessionId123", "user123");
    }

    /**
     * Simulates the user activating the search functionality.
     * This method includes the entry condition checks (R3, R4) from the sequence diagram.
     */
    public void activatesSearch() {
        System.out.println("\nUI: User activates search...");

        // Entry Condition R3: Check if search functionality is available
        if (!systemStatus.isSearchFunctionalityAvailable()) {
            displayErrorMessage("Search functionality is currently unavailable. Please try again later.");
            return;
        }
        System.out.println("UI: Search functionality is available.");

        // Entry Condition R4: Check if user is authenticated
        if (!authenticationService.isAuthenticated(currentUserSession)) {
            displayErrorMessage("User is not authenticated. Please log in to perform a search.");
            return;
        }
        System.out.println("UI: User is authenticated.");

        displaySearchForm();
    }

    /**
     * Displays the search form to the user.
     */
    public void displaySearchForm() {
        System.out.println("\nUI: Displaying search form:");
        System.out.println("---------------------------");
        System.out.println("Enter keyword (e.g., 'museum', 'park'): ");
        System.out.println("Enter type filter (e.g., 'historic', 'nature'): ");
        System.out.println("Enter max distance in km (e.g., '10'): ");
        System.out.println("---------------------------");
    }

    /**
     * Simulates getting search input from the user.
     * In a real application, this would parse UI elements.
     *
     * @return A SearchCriteria object based on simulated input.
     */
    public SearchCriteria getSearchInput() {
        // For this runnable example, we'll return a fixed criteria
        // In a real UI, this would involve reading from input fields.
        return new SearchCriteria("museum", "historic", 5);
    }

    /**
     * Simulates the user submitting the search form with specific criteria.
     *
     * @param criteria The search criteria submitted by the user.
     */
    public void submitSearchForm(SearchCriteria criteria) {
        System.out.println("\nUI: User submitted search form with criteria: " + criteria);
        try {
            // Delegate the search request to the application service
            List<PointOfRest> results = pointOfRestSearchService.processSearchRequest(criteria);
            displayResults(results);
        } catch (ApplicationServiceException e) {
            // Handle application service level exceptions by displaying an error message
            // Original: displayErrorMessage("Failed to perform search: " + e.getMessage() + ". Cause: " + e.getCause().getMessage());
            // Changed to handle null cause gracefully
            String causeMessage = (e.getCause() != null) ? e.getCause().getMessage() : "Unknown cause";
            displayErrorMessage("Failed to perform search: " + e.getMessage() + ". Cause: " + causeMessage);
        }
    }

    /**
     * Displays the search results to the user.
     *
     * @param results The list of PointOfRest objects to display.
     */
    public void displayResults(List<PointOfRest> results) {
        System.out.println("\nUI: Displaying search results:");
        if (results.isEmpty()) {
            System.out.println("No points of rest found matching your criteria.");
        } else {
            for (PointOfRest por : results) {
                System.out.println("- " + por);
            }
        }
        System.out.println("Operation completed.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\nUI Error: " + message);
    }

    /**
     * Main method to demonstrate the sequence diagram flow.
     * Initializes the components and simulates user interaction.
     */
    public static void main(String[] args) {
        // 1. Initialize Infrastructure Components
        EtourApiAdapter etourApiAdapter = new EtourApiAdapter();
        SystemStatus systemStatus = new SystemStatus();
        AuthenticationService authenticationService = new AuthenticationService();

        // 2. Initialize Repository Layer
        IPointOfRestRepository pointOfRestRepository = new EtourPointOfRestRepository(etourApiAdapter);

        // 3. Initialize Application Service Layer
        PointOfRestSearchService pointOfRestSearchService = new PointOfRestSearchService(pointOfRestRepository);

        // 4. Initialize Presentation Layer
        UserInterface userInterface = new UserInterface(pointOfRestSearchService, systemStatus, authenticationService);

        System.out.println("--- Scenario 1: Successful Search ---");
        // Ensure adapter is not simulating error for this scenario
        etourApiAdapter.setSimulateConnectionError(false);
        systemStatus.setSearchFunctionalityAvailable(true); // Ensure functionality is available
        // Simulate user interaction
        userInterface.activatesSearch(); // This will display the form after checks
        SearchCriteria criteria1 = new SearchCriteria("museum", "historical", 10);
        userInterface.submitSearchForm(criteria1);

        System.out.println("\n--- Scenario 2: Search with a different criteria ---");
        // Ensure adapter is not simulating error for this scenario
        etourApiAdapter.setSimulateConnectionError(false);
        userInterface.activatesSearch();
        SearchCriteria criteria2 = new SearchCriteria("park", "nature", 25);
        userInterface.submitSearchForm(criteria2);

        System.out.println("\n--- Scenario 3: Connection to ETOUR is interrupted (Error Path) ---");
        // Force adapter to simulate connection error for this scenario
        etourApiAdapter.setSimulateConnectionError(true);
        userInterface.activatesSearch();
        SearchCriteria criteria3 = new SearchCriteria("beach", "recreational", 50);
        userInterface.submitSearchForm(criteria3);
        etourApiAdapter.setSimulateConnectionError(false); // Reset for next scenarios if any

        System.out.println("\n--- Scenario 4: Search functionality unavailable (Entry Condition R3) ---");
        systemStatus.setSearchFunctionalityAvailable(false); // Make search unavailable
        userInterface.activatesSearch(); // This should display an error and stop
        systemStatus.setSearchFunctionalityAvailable(true); // Reset for next scenarios if any

        System.out.println("\n--- Scenario 5: User not authenticated (Entry Condition R4) ---");
        // Temporarily invalidate the session for this test
        userInterface.currentUserSession = new Session(null, null); // Simulate unauthenticated user
        userInterface.activatesSearch(); // This should display an error and stop
        // Restore authenticated session for completeness (though not strictly needed after main)
        userInterface.currentUserSession = new Session("dummySessionId123", "user123");
    }
}
