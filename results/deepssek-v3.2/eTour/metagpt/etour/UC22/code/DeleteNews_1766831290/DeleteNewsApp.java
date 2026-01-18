import java.util.Scanner;

/**
 * Main application class for the DeleteNews use case.
 * This class simulates an Agency Operator logging into the system
 * and following the workflow to delete a news item.
 * It handles the complete flow as specified in the use case.
 */
public class DeleteNewsApp {

    /**
     * Main entry point of the application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== News Management System ===\n");
        
        // Simulate agency login (Entry condition: The agency has logged)
        if (!simulateAgencyLogin()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        
        System.out.println("Login successful. Welcome Agency Operator!");
        
        // Initialize components
        DatabaseConnection dbConnection = new DatabaseConnection();
        NewsService newsService = new NewsService(dbConnection);
        Scanner mainScanner = new Scanner(System.in);
        
        try {
            // Connect to database
            System.out.println("\nEstablishing connection to ETOUR server...");
            try {
                dbConnection.connect();
            } catch (DatabaseConnection.ServerInterruptedException e) {
                System.err.println("Fatal error: " + e.getMessage());
                System.err.println("Cannot proceed without database connection. Exiting.");
                return;
            }
            
            boolean continueRunning = true;
            
            // Main application loop
            while (continueRunning) {
                displayMainMenu();
                System.out.print("Enter your choice: ");
                String choice = mainScanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        // Step 1: Activate the function of elimination of a news
                        activateDeleteNews(newsService);
                        break;
                    case "2":
                        // Additional feature: View all news (outside delete flow)
                        System.out.println("\n=== View All News ===");
                        newsService.listAllNews();
                        break;
                    case "3":
                        // Exit the application
                        System.out.println("\nThank you for using News Management System.");
                        continueRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
                
                if (continueRunning) {
                    System.out.print("\nPress Enter to continue...");
                    mainScanner.nextLine();
                }
            }
            
        } finally {
            // Cleanup resources
            newsService.close();
            dbConnection.disconnect();
            mainScanner.close();
            System.out.println("System shutdown complete.");
        }
    }

    /**
     * Simulates agency operator login.
     * In a real system, this would involve authentication against a user database.
     * 
     * @return true if login is successful, false otherwise
     */
    private static boolean simulateAgencyLogin() {
        Scanner loginScanner = new Scanner(System.in);
        System.out.println("=== Agency Login ===");
        System.out.print("Enter agency ID: ");
        String agencyId = loginScanner.nextLine().trim();
        
        System.out.print("Enter password: ");
        String password = loginScanner.nextLine().trim();
        
        // Simulate authentication - in a real system, this would validate against a database
        if (agencyId.isEmpty() || password.isEmpty()) {
            System.out.println("Agency ID and password cannot be empty.");
            return false;
        }
        
        // Simple validation for demonstration
        if (agencyId.equals("agency123") && password.equals("securepass")) {
            return true;
        } else {
            System.out.println("Invalid credentials. Login failed.");
            return false;
        }
    }

    /**
     * Displays the main menu of the application.
     */
    private static void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Delete News (Activate elimination function)");
        System.out.println("2. View All News");
        System.out.println("3. Exit System");
        System.out.println("==================");
    }

    /**
     * Activates the delete news function as per use case step 1.
     * This method orchestrates the entire delete news workflow.
     * 
     * @param newsService the news service instance to use for operations
     */
    private static void activateDeleteNews(NewsService newsService) {
        System.out.println("\n=== Activating Delete News Function ===");
        System.out.println("Step 1: Function activated successfully.");
        
        // This initiates the complete workflow through NewsService
        // The NewsService.handleDeleteNews() method will guide through:
        // 2. View all news in a form
        // 3. Select a news from the list and submit the form
        // 4. Asks for confirmation of the transaction
        // 5. Confirm the deletion of the news
        // 6. Delete the data news
        System.out.println("Initiating delete workflow...");
        
        boolean success = newsService.initiateDeleteNews();
        
        if (success) {
            System.out.println("\n=== Delete News Workflow Complete ===");
        } else {
            System.out.println("\n=== Delete News Workflow Terminated ===");
        }
    }
}