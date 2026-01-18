import java.util.List;
import java.util.Scanner;

/**
 * Service class for news operations.
 * This class handles the business logic for listing news, deleting news,
 * and managing the confirmation flow as per the use case.
 */
public class NewsService {
    private DatabaseConnection dbConnection;
    private Scanner scanner;
    
    /**
     * Constructor initializes the service with a database connection.
     * 
     * @param dbConnection the database connection to use
     */
    public NewsService(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Lists all news in the system.
     * This corresponds to step 2 of the use case: "View all news in a form".
     * 
     * @return true if operation was successful, false otherwise
     */
    public boolean listAllNews() {
        try {
            System.out.println("\n=== List of All News ===");
            List<News> newsList = dbConnection.getAllNews();
            
            if (newsList.isEmpty()) {
                System.out.println("No news found in the system.");
                return false;
            }
            
            // Display each news item with its details
            for (News news : newsList) {
                System.out.println(news.toString());
            }
            System.out.println("=======================\n");
            return true;
        } catch (DatabaseConnection.ServerInterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Operation interrupted due to server connection issue.");
            return false;
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Initiates the delete news process as per the use case flow.
     * This method guides the user through the entire deletion process.
     * 
     * @return true if news was successfully deleted, false otherwise
     */
    public boolean initiateDeleteNews() {
        System.out.println("\n=== Delete News Function ===");
        System.out.println("Step 1: Activating delete function...");
        
        // Step 2: View all news
        System.out.println("\nStep 2: Viewing all news...");
        boolean hasNews = listAllNews();
        if (!hasNews) {
            System.out.println("Cannot proceed with deletion as there are no news items.");
            return false;
        }
        
        // Step 3: Select a news from the list
        System.out.println("Step 3: Select a news item to delete.");
        System.out.print("Enter the ID of the news you want to delete (or 0 to cancel): ");
        
        int selectedId;
        try {
            selectedId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return false;
        }
        
        // Handle cancellation (Exit condition: Operator cancels the operation)
        if (selectedId == 0) {
            System.out.println("Operation cancelled by operator.");
            return false;
        }
        
        // Validate that the news exists
        try {
            if (!dbConnection.newsExists(selectedId)) {
                System.out.println("Error: News with ID " + selectedId + " does not exist.");
                return false;
            }
        } catch (DatabaseConnection.ServerInterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Operation interrupted due to server connection issue.");
            return false;
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
        
        // Step 4: Ask for confirmation
        System.out.println("\nStep 4: Confirmation required.");
        System.out.println("You are about to delete news item with ID: " + selectedId);
        System.out.print("Are you sure you want to proceed? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        // Step 5: Confirm the deletion
        System.out.println("\nStep 5: Processing confirmation...");
        if (!confirmation.equals("yes")) {
            System.out.println("Deletion cancelled. News item was not deleted.");
            return false;
        }
        
        // Step 6: Delete the data news
        System.out.println("\nStep 6: Deleting news data...");
        try {
            boolean deleted = dbConnection.deleteNews(selectedId);
            if (deleted) {
                System.out.println("Success: News item with ID " + selectedId + " has been deleted.");
                System.out.println("Exit condition: System notifies successful elimination of the news.");
                return true;
            } else {
                System.out.println("Error: Failed to delete news item with ID " + selectedId);
                return false;
            }
        } catch (DatabaseConnection.ServerInterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Exit condition: Interruption of the connection to the server ETOUR.");
            return false;
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Closes the scanner resource.
     * Should be called when the service is no longer needed.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}