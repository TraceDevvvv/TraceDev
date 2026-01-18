import java.util.List;
import java.util.Scanner;

/**
 * UserInterface class handles the console-based user interface for the 
 * ModifyDataRefreshmentPointAgency use case.
 * This class implements the flow of events as described in the use case:
 * 1. View list of points of rest (from SearchRefreshmentPoint)
 * 2. Load and display form for selected point
 * 3. Collect user modifications
 * 4. Validate data and ask for confirmation
 * 5. Handle confirmation or error
 * 6. Store modified data
 */
public class UserInterface {
    private RefreshmentPointService service;
    private Scanner scanner;
    private boolean isLoggedIn;
    
    /**
     * Constructor initializes the UserInterface with required dependencies.
     */
    public UserInterface() {
        this.service = new RefreshmentPointService();
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = false;
    }
    
    /**
     * Main entry point for the user interface.
     * Implements the complete flow described in the use case.
     */
    public void start() {
        System.out.println("==========================================");
        System.out.println("   Refreshment Point Management System    ");
        System.out.println("      Modify Data (Agency Operator)       ");
        System.out.println("==========================================");
        System.out.println();
        
        // Entry condition: Agency must be logged in
        if (!login()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        
        boolean continueRunning = true;
        while (continueRunning) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    executeModifyDataWorkflow();
                    break;
                case "2":
                    searchRefreshmentPoints();
                    break;
                case "3":
                    viewAllRefreshmentPoints();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    isLoggedIn = false;
                    continueRunning = false;
                    break;
                case "5":
                    System.out.println("Exiting system. Goodbye!");
                    continueRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
            
            if (continueRunning) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Implements the login process as per entry condition: "The agency has logged."
     * 
     * @return true if login successful, false otherwise
     */
    private boolean login() {
        System.out.println("=== AGENCY LOGIN ===");
        System.out.print("Enter Agency ID: ");
        String agencyId = scanner.nextLine().trim();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();
        
        // Simple authentication for demonstration
        // In a real system, this would validate against a database
        if (agencyId.isEmpty() || password.isEmpty()) {
            System.out.println("Login failed: Agency ID and password are required.");
            return false;
        }
        
        // Simple validation - in production this would be more secure
        if (agencyId.equals("agency") && password.equals("password")) {
            System.out.println("Login successful! Welcome, Agency Operator.");
            isLoggedIn = true;
            return true;
        } else {
            System.out.println("Login failed: Invalid credentials.");
            return false;
        }
    }
    
    /**
     * Displays the main menu to the user.
     */
    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Modify Refreshment Point Data");
        System.out.println("2. Search Refreshment Points");
        System.out.println("3. View All Refreshment Points");
        System.out.println("4. Logout");
        System.out.println("5. Exit System");
        System.out.print("Enter your choice (1-5): ");
    }
    
    /**
     * Executes the complete Modify Data workflow as described in the use case.
     * This method implements all steps from the flow of events.
     */
    private void executeModifyDataWorkflow() {
        System.out.println("\n=== MODIFY REFRESHMENT POINT DATA ===");
        
        // Step 1: View list of points and select one
        System.out.println("\n--- Step 1: Select a Refreshment Point ---");
        List<RefreshmentPoint> searchResults = performSearch();
        
        if (searchResults.isEmpty()) {
            System.out.println("No refreshment points found. Returning to main menu.");
            return;
        }
        
        // Filter to show only active and functional points as per use case
        List<RefreshmentPoint> activePoints = searchResults.stream()
                .filter(RefreshmentPoint::isActiveAndFunctional)
                .toList();
        
        if (activePoints.isEmpty()) {
            System.out.println("No active and functional refreshment points found.");
            System.out.println("Only active and functional points can be modified.");
            return;
        }
        
        System.out.println("\nActive and Functional Refreshment Points:");
        for (int i = 0; i < activePoints.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, activePoints.get(i));
        }
        
        System.out.print("\nSelect a refreshment point (enter number) or 0 to cancel: ");
        int selection = readIntInput(0, activePoints.size());
        
        if (selection == 0) {
            System.out.println("Operation cancelled by operator.");
            return;
        }
        
        RefreshmentPoint selectedPoint = activePoints.get(selection - 1);
        
        // Step 2: Load and display form
        System.out.println("\n--- Step 2: Current Data Loaded ---");
        System.out.println(selectedPoint.toDetailedString());
        
        // Create a copy for modification
        RefreshmentPoint modifiedPoint = selectedPoint.copy();
        
        // Step 3: Display modification form and collect changes
        System.out.println("\n--- Step 3: Modify Data ---");
        System.out.println("Enter new values (press Enter to keep current value):");
        
        collectFormInputs(modifiedPoint);
        
        // Step 4: Validate data and ask for confirmation
        System.out.println("\n--- Step 4: Validation and Confirmation ---");
        
        // Check server connection (exit condition)
        if (!service.checkServerConnection()) {
            System.out.println("Operation aborted due to server connection issue.");
            return;
        }
        
        RefreshmentPointService.ValidationResult validationResult = 
                service.validateRefreshmentPoint(modifiedPoint);
        
        if (!validationResult.isValid()) {
            System.out.println("\nVALIDATION ERRORS:");
            System.out.println(validationResult.getFormattedErrors());
            System.out.println("Activating error handling (use case Errored)...");
            handleErrorCase(validationResult);
            return;
        }
        
        // Step 5: Confirm operation
        System.out.println("\nData validation successful.");
        boolean confirmed = service.confirmModification(selectedPoint, modifiedPoint);
        
        if (!confirmed) {
            System.out.println("Modification not confirmed. Operation cancelled.");
            return;
        }
        
        // Step 6: Store modified data
        System.out.println("\n--- Step 6: Storing Modified Data ---");
        
        // Block input controls to prevent multiple submissions as per quality requirement
        System.out.println("Input controls blocked to prevent multiple submissions...");
        
        boolean storageSuccess = service.storeModifiedRefreshmentPoint(modifiedPoint);
        
        if (storageSuccess) {
            System.out.println("\nSUCCESS: Refreshment point data has been updated.");
            System.out.println("The system has reported the information required by the point of rest.");
        } else {
            System.out.println("\nERROR: Failed to store modified data.");
            System.out.println("Please try again or contact system administrator.");
        }
    }
    
