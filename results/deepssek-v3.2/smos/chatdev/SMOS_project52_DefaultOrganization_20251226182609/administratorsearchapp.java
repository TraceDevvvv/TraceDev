/**
 * AdministratorSearchApp.java
 * Main application class that simulates administrator entity search functionality.
 * This is a console-based implementation that includes all necessary classes
 * in a single file to ensure it's fully runnable.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AdministratorSearchApp {
    private EntityService entityService;
    private boolean isLoggedInAsAdmin;
    private boolean serverConnected;
    private Scanner scanner;
    public AdministratorSearchApp() {
        this.entityService = new EntityService();
        this.isLoggedInAsAdmin = false;
        this.serverConnected = true;
        this.scanner = new Scanner(System.in);
    }
    /**
     * Simulates administrator login process.
     * For demonstration, sets isLoggedInAsAdmin to true.
     */
    public void loginAsAdministrator() {
        System.out.println("Logging in as administrator...");
        // Simulate authentication (in real app, would check credentials)
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Simple authentication check (for demo purposes)
        if (username.equals("admin") && password.equals("admin123")) {
            isLoggedInAsAdmin = true;
            serverConnected = true;
            System.out.println("Login successful. Server connection established.\n");
        } else {
            System.out.println("Login failed. Invalid credentials.\n");
        }
    }
    /**
     * Performs entity search based on administrator input.
     * Only works if user is logged in as administrator.
     *
     * @param searchText the text to search for
     * @return list of entities matching the search
     */
    public List<Entity> performSearch(String searchText) {
        // Check preconditions: user must be logged in as administrator
        if (!isLoggedInAsAdmin) {
            System.out.println("Error: User is not logged in as administrator.");
            return new ArrayList<>();
        }
        // Check server connection status
        if (!serverConnected) {
            System.out.println("Error: SMOS server connection interrupted.");
            return new ArrayList<>();
        }
        System.out.println("Searching for: '" + searchText + "'\n");
        // Perform the search using EntityService
        List<Entity> results = entityService.searchEntities(searchText);
        // Display results in an active list format
        if (results.isEmpty()) {
            System.out.println("No entities found matching the search criteria.");
        } else {
            System.out.println("Found " + results.size() + " matching entities:");
            System.out.println("==========================================");
            for (Entity entity : results) {
                System.out.println(entity);
            }
            System.out.println("==========================================\n");
        }
        return results;
    }
    /**
     * Simulates user stopping the operation.
     * This interrupts the server connection as per postconditions.
     */
    public void stopOperation() {
        System.out.println("Stopping search operation...");
        serverConnected = false;
        System.out.println("SMOS server connection interrupted.");
        System.out.println("Operation stopped successfully.\n");
    }
    /**
     * Logs out the administrator and resets the session.
     */
    public void logout() {
        System.out.println("Logging out administrator...");
        isLoggedInAsAdmin = false;
        System.out.println("Logged out successfully.\n");
    }
    /**
     * Main method to demonstrate the entity search functionality.
     * This simulates the complete use case flow as specified:
     * 1. Administrator login (precondition)
     * 2. Searching entities with various keywords
     * 3. Stopping operation (postcondition: connection interrupted)
     */
    public static void main(String[] args) {
        AdministratorSearchApp app = new AdministratorSearchApp();
        Scanner mainScanner = new Scanner(System.in);
        // Step 1: Login as administrator (required precondition)
        System.out.println("=== Administrator Entity Search System ===");
        app.loginAsAdministrator();
        // Check if login was successful
        if (!app.isLoggedInAsAdmin) {
            System.out.println("Cannot proceed without administrator login.");
            mainScanner.close();
            return;
        }
        // Interactive search loop
        boolean running = true;
        while (running) {
            System.out.println("\n=== Entity Search Menu ===");
            System.out.println("1. Search entities");
            System.out.println("2. Stop operation (interrupts server)");
            System.out.println("3. Logout and exit");
            System.out.print("Enter choice (1-3): ");
            String choice = mainScanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter search keywords: ");
                    String searchText = mainScanner.nextLine();
                    app.performSearch(searchText);
                    break;
                case "2":
                    app.stopOperation();
                    running = false;
                    break;
                case "3":
                    app.stopOperation();
                    app.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        // Clean up
        app.scanner.close();
        mainScanner.close();
        System.out.println("\nEntity search demonstration completed.");
    }
}