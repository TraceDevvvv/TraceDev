import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a daily menu for a restaurant.
 * In a real application, this would contain more details like dishes, pr, etc.
 */
class DailyMenu {
    private String dayOfWeek;
    private String menuDetails;

    /**
     * Constructs a DailyMenu object.
     *
     * @param dayOfWeek The day of the week this menu is for.
     * @param menuDetails A description of the menu for the day.
     */
    public DailyMenu(String dayOfWeek, String menuDetails) {
        this.dayOfWeek = dayOfWeek;
        this.menuDetails = menuDetails;
    }

    /**
     * Gets the day of the week for this menu.
     *
     * @return The day of the week.
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Gets the details of the menu.
     *
     * @return The menu details.
     */
    public String getMenuDetails() {
        return menuDetails;
    }

    @Override
    public String toString() {
        return "Day: " + dayOfWeek + ", Menu: " + menuDetails;
    }
}

/**
 * Manages the restaurant's daily menus.
 * This class acts as a data store for menus.
 */
class MenuManager {
    // A map to store daily menus, with the day of the week as the key.
    private Map<String, DailyMenu> dailyMenus;

    /**
     * Constructs a MenuManager and initializes it with some sample menus.
     */
    public MenuManager() {
        dailyMenus = new HashMap<>();
        // Initialize with some dummy data for demonstration
        dailyMenus.put("MONDAY", new DailyMenu("MONDAY", "Pasta Day"));
        dailyMenus.put("TUESDAY", new DailyMenu("TUESDAY", "Taco Tuesday"));
        dailyMenus.put("WEDNESDAY", new DailyMenu("WEDNESDAY", "Burger Bonanza"));
        dailyMenus.put("THURSDAY", new DailyMenu("THURSDAY", "Curry Night"));
        dailyMenus.put("FRIDAY", new DailyMenu("FRIDAY", "Pizza Party"));
        dailyMenus.put("SATURDAY", new DailyMenu("SATURDAY", "Steak Special"));
        dailyMenus.put("SUNDAY", new DailyMenu("SUNDAY", "Roast Dinner"));
    }

    /**
     * Retrieves a daily menu by its day of the week.
     *
     * @param dayOfWeek The day of the week (e.g., "MONDAY").
     * @return The DailyMenu object if found, otherwise null.
     */
    public DailyMenu getMenu(String dayOfWeek) {
        return dailyMenus.get(dayOfWeek.toUpperCase());
    }

    /**
     * Deletes a daily menu for a given day of the week.
     *
     * @param dayOfWeek The day of the week for which to delete the menu.
     * @return true if the menu was successfully deleted, false if it didn't exist.
     */
    public boolean deleteMenu(String dayOfWeek) {
        // Remove the menu and return true if a menu was present for the key, false otherwise.
        return dailyMenus.remove(dayOfWeek.toUpperCase()) != null;
    }

    /**
     * Displays all current daily menus.
     */
    public void displayAllMenus() {
        if (dailyMenus.isEmpty()) {
            System.out.println("No daily menus currently set.");
            return;
        }
        System.out.println("\n--- Current Daily Menus ---");
        String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        for (String day : days) {
            DailyMenu menu = dailyMenus.get(day);
            if (menu != null) {
                System.out.println(menu);
            } else {
                System.out.println("Day: " + day + ", Menu: Not set");
            }
        }
        System.out.println("---------------------------\n");
    }
}

/**
 * Represents the authenticated operator of the restaurant.
 * In a real system, this would involve user authentication logic.
 * For this use case, we assume authentication is already successful.
 */
class RestaurantOperator {
    private String username;
    private boolean isAuthenticated;

    /**
     * Constructs a RestaurantOperator.
     *
     * @param username The username of the operator.
     */
    public RestaurantOperator(String username) {
        this.username = username;
        // For this use case, we assume the operator is already authenticated.
        this.isAuthenticated = true;
    }

    /**
     * Checks if the operator is authenticated.
     *
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Gets the username of the operator.
     *
     * @return The operator's username.
     */
    public String getUsername() {
        return username;
    }
}

/**
 * The main application class that orchestrates the DeleteMenu use case.
 * It handles user interaction and calls the MenuManager for data operations.
 */
public class DeleteMenu {
    private MenuManager menuManager;
    private Scanner scanner;
    private RestaurantOperator currentOperator;

