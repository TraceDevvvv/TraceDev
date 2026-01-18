package workspace.ModifyCulturalHeritage_1766884653;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ModifyCulturalHeritageApp: Main application class that implements the full ModifyCulturalHeritage use case.
 * This program simulates the process of modifying cultural heritage data in the system.
 * It follows the use case flow exactly and handles all specified exit conditions.
 * 
 * Key features:
 * 1. Simulated login for Agency Operator
 * 2. View cultural heritage list (from SearchCulturalHeritage use case simulation)
 * 3. Select cultural heritage for modification
 * 4. Load and display edit form
 * 5. Data validation and error handling (Errored use case)
 * 6. Confirmation mechanism
 * 7. Input blocking after confirmation to prevent multiple submissions
 * 8. Data persistence simulation
 * 9. Handling of all exit conditions
 */
public class ModifyCulturalHeritageApp {
    private CulturalHeritageDataManager dataManager;
    private Scanner scanner;
    private boolean isLoggedIn;
    private AtomicBoolean inputBlocked; // AtomicBoolean for thread-safe input blocking
    private boolean operationInProgress;
    
    /**
     * Constructor: Initializes the application with necessary components
     */
    public ModifyCulturalHeritageApp() {
        this.dataManager = new CulturalHeritageDataManager();
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = false;
        this.inputBlocked = new AtomicBoolean(false);
        this.operationInProgress = false;
    }
    
