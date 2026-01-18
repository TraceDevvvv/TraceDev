import java.util.Scanner;

/**
 * Main application class for deleting a daily menu.
 * This class orchestrates the flow: enable delete functionality,
 * display days of week, get selection, confirm, delete, show success.
 * Handles exceptions for cancellation and server interruption.
 */
public class DeleteMenuApplication {
    private ConsoleUI consoleUI;
    private MenuService menuService;
    private Scanner scanner;

    /**
     * Constructor initializes dependencies.
     */
    public DeleteMenuApplication() {
        this.consoleUI = new ConsoleUI();
        this.menuService = new MenuService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main entry point of the application.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        DeleteMenuApplication app = new DeleteMenuApplication();
        app.run();
    }

    /**
     * Runs the main application flow.
     * Handles the complete process from authentication to deletion.
     */
    public void run() {
        try {
            // Entry condition: Operator has successfully authenticated
            // In a real system, authentication would happen here
            System.out.println("=== Restaurant Point of Operator System ===");
            System.out.println("Operator authenticated successfully.");
            
            // Step 1: Enable the functionality of deleting a menu
            enableDeleteFunctionality();
            
            // Step 2: Display form containing the seven days of the week
            consoleUI.displayDaysOfWeek();
            
            // Step 3: Select a day of the week and submit the form
            int daySelection = consoleUI.getDaySelection(scanner);
            
            // Step 4: Call to confirm the deletion
            boolean confirmed = consoleUI.confirmDeletion(scanner, daySelection);
            
            if (!confirmed) {
                // Operator cancels the operation
                throw new OperationCancelledException("Operation cancelled by operator.");
            }
            
            // Step 5: Confirm the operation (already done in step 4)
            // Step 6: Delete the daily menu selection
            menuService.deleteDailyMenu(daySelection);
            
            // Exit condition: Notify successful elimination
            consoleUI.showSuccessMessage(daySelection);
            
        } catch (OperationCancelledException e) {
            // Handle operator cancellation
            System.out.println("Operation cancelled: " + e.getMessage());
            System.out.println("Returning to main menu...");
        } catch (ServerConnectionException e) {
            // Handle server connection interruption
            System.out.println("Server connection error: " + e.getMessage());
            System.out.println("Please check your connection and try again.");
        } catch (Exception e) {
            // Handle any other unexpected errors
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println("Please contact system administrator.");
        } finally {
            // Clean up resources
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Enables the delete functionality.
     * In a real system, this might check permissions or set up necessary state.
     */
    private void enableDeleteFunctionality() {
        System.out.println("\nDelete menu functionality enabled.");
        System.out.println("You can now delete a daily menu for any day of the week.");
    }
}