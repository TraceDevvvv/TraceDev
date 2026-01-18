import java.util.Scanner;

/**
 * Console User Interface class for the Delete Menu application.
 * Provides methods for displaying UI elements, getting user input,
 * and showing messages.
 */
public class ConsoleUI {
    // Days of the week for display
    private static final String[] DAYS_OF_WEEK = {
        "Monday",
        "Tuesday", 
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    };

    /**
     * Displays the seven days of the week as a form for selection.
     * Each day is numbered starting from 1.
     */
    public void displayDaysOfWeek() {
        System.out.println("\n=== Select a day to delete menu ===");
        System.out.println("Please choose a day from the list below:");
        
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            System.out.println((i + 1) + ". " + DAYS_OF_WEEK[i]);
        }
        
        System.out.println("0. Cancel operation");
        System.out.print("\nEnter your choice (1-7, or 0 to cancel): ");
    }

    /**
     * Gets the user's day selection from console input.
     * Validates input to ensure it's within the valid range (0-7).
     * 
     * @param scanner Scanner object for reading user input
     * @return the selected day number (1-7) or 0 for cancellation
     * @throws OperationCancelledException if user enters 0 (cancels)
     */
    public int getDaySelection(Scanner scanner) throws OperationCancelledException {
        int selection = -1;
        
        while (selection < 0 || selection > 7) {
            try {
                String input = scanner.nextLine().trim();
                selection = Integer.parseInt(input);
                
                if (selection == 0) {
                    throw new OperationCancelledException("Day selection cancelled.");
                }
                
                if (selection < 1 || selection > 7) {
                    System.out.print("Invalid selection. Please enter a number between 1 and 7 (or 0 to cancel): ");
                    selection = -1; // Reset to continue loop
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number between 1 and 7 (or 0 to cancel): ");
            }
        }
        
        return selection;
    }

    /**
     * Shows confirmation dialog for menu deletion.
     * Asks user to confirm before proceeding with deletion.
     * 
     * @param scanner Scanner object for reading user input
     * @param daySelection the selected day number (1-7)
     * @return true if user confirms deletion, false if user cancels
     */
    public boolean confirmDeletion(Scanner scanner, int daySelection) {
        System.out.println("\n=== Confirm Deletion ===");
        System.out.println("You have selected to delete the menu for: " + DAYS_OF_WEEK[daySelection - 1]);
        System.out.println("This action cannot be undone.");
        System.out.print("Are you sure you want to delete this daily menu? (yes/no): ");
        
        String response = scanner.nextLine().trim().toLowerCase();
        
        while (!response.equals("yes") && !response.equals("no")) {
            System.out.print("Please enter 'yes' to confirm or 'no' to cancel: ");
            response = scanner.nextLine().trim().toLowerCase();
        }
        
        return response.equals("yes");
    }

    /**
     * Displays success message after successful deletion.
     * 
     * @param daySelection the selected day number (1-7) that was deleted
     */
    public void showSuccessMessage(int daySelection) {
        System.out.println("\n=== Success! ===");
        System.out.println("The daily menu for " + DAYS_OF_WEEK[daySelection - 1] + 
                          " has been successfully deleted.");
        System.out.println("You can now close this application or perform another operation.");
    }

    /**
     * Displays an error message for invalid day selection.
     * 
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.out.println("\n=== Error ===");
        System.out.println(message);
    }
}