    /**
     * Main entry point of the application
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Modify Cultural Heritage System ===\n");
        
        ModifyCulturalHeritageApp app = new ModifyCulturalHeritageApp();
        app.run();
        
        System.out.println("\n=== System terminated ===");
    }
    
    /**
     * Main application run loop
     */
    private void run() {
        // Entry condition: Agency must be logged in
        if (!login()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        
        // Main application loop
        boolean exitRequested = false;
        while (!exitRequested && isLoggedIn) {
            displayMainMenu();
            
            try {
                int choice = getMenuChoice(1, 3);
                
                switch (choice) {
                    case 1:
                        // View list and select cultural heritage for modification
                        modifyCulturalHeritage();
                        break;
                    case 2:
                        // View system status
                        viewSystemStatus();
                        break;
                    case 3:
                        // Exit system
                        exitRequested = true;
                        System.out.println("Exiting system...");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                // Continue to prevent the application from crashing
            }
        }
        
        // Close scanner to prevent resource leak
        scanner.close();
    }
    
    /**
     * Simulates login process for Agency Operator
     * 
     * @return true if login successful, false otherwise
     */
    private boolean login() {
        System.out.println("=== Login Required ===");
        System.out.print("Enter Agency ID: ");
        String agencyId = scanner.nextLine().trim();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        // Simulated authentication (in a real system, this would validate against a database)
        if ("agency123".equals(agencyId) && "securepass".equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successful! Welcome Agency Operator.\n");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }
    
    /**
     * Displays the main menu
     */
    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Modify Cultural Heritage");
        System.out.println("2. View System Status");
        System.out.println("3. Exit");
        System.out.print("Select an option (1-3): ");
    }
    
    /**
     * Gets and validates menu choice from user
     * 
     * @param min Minimum valid choice
     * @param max Maximum valid choice
     * @return Validated user choice
     */
    private int getMenuChoice(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter a number between %d and %d: ", min, max);
            }
        }
    }
    
    /**
     * Implements the full ModifyCulturalHeritage use case flow
     * 1. View list from SearchCulturalHeritage
     * 2. Select and activate change function
     * 3. Load data and display edit form
     * 4. Change data and submit
     * 5. Verify data and ask for confirmation
     * 6. Confirm operation
     * 7. Store modified data
     */
    private void modifyCulturalHeritage() {
        System.out.println("\n=== Modify Cultural Heritage ===");
        
        // Check if input is blocked (prevent multiple submissions)
        if (inputBlocked.get()) {
            System.out.println("A modification operation is already in progress. Please wait...");
            return;
        }
        
        try {
            // Step 1: View the list of cultural goods (simulating SearchCulturalHeritage use case)
            List<CulturalHeritage> heritageList = dataManager.getAllCulturalHeritage();
            
            if (heritageList.isEmpty()) {
                System.out.println("No cultural heritage items found in the system.");
                return;
            }
            
            System.out.println("\n=== Cultural Heritage List ===");
            for (int i = 0; i < heritageList.size(); i++) {
                CulturalHeritage item = heritageList.get(i);
                System.out.printf("%d. %s (ID: %s, Location: %s)\n", 
                    i + 1, item.getName(), item.getId(), item.getLocation());
            }
            
            // Step 2: Select a cultural heritage item to modify
            System.out.printf("\nSelect a cultural heritage item to modify (1-%d, or 0 to cancel): ", 
                heritageList.size());
            
            int selection = getMenuChoice(0, heritageList.size());
            
            if (selection == 0) {
                System.out.println("Operation cancelled by user.");
                return; // Exit condition: Operator cancels the operation
            }
            
            CulturalHeritage selectedHeritage = heritageList.get(selection - 1);
            System.out.println("\nSelected: " + selectedHeritage);
            
            // Step 3: Load data and display edit form
            CulturalHeritage editedHeritage = selectedHeritage.copy();
            displayEditForm(editedHeritage);
            
            // Step 4: Change data in form and submit
            boolean formCompleted = fillEditForm(editedHeritage);
            if (!formCompleted) {
                System.out.println("Form submission cancelled.");
                return; // Operator cancelled form filling
            }
            
            // Step 5: Verify data
            String validationErrors = editedHeritage.getValidationErrors();
            if (!validationErrors.isEmpty()) {
                // Activate Errored use case (simulated)
                System.out.println("\n=== DATA VALIDATION ERROR ===");
                System.out.println("Invalid or insufficient data detected:");
                System.out.println(validationErrors);
                System.out.println("\nPlease correct the errors and try again.");
                // In a real system, this would return to the edit form
                return;
            }
            
            // Step 5 (continued): Ask for confirmation
            System.out.println("\n=== CONFIRMATION REQUIRED ===");
            System.out.println("Please review the changes:");
            System.out.println("Original: " + selectedHeritage);
            System.out.println("Modified: " + editedHeritage);
            
            System.out.print("\nDo you want to save these changes? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!"yes".equals(confirmation) && !"y".equals(confirmation)) {
                System.out.println("Operation cancelled by user.");
                return; // Exit condition: Operator cancels the operation
            }
            
            // Block input controls to prevent multiple submissions
            inputBlocked.set(true);
            operationInProgress = true;
            
            // Step 6: Confirm operation and store modified data
            System.out.println("\nSaving changes...");
            
            // Simulate processing delay
            Thread.sleep(1500);
            
            // Check for server connection interruption (simulated random failure)
            if (Math.random() < 0.1) { // 10% chance to simulate server interruption
                dataManager.simulateServerInterruption();
                throw new CulturalHeritageDataManager.DataManagerException(
                    "Server connection interrupted during save operation.");
            }
            
            // Step 7: Store the modified data
            boolean success = dataManager.updateCulturalHeritage(editedHeritage);
            
            if (success) {
                System.out.println("\n=== OPERATION SUCCESSFUL ===");
                System.out.println("Cultural heritage data has been successfully updated.");
                System.out.println("Notification sent: Cultural heritage '" + editedHeritage.getName() + 
                    "' has been modified.");
                
                // Exit condition: Notification system has been changing the data
                System.out.println("\nReturning to main menu...");
            } else {
                System.out.println("\n=== OPERATION FAILED ===");
                System.out.println("Failed to update cultural heritage data. The item may have been deleted.");
            }
            
        } catch (CulturalHeritageDataManager.DataManagerException e) {
            // Handle data manager exceptions (including server connection interruption)
            System.out.println("\n=== OPERATION FAILED ===");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Exit condition: " + 
                (e.getMessage().contains("Server connection") ? 
                    "Interruption of the connection to the server" : 
                    "System error occurred"));
                    
            // If server disconnected, try to restore connection
            if (!dataManager.isServerConnected()) {
                System.out.println("Attempting to restore server connection...");
                dataManager.restoreServerConnection();
            }
        } catch (InterruptedException e) {
            System.out.println("Operation interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        } catch (Exception e) {
            System.out.println("Unexpected error during modification: " + e.getMessage());
        } finally {
            // Always unblock input controls when operation is complete
            inputBlocked.set(false);
            operationInProgress = false;
        }
    }
    
    /**
     * Displays the edit form with current cultural heritage data
     * 
     * @param heritage CulturalHeritage object to display
     */
    private void displayEditForm(CulturalHeritage heritage) {
        System.out.println("\n=== EDIT CULTURAL HERITAGE FORM ===");
        System.out.println("Current values are shown in parentheses.");
        System.out.println("Enter new values, or press Enter to keep current value.\n");
        
        System.out.println("Cultural Heritage ID: " + heritage.getId() + " (Cannot be changed)");
    }
    
    /**
     * Fills the edit form with user input
     * 
     * @param heritage CulturalHeritage object to modify
     * @return true if form was completed, false if cancelled
     */
    private boolean fillEditForm(CulturalHeritage heritage) {
        System.out.print("Name (" + heritage.getName() + "): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            heritage.setName(name);
        }
        
        System.out.print("Description (" + 
            (heritage.getDescription().length() > 30 ? 
                heritage.getDescription().substring(0, 30) + "..." : 
                heritage.getDescription()) + 
            "): ");
        String description = scanner.nextLine().trim();
        if (!description.isEmpty()) {
            heritage.setDescription(description);
        }
        
        System.out.print("Location (" + heritage.getLocation() + "): ");
        String location = scanner.nextLine().trim();
        if (!location.isEmpty()) {
            heritage.setLocation(location);
        }
        
        System.out.print("Year (" + heritage.getYear() + "): ");
        String yearStr = scanner.nextLine().trim();
        if (!yearStr.isEmpty()) {
            try {
                int year = Integer.parseInt(yearStr);
                heritage.setYear(year);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year format. Keeping current value.");
            }
        }
        
        System.out.print("Category (" + heritage.getCategory() + "): ");
        String category = scanner.nextLine().trim();
        if (!category.isEmpty()) {
            heritage.setCategory(category);
        }
        
        System.out.print("Status (" + heritage.getStatus() + "): ");
        String status = scanner.nextLine().trim();
        if (!status.isEmpty()) {
            heritage.setStatus(status);
        }
        
        System.out.println("\n=== FORM PREVIEW ===");
        System.out.println(heritage.toDetailedString());
        
        System.out.print("\nSubmit this form? (yes/no): ");
        String submit = scanner.nextLine().trim().toLowerCase();
        
        return "yes".equals(submit) || "y".equals(submit);
    }
    
    /**
     * Displays system status including connection state and data statistics
     */
    private void viewSystemStatus() {
        System.out.println("\n=== SYSTEM STATUS ===");
        System.out.println("Logged in: " + (isLoggedIn ? "Yes" : "No"));
        System.out.println("Server connected: " + 
            (dataManager.isServerConnected() ? "Yes" : "No"));
        System.out.println("Input controls blocked: " + 
            (inputBlocked.get() ? "Yes (operation in progress)" : "No"));
        System.out.println("Total cultural heritage items: " + 
            dataManager.getItemCount());
        System.out.println("Next operation ID: " + 
            dataManager.getNextOperationId());
    }
    
    /**
     * Helper method to simulate the Errored use case
     * This would be called when data validation fails
     */
    private void activateErroredUseCase(String errorMessage) {
        System.out.println("\n=== ERRORED USE CASE ACTIVATED ===");
        System.out.println("Error: " + errorMessage);
        System.out.println("Please correct the errors and try the operation again.");
    }
}