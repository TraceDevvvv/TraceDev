import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

/**
 * Main application class to simulate the use case flow defined in the sequence diagram.
 * This class acts as the "Agency Operator" and orchestrates the system components.
 */
public class Main {

    public static void main(String[] args) {
        // --- System Initialization ---
        // Instantiate all core components and inject dependencies
        Database database = new Database();
        ITouristAccountRepository touristAccountRepository = new TouristAccountRepository(database);
        TouristManager touristManager = new TouristManager(touristAccountRepository);
        TouristSearchService touristSearchService = new TouristSearchService(touristManager);
        TouristSearchView touristSearchView = new TouristSearchView(); // View needs controller reference
        TouristSearchController touristSearchController = new TouristSearchController(touristSearchService, touristSearchView);
        touristSearchView.setTouristSearchController(touristSearchController); // Inject controller into view

        AuthenticationService authService = new AuthenticationService();
        Scanner mainScanner = new Scanner(System.in);

        System.out.println("--- Tourist Account Search Application ---");

        // --- Precondition: Agency Operator is logged in. ---
        System.out.println("\nSimulating login for Agency Operator...");
        authService.login(new Credentials("agency_operator", "password123"));
        if (!authService.isLoggedIn()) {
            System.err.println("Login failed. Cannot proceed with search. Exiting.");
            return;
        }

        // --- Sequence Diagram Flow Simulation ---

        // Scenario 1: Successful Search
        System.out.println("\n--- Scenario 1: Successful Search ---");
        database.setSimulateConnectionError(false); // Ensure no database error simulation
        runSearchScenario(touristSearchView, mainScanner);


        // Scenario 2: Search with Database Connection Interruption
        System.out.println("\n--- Scenario 2: Search with Simulated Database Connection Interruption ---");
        database.setSimulateConnectionError(true); // Simulate database error
        runSearchScenario(touristSearchView, mainScanner);

        // Scenario 3: Another Successful Search (after potential error)
        System.out.println("\n--- Scenario 3: Another Successful Search (after connection restored) ---");
        database.setSimulateConnectionError(false); // Restore database connection
        runSearchScenario(touristSearchView, mainScanner);


        System.out.println("\n--- Application End ---");
        authService.logout();
        mainScanner.close();
    }

    /**
     * Helper method to encapsulate the search scenario flow for reusability.
     * @param view The TouristSearchView instance.
     * @param scanner The Scanner for user input simulation.
     */
    private static void runSearchScenario(TouristSearchView view, Scanner scanner) {
        // AO -> View: requestSearchForm()
        // View --> AO: displaySearchForm()
        view.displaySearchForm();

        // Simulate user input for search criteria from AO
        Map<String, String> searchData = new HashMap<>();
        System.out.print("Simulated User Input (Name): ");
        searchData.put("name", "John"); // Example input
        System.out.println(searchData.get("name")); // Echo input for clarity
        
        System.out.print("Simulated User Input (Nationality): ");
        searchData.put("nationality", ""); // Example input
        System.out.println("''"); // Echo input for clarity

        System.out.print("Simulated User Input (Min Booking Count): ");
        searchData.put("minBookingCount", "1"); // Example input
        System.out.println(searchData.get("minBookingCount")); // Echo input for clarity


        // AO -> View: submitSearchCriteria(searchData)
        // This call will trigger the entire sequence from View through Controller, Service, Manager, Repository, and Database.
        System.out.println("\nAO -> View: submitSearchCriteria (simulated from Main)");
        view.submitSearchCriteria(searchData);

        System.out.println("\n----- End of Search Scenario -----\n");
        // Clear remaining newline character from previous scanner.nextLine() if any
        // scanner.nextLine(); // Consume newline if getSearchCriteria used scanner directly and didn't consume.
    }
}