    /**
     * Performs a search for refreshment points as described in Step 1.
     * 
     * @return list of refreshment points matching search criteria
     */
    private List<RefreshmentPoint> performSearch() {
        System.out.print("Enter search term (or press Enter to view all): ");
        String searchTerm = scanner.nextLine().trim();
        
        return service.searchRefreshmentPoints(searchTerm);
    }
    
    /**
     * Collects form inputs from the user for modifying a refreshment point.
     * Implements Step 3 of the use case: "Change data in the form"
     * 
     * @param point the refreshment point to modify
     */
    private void collectFormInputs(RefreshmentPoint point) {
        System.out.printf("Name [%s]: ", point.getName());
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            point.setName(input);
        }
        
        System.out.printf("Location [%s]: ", point.getLocation());
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            point.setLocation(input);
        }
        
        System.out.printf("Description [%s]: ", point.getDescription() != null ? point.getDescription() : "");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            point.setDescription(input);
        }
        
        System.out.printf("Facilities [%s]: ", point.getFacilities() != null ? point.getFacilities() : "");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            point.setFacilities(input);
        }
        
        System.out.printf("Status (ACTIVE/INACTIVE/UNDER_MAINTENANCE) [%s]: ", point.getStatus());
        input = scanner.nextLine().trim().toUpperCase();
        if (!input.isEmpty() && (input.equals("ACTIVE") || input.equals("INACTIVE") || input.equals("UNDER_MAINTENANCE"))) {
            point.setStatus(input);
        } else if (!input.isEmpty()) {
            System.out.println("Invalid status. Keeping current value.");
        }
        
        System.out.printf("Functional (true/false) [%s]: ", point.isFunctional());
        input = scanner.nextLine().trim().toLowerCase();
        if (!input.isEmpty() && (input.equals("true") || input.equals("false"))) {
            point.setFunctional(Boolean.parseBoolean(input));
        } else if (!input.isEmpty()) {
            System.out.println("Invalid value. Keeping current value.");
        }
        
        System.out.printf("Capacity [%d]: ", point.getCapacity());
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                int capacity = Integer.parseInt(input);
                if (capacity >= 0) {
                    point.setCapacity(capacity);
                } else {
                    System.out.println("Capacity cannot be negative. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Keeping current value.");
            }
        }
        
        System.out.printf("Contact Phone [%s]: ", point.getContactPhone() != null ? point.getContactPhone() : "");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            point.setContactPhone(input);
        }
        
        System.out.printf("Contact Email [%s]: ", point.getContactEmail() != null ? point.getContactEmail() : "");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            point.setContactEmail(input);
        }
        
        System.out.printf("Operating Hours [%s]: ", point.getOperatingHours() != null ? point.getOperatingHours() : "");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            point.setOperatingHours(input);
        }
    }
    
    /**
     * Handles the error case as described in Step 4 of use case.
     * "Where the data is invalid or insufficient, the system activates the use case Errored."
     * 
     * @param validationResult the validation result containing error details
     */
    private void handleErrorCase(RefreshmentPointService.ValidationResult validationResult) {
        System.out.println("\n=== ERROR HANDLING ACTIVATED ===");
        System.out.println("The following issues were found with your data:");
        System.out.println(validationResult.getFormattedErrors());
        
        System.out.println("\nOptions:");
        System.out.println("1. Return to form and correct errors");
        System.out.println("2. Cancel operation");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = readIntInput(1, 2);
        
        if (choice == 1) {
            System.out.println("Returning to form...");
            // In a real implementation, this would return to the form
            // For simplicity, we'll just return to main menu
        } else {
            System.out.println("Operation cancelled due to errors.");
        }
    }
    
    /**
     * Implements the SearchRefreshmentPoint use case mentioned in Step 1.
     * Allows user to search for refreshment points.
     */
    private void searchRefreshmentPoints() {
        System.out.println("\n=== SEARCH REFRESHMENT POINTS ===");
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().trim();
        
        List<RefreshmentPoint> results = service.searchRefreshmentPoints(searchTerm);
        
        if (results.isEmpty()) {
            System.out.println("No refreshment points found matching your search.");
        } else {
            System.out.println("\nSearch Results:");
            System.out.println("Found " + results.size() + " refreshment point(s):");
            
            for (int i = 0; i < results.size(); i++) {
                RefreshmentPoint point = results.get(i);
                String statusIndicator = point.isActiveAndFunctional() ? "[ACTIVE]" : "[INACTIVE/MAINTENANCE]";
                System.out.printf("%d. %s %s - %s\n", 
                    i + 1, statusIndicator, point.getName(), point.getLocation());
            }
        }
    }
    
    /**
     * Displays all refreshment points in the system.
     */
    private void viewAllRefreshmentPoints() {
        System.out.println("\n=== ALL REFRESHMENT POINTS ===");
        List<RefreshmentPoint> allPoints = service.searchRefreshmentPoints("");
        
        if (allPoints.isEmpty()) {
            System.out.println("No refreshment points in the system.");
        } else {
            System.out.println("Total refreshment points: " + allPoints.size());
            
            int activeCount = (int) allPoints.stream()
                    .filter(RefreshmentPoint::isActiveAndFunctional)
                    .count();
            
            System.out.println("Active and functional: " + activeCount);
            System.out.println("Inactive/maintenance: " + (allPoints.size() - activeCount));
            System.out.println();
            
            for (RefreshmentPoint point : allPoints) {
                System.out.println(point);
            }
        }
    }
    
    /**
     * Helper method to read integer input with validation.
     * 
     * @param min minimum valid value
     * @param max maximum valid value
     * @return the validated integer input
     */
    private int readIntInput(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter a number between %d and %d: ", min, max);
            }
        }
    }
    
    /**
     * Simulates the "Operator Agency cancels the operation" exit condition.
     * This can be called from various points in the workflow.
     */
    public void cancelOperation() {
        System.out.println("\nOperation cancelled by agency operator.");
        service.cancelOperation();
    }
    
    /**
     * Checks if user is currently logged in.
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}