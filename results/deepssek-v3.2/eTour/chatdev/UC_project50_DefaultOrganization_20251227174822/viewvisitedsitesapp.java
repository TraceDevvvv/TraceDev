import java.util.List;
import java.util.Scanner;
/**
 * Main application class that implements the ViewVisitedSites use case.
 * Simulates tourist authentication and interaction to display visited sites with feedback.
 */
public class ViewVisitedSitesApp {
    private static boolean isAuthenticated = false;
    private static SiteService siteService = new SiteService();
    private static Scanner scanner = new Scanner(System.in);
    /**
     * Simulates tourist authentication. For demonstration, accepts any non-empty username and password.
     */
    private static void authenticateTourist() {
        System.out.println("=== Tourist Authentication ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Simple authentication simulation
        if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
            isAuthenticated = true;
            System.out.println("Authentication successful!\n");
        } else {
            System.out.println("Authentication failed. Username and password cannot be empty.\n");
        }
    }
    /**
     * Displays the list of visited sites with feedback.
     * Handles server interruption gracefully.
     */
    private static void displayVisitedSites() {
        System.out.println("Fetching your visited sites with feedback...");
        try {
            List<Site> sites = siteService.fetchVisitedSitesForTourist();
            if (sites.isEmpty()) {
                System.out.println("No visited sites with feedback found.");
            } else {
                System.out.println("=== Your Visited Sites with Feedback ===");
                for (Site site : sites) {
                    System.out.println(site);
                }
                System.out.println("=======================================");
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please try again later.\n");
        }
    }
    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the ETOUR System - View Visited Sites Feature\n");
        // Step 1: Authentication (Entry condition)
        authenticateTourist();
        if (isAuthenticated) {
            // Step 2: Feature selection and display
            boolean exit = false;
            while (!exit) {
                System.out.println("Options:");
                System.out.println("1. View visited sites with feedback");
                System.out.println("2. Exit");
                System.out.print("Select an option: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        displayVisitedSites();
                        break;
                    case "2":
                        exit = true;
                        System.out.println("Exiting. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.\n");
                }
            }
        } else {
            System.out.println("Authentication required to access this feature.");
        }
        scanner.close();
    }
}