/**
 * Main class demonstrating the ModifySearchPreference use case.
 * Provides a console-based user interface that simulates the complete flow:
 * user authentication, displaying current preferences, editing preferences,
 * confirmation, and handling edge cases (cancellation, server interruption).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== eTour System - Modify Search Preferences ===");
        System.out.println("This program demonstrates the ModifySearchPreference use case.");
        
        // Create an authenticated tourist (entry condition: successfully authenticated)
        Tourist tourist = createAndAuthenticateTourist();
        
        if (tourist.isAuthenticated()) {
            // Create the service for modifying preferences
            ModifySearchPreferenceService service = new ModifySearchPreferenceService(tourist);
            
            try {
                // Main menu loop
                boolean continueRunning = true;
                while (continueRunning) {
                    displayMainMenu();
                    int choice = getUserChoice();
                    
                    switch (choice) {
                        case 1:
                            // Execute the modify preference flow
                            boolean success = service.executeModifyPreferenceFlow();
                            if (success) {
                                System.out.println("\nUse case completed successfully!");
                            } else {
                                System.out.println("\nUse case did not complete successfully.");
                            }
                            break;
                            
                        case 2:
                            // View current preferences
                            viewCurrentPreferences(tourist);
                            break;
                            
                        case 3:
                            // Simulate different exit conditions
                            demonstrateExitConditions(service);
                            break;
                            
                        case 4:
                            // Exit the program
                            System.out.println("Thank you for using eTour System. Goodbye!");
                            continueRunning = false;
                            break;
                            
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    
                    if (continueRunning) {
                        System.out.println("\nPress Enter to continue...");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                            // Ignore exception
                        }
                    }
                }
            } finally {
                // Clean up resources
                service.close();
            }
        } else {
            System.out.println("Authentication failed. Cannot proceed with modifying search preferences.");
        }
    }
    
    /**
     * Creates and authenticates a tourist for the demonstration.
     * @return an authenticated Tourist object
     */
    private static Tourist createAndAuthenticateTourist() {
        System.out.println("\n=== User Authentication ===");
        System.out.println("Entry condition: Tourist must be successfully authenticated.");
        
        // Create a tourist (initially not authenticated)
        Tourist tourist = new Tourist("T001", "JohnDoe", false);
        
        // Simulate authentication process
        System.out.println("Authenticating user: " + tourist.getUsername());
        
        // In a real system, this would involve password validation
        // For demonstration, we'll simulate successful authentication
        boolean authSuccess = tourist.authenticate("securePassword123");
        
        if (authSuccess) {
            System.out.println("Authentication successful! User is now authenticated.");
            
            // Set some initial search preferences for the tourist
            SearchPreferences initialPrefs = new SearchPreferences(
                "Tokyo", 
                2000.0, 
                5000.0, 
                "2024-09-15", 
                "2024-09-25", 
                "Hotel", 
                2, 
                true, 
                true, 
                "Luxury"
            );
            tourist.setSearchPreferences(initialPrefs);
            System.out.println("Initial search preferences have been set.");
        } else {
            System.out.println("Authentication failed.");
        }
        
        return tourist;
    }
    
    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Modify Search Preferences");
        System.out.println("2. View Current Preferences");
        System.out.println("3. Demonstrate Exit Conditions");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    /**
     * Gets user choice from the menu.
     * @return the user's choice as an integer
     */
    private static int getUserChoice() {
        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }
    
    /**
     * Displays the current preferences of the tourist.
     * @param tourist the Tourist object whose preferences to display
     */
    private static void viewCurrentPreferences(Tourist tourist) {
        System.out.println("\n=== Current Search Preferences ===");
        if (tourist.hasSearchPreferences()) {
            SearchPreferences prefs = tourist.getSearchPreferences();
            System.out.println("User: " + tourist.getUsername());
            System.out.println("Preferences Summary:");
            System.out.println(prefs.getSummary());
            System.out.println("\nDetailed View:");
            System.out.println("Destination: " + prefs.getDestination());
            System.out.println("Budget Range: $" + prefs.getMinBudget() + " - $" + prefs.getMaxBudget());
            System.out.println("Travel Dates: " + prefs.getStartDate() + " to " + prefs.getEndDate());
            System.out.println("Accommodation: " + prefs.getAccommodationType());
            System.out.println("Travelers: " + prefs.getNumberOfTravelers());
            System.out.println("Flights Included: " + (prefs.isIncludeFlights() ? "Yes" : "No"));
            System.out.println("Meals Included: " + (prefs.isIncludeMeals() ? "Yes" : "No"));
            System.out.println("Travel Style: " + prefs.getTravelStyle());
            
            // Validate the preferences
            if (prefs.validate()) {
                System.out.println("\n✓ Preferences are valid.");
            } else {
                System.out.println("\n⚠ Preferences have validation issues.");
            }
        } else {
            System.out.println("No search preferences found for this user.");
        }
    }
    
    /**
     * Demonstrates the different exit conditions from the use case.
     * @param service the ModifySearchPreferenceService to use for demonstration
     */
    private static void demonstrateExitConditions(ModifySearchPreferenceService service) {
        System.out.println("\n=== Demonstrating Exit Conditions ===");
        System.out.println("The use case specifies three exit conditions:");
        System.out.println("1. Successful modification notification");
        System.out.println("2. User cancellation");
        System.out.println("3. Server interruption");
        
        System.out.println("\nLet's demonstrate each condition:");
        
        // Create a simple menu for exit condition demonstration
        System.out.println("\nSelect an exit condition to demonstrate:");
        System.out.println("1. Successful modification");
        System.out.println("2. User cancellation");
        System.out.println("3. Server interruption simulation");
        System.out.print("Enter choice (1-3): ");
        
        int choice = getUserChoice();
        
        switch (choice) {
            case 1:
                demonstrateSuccessfulModification(service);
                break;
            case 2:
                demonstrateUserCancellation();
                break;
            case 3:
                demonstrateServerInterruption();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    /**
     * Demonstrates successful modification exit condition.
     * @param service the ModifySearchPreferenceService to use
     */
    private static void demonstrateSuccessfulModification(ModifySearchPreferenceService service) {
        System.out.println("\n--- Demonstrating Successful Modification ---");
        System.out.println("This shows the normal flow where preferences are successfully modified.");
        
        // We need to temporarily modify the service to ensure success
        // For demonstration, we'll just show what happens
        System.out.println("\nWhen the user completes all steps:");
        System.out.println("1. User accesses modification functionality");
        System.out.println("2. Current preferences are displayed");
        System.out.println("3. User edits preferences");
        System.out.println("4. System asks for confirmation");
        System.out.println("5. User confirms changes");
        System.out.println("6. System saves preferences successfully");
        System.out.println("\nExit: System notifies user of successful modification!");
        System.out.println("Message: 'Search preferences have been successfully updated!'");
    }
    
    /**
     * Demonstrates user cancellation exit condition.
     */
    private static void demonstrateUserCancellation() {
        System.out.println("\n--- Demonstrating User Cancellation ---");
        System.out.println("This shows when the user cancels the operation.");
        
        System.out.println("\nPossible cancellation points:");
        System.out.println("1. During preference editing - user types 'cancel'");
        System.out.println("2. During confirmation - user chooses 'cancel' option");
        System.out.println("3. At any prompt - user can type 'cancel' to abort");
        
        System.out.println("\nExit: Operation is cancelled by the user.");
        System.out.println("Message: 'Operation cancelled by user.'");
        System.out.println("\nThe system returns to the main menu without saving changes.");
    }
    
    /**
     * Demonstrates server interruption exit condition.
     */
    private static void demonstrateServerInterruption() {
        System.out.println("\n--- Demonstrating Server Interruption ---");
        System.out.println("This shows when the connection to eTour server is interrupted.");
        
        System.out.println("\nPossible interruption scenarios:");
        System.out.println("1. Network connection lost during save operation");
        System.out.println("2. Server timeout while processing request");
        System.out.println("3. Database connection failure");
        
        System.out.println("\nExit: System handles the interruption gracefully.");
        System.out.println("Message: 'Error: Failed to save preferences. Server may be unavailable.'");
        System.out.println("\nThe system informs the user and allows them to try again later.");
        System.out.println("In a real system, this might trigger a retry mechanism or offline mode.");
    }
    
    /**
     * Helper method to print a separator line.
     */
    private static void printSeparator() {
        System.out.println("========================================");
    }
}