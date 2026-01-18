import java.util.List;
import java.util.Scanner;

/**
 * ViewRefreshmentPointCard.java
 * Main class implementing the ViewRefreshmentPointCard use case.
 * 
 * This program simulates an agency operator logged into the system
 * who wants to view details of a selected refreshment point.
 * 
 * Use Case Flow:
 * 1. Operator is already logged in (simulated)
 * 2. View list of refreshment points from previous search (SearchRefreshmentPoint use case)
 * 3. Select a refreshment point from the list
 * 4. View detailed card of the selected refreshment point
 * 5. Handle server connection interruptions gracefully
 * 
 * The program is a console-based Java application that demonstrates
 * the complete use case with error handling and user interaction.
 */
public class ViewRefreshmentPointCard {
    
    /**
     * Main method - entry point of the application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  ETOUR Refreshment Point Management System");
        System.out.println("==============================================");
        System.out.println();
        
        // Simulate agency operator login (entry condition)
        System.out.println("Simulating agency operator login...");
        boolean loggedIn = simulateLogin();
        
        if (!loggedIn) {
            System.out.println("Error: Login failed. Exiting system.");
            return;
        }
        
        System.out.println("Login successful. Welcome, Agency Operator!");
        System.out.println();
        
        // Initialize service and scanner for user input
        RefreshmentPointService service = new RefreshmentPointService();
        Scanner scanner = new Scanner(System.in);
        
        boolean exitProgram = false;
        
        while (!exitProgram) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            
            try {
                int choice = getSafeIntInput(scanner);
                System.out.println();
                
                switch (choice) {
                    case 1:
                        // Use Case Flow: View list of points from search
                        viewRefreshmentPointList(service, scanner);
                        break;
                    case 2:
                        // Directly enter ID to view details
                        viewRefreshmentPointById(service, scanner);
                        break;
                    case 3:
                        // Exit program
                        exitProgram = true;
                        System.out.println("Thank you for using ETOUR system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
                scanner.nextLine(); // Clear input buffer
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    /**
     * Simulates agency operator login.
     * In a real system, this would involve authentication against a database.
     * 
     * @return true if login successful, false otherwise
     */
    private static boolean simulateLogin() {
        // Simulate login delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        // For simulation purposes, always return true
        // In a real application, this would validate credentials
        return true;
    }
    
    /**
     * Displays the main menu options to the user.
     */
    private static void displayMainMenu() {
        System.out.println("=== MAIN MENU ===");
        System.out.println("1. View refreshment point list (from SearchRefreshmentPoint)");
        System.out.println("2. View refreshment point details by ID");
        System.out.println("3. Exit");
        System.out.println();
    }
    
    /**
     * Implements the main flow of the ViewRefreshmentPointCard use case:
     * 1. View list of refreshment points (from search)
     * 2. Select a refreshment point from the list
     * 3. View detailed card of selected point
     * 
     * @param service the RefreshmentPointService instance
     * @param scanner the Scanner for user input
     */
    private static void viewRefreshmentPointList(RefreshmentPointService service, Scanner scanner) {
        System.out.println("=== VIEW REFRESHMENT POINT LIST ===");
        System.out.println("Loading search results from SearchRefreshmentPoint use case...");
        System.out.println();
        
        try {
            // Step 1: Get list of refreshment points (simulating search results)
            List<RefreshmentPoint> points = service.searchRefreshmentPoints();
            
            if (points.isEmpty()) {
                System.out.println("No refreshment points found in the search results.");
                return;
            }
            
            // Display the list of refreshment points
            System.out.println("Search Results - " + points.size() + " refreshment point(s) found:");
            System.out.println("--------------------------------------------------");
            
            for (RefreshmentPoint point : points) {
                System.out.println(point.toString());
            }
            
            System.out.println("--------------------------------------------------");
            System.out.println();
            
            // Step 2: Prompt user to select a refreshment point
            System.out.print("Enter the ID of the refreshment point to view details (or 0 to go back): ");
            int selectedId = getSafeIntInput(scanner);
            
            if (selectedId == 0) {
                System.out.println("Returning to main menu.");
                return;
            }
            
            // Step 3: View detailed card of selected refreshment point
            viewRefreshmentPointCard(service, selectedId);
            
        } catch (RefreshmentPointService.ServerConnectionException e) {
            // Handle server connection interruption as specified in the use case
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Please check your internet connection and try again.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Allows user to directly enter a refreshment point ID to view details.
     * This is an alternative flow not specified in the use case but helpful for testing.
     * 
     * @param service the RefreshmentPointService instance
     * @param scanner the Scanner for user input
     */
    private static void viewRefreshmentPointById(RefreshmentPointService service, Scanner scanner) {
        System.out.println("=== VIEW REFRESHMENT POINT BY ID ===");
        System.out.print("Enter the refreshment point ID: ");
        
        try {
            int id = getSafeIntInput(scanner);
            viewRefreshmentPointCard(service, id);
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter a valid number.");
        }
    }
    
    /**
     * Displays the detailed card of a refreshment point by ID.
     * This implements the core functionality of the use case.
     * 
     * @param service the RefreshmentPointService instance
     * @param id the ID of the refreshment point to display
     */
    private static void viewRefreshmentPointCard(RefreshmentPointService service, int id) {
        System.out.println();
        System.out.println("Loading refreshment point details for ID: " + id + "...");
        
        try {
            // Step in use case: "Upload data to a selected restaurant"
            RefreshmentPoint point = service.getRefreshmentPointById(id);
            
            // Step in use case: "The system displays the details of the selected point of rest"
            System.out.println();
            System.out.println(point.toDetailedString());
            System.out.println();
            
            // Success message
            System.out.println("Refreshment point card displayed successfully!");
            
        } catch (RefreshmentPointService.ServerConnectionException e) {
            // Handle server connection interruption as specified in the use case
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("This is the 'Interruption of the connection to the server ETOUR' scenario.");
            System.out.println("Please try again when the connection is restored.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Please check the ID and try again.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
    
    /**
     * Safely gets integer input from the user, handling invalid input.
     * 
     * @param scanner the Scanner for user input
     * @return the integer value entered by the user
     */
    private static int getSafeIntInput(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}