    /**
     * Constructs the DeleteMenu application.
     *
     * @param operator The authenticated restaurant operator.
     */
    public DeleteMenu(RestaurantOperator operator) {
        this.menuManager = new MenuManager();
        this.scanner = new Scanner(System.in);
        this.currentOperator = operator;
    }

    /**
     * Displays the form containing the seven days of the week.
     * Allows the operator to select a day.
     *
     * @return The selected day of the week as a String, or null if the operation is cancelled.
     */
    private String displayDaySelectionForm() {
        System.out.println("--- Delete Daily Menu ---");
        System.out.println("Please select a day of the week to delete its menu:");
        String[] days = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        for (int i = 0; i < days.length; i++) {
            System.out.println((i + 1) + ". " + days[i]);
        }
        System.out.println("0. Cancel operation");

        int choice = -1;
        while (true) {
            System.out.print("Enter your choice (0-7): ");
            try {
                choice = scanner.nextInt();
                if (choice >= 0 && choice <= days.length) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 0 and " + days.length + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the remaining newline character

        if (choice == 0) {
            return null; // Operator cancelled
        } else {
            return days[choice - 1];
        }
    }

    /**
     * Confirms the deletion operation with the operator.
     *
     * @param dayToDelete The day of the week whose menu is to be deleted.
     * @return true if the operator confirms, false otherwise.
     */
    private boolean confirmDeletion(String dayToDelete) {
        System.out.println("\nYou have selected to delete the menu for " + dayToDelete + ".");
        DailyMenu menu = menuManager.getMenu(dayToDelete);
        if (menu != null) {
            System.out.println("Current menu for " + dayToDelete + ": " + menu.getMenuDetails());
        } else {
            System.out.println("There is no menu currently set for " + dayToDelete + ".");
        }
        System.out.print("Are you sure you want to delete this daily menu? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        return confirmation.equals("yes");
    }

    /**
     * Executes the DeleteMenu use case.
     * This method orchestrates the flow of events as described in the use case.
     */
    public void execute() {
        // Entry condition: The Point Of Restaurant Operator has successfully authenticated to the system.
        if (!currentOperator.isAuthenticated()) {
            System.out.println("Error: Operator not authenticated. Cannot proceed with menu deletion.");
            return;
        }
        System.out.println("Operator '" + currentOperator.getUsername() + "' is authenticated. Enabling menu deletion functionality.");

        // 1. Enable the functionality of deleting a menu. (Implicitly enabled by calling this method)
        // Display current menus for context
        menuManager.displayAllMenus();

        // 2. Displays a form containing the seven days of the week.
        // 3. Select a day of the week and submit the form.
        String selectedDay = displayDaySelectionForm();

        // Check if the operator cancelled the operation
        if (selectedDay == null) {
            System.out.println("Operation cancelled by operator.");
            return; // Exit condition: Restaurant Point Of Operator cancels the operation.
        }

        // 4. Calls to confirm the deletion.
        // 5. Confirm the operation.
        boolean confirmed = confirmDeletion(selectedDay);

        if (confirmed) {
            // 6. Delete the daily menu selection.
            try {
                // Simulate potential server connection interruption (ETOUR)
                // In a real scenario, this would be handled by network communication layers.
                // For this simulation, we'll just proceed as if connection is stable.
                // If a real network call failed, an exception would be caught here.

                boolean deleted = menuManager.deleteMenu(selectedDay);

                if (deleted) {
                    // Exit condition: The system shall notify the successful elimination of the daily menu selection.
                    System.out.println("\nSuccess: Daily menu for " + selectedDay + " has been successfully deleted.");
                } else {
                    System.out.println("\nInfo: No menu found for " + selectedDay + " to delete.");
                }
            } catch (Exception e) {
                // Exit condition: Interruption of the connection to the server ETOUR.
                System.err.println("\nError: An unexpected error occurred during deletion (simulated ETOUR/server interruption). Please try again.");
                // In a real application, more specific exception handling for network issues would be here.
            }
        } else {
            System.out.println("\nDeletion operation for " + selectedDay + " cancelled by operator.");
            // Exit condition: Restaurant Point Of Operator cancels the operation.
        }

        // Display menus after potential deletion
        menuManager.displayAllMenus();
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Simulate successful authentication
        RestaurantOperator operator = new RestaurantOperator("admin_chef");

        // Create and run the DeleteMenu application
        DeleteMenu app = new DeleteMenu(operator);
        app.execute();

        // Close the scanner when the application finishes
        app.scanner.close();
    }
}