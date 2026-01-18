'''
Main application class for the ModifyDataRefreshmentPointAgency system.
Implements the complete workflow for modifying refreshment point data
according to the specified use case, including form locking to prevent
multiple submissions and proper error handling.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ModifyDataRefreshmentPointAgency {
    // Simulated database of refreshment points
    private static List<RefreshmentPoint> refreshmentPoints = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isFormLocked = false;
    private static boolean isLoggedIn = false;
    private static String currentUser = "";
    /**
     * Main method to start the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        initializeSampleData();
        runApplication();
        scanner.close();
    }
    /**
     * Initializes sample data for demonstration purposes.
     */
    private static void initializeSampleData() {
        refreshmentPoints.add(new RefreshmentPoint(1, "Rest Area A", "Highway 1, Mile 45", "Open", 50, "24/7"));
        refreshmentPoints.add(new RefreshmentPoint(2, "Mountain View Cafe", "Scenic Route 7", "Closed", 30, "9AM-6PM"));
        refreshmentPoints.add(new RefreshmentPoint(3, "Traveler's Oasis", "Interstate 90, Exit 23", "Under Maintenance", 100, "8AM-10PM"));
        refreshmentPoints.add(new RefreshmentPoint(4, "Desert Stop", "Route 66, Mile 120", "Open", 40, "6AM-10PM"));
        refreshmentPoints.add(new RefreshmentPoint(5, "Alpine Rest Area", "Mountain Pass Road", "Open", 25, "7AM-9PM"));
    }
    /**
     * Main application loop that handles the complete workflow.
     */
    private static void runApplication() {
        System.out.println("=== Modify Data Refreshment Point Agency System ===");
        // Entry condition: Agency must be logged in
        if (!login()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        boolean continueOperation = true;
        while (continueOperation) {
            try {
                System.out.println("\n--- Main Menu (User: " + currentUser + ") ---");
                System.out.println("1. View and select refreshment point to modify");
                System.out.println("2. View all refreshment points");
                System.out.println("3. Exit");
                System.out.print("Select option: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        modifyRefreshmentPointWorkflow();
                        break;
                    case 2:
                        viewAllRefreshmentPoints();
                        break;
                    case 3:
                        continueOperation = false;
                        System.out.println("Logging out. Goodbye, " + currentUser + "!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
    /**
     * Simulates user login (simplified for demonstration).
     * @return true if login successful, false otherwise.
     */
    private static boolean login() {
        System.out.print("Enter agency operator username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Simplified authentication
        if (!username.isEmpty() && !password.isEmpty()) {
            isLoggedIn = true;
            currentUser = username;
            System.out.println("Login successful! Welcome, " + username);
            return true;
        }
        System.out.println("Invalid credentials.");
        return false;
    }
    /**
     * Complete workflow for modifying a refreshment point.
     * Implements all steps from the use case specification.
     */
    private static void modifyRefreshmentPointWorkflow() {
        // Step 1: View list and select point (result of SearchRefreshmentPoint use case)
        RefreshmentPoint selectedPoint = selectRefreshmentPoint();
        if (selectedPoint == null) {
            System.out.println("No point selected. Returning to main menu.");
            return;
        }
        // Step 2: Display current data and edit form
        displayCurrentData(selectedPoint);
        // Step 3: Create and display edit form
        RefreshmentPoint modifiedPoint = displayEditForm(selectedPoint);
        if (modifiedPoint == null) {
            System.out.println("Edit cancelled by user.");
            isFormLocked = false; // Ensure form is unlocked on cancellation
            return;
        }
        // Step 4: Validate data (activates Errored use case if invalid)
        if (!validateData(modifiedPoint)) {
            handleError("Invalid or insufficient data provided.");
            return;
        }
        // Step 5: Confirm operation
        if (!confirmOperation(modifiedPoint, selectedPoint)) {
            System.out.println("Operation cancelled by user.");
            isFormLocked = false; // Unlock form on cancellation
            return;
        }
        // Step 6: Save modified data
        saveModifiedData(selectedPoint, modifiedPoint);
    }
    /**
     * Displays list of refreshment points and allows user selection.
     * Simulates the result of SearchRefreshmentPoint use case.
     * @return Selected refreshment point or null if cancelled.
     */
    private static RefreshmentPoint selectRefreshmentPoint() {
        System.out.println("\n--- Available Refreshment Points ---");
        if (refreshmentPoints.isEmpty()) {
            System.out.println("No refreshment points available.");
            return null;
        }
        // Display only active points (Active and Functional as per use case)
        List<RefreshmentPoint> activePoints = new ArrayList<>();
        System.out.println("ID\tName\t\t\tStatus");
        System.out.println("------------------------------------------------");
        for (RefreshmentPoint point : refreshmentPoints) {
            if (!"Closed".equals(point.getStatus())) {
                activePoints.add(point);
                System.out.printf("%d\t%-20s\t%s%n", 
                    point.getId(), 
                    point.getName(), 
                    point.getStatus());
            }
        }
        if (activePoints.isEmpty()) {
            System.out.println("No active refreshment points available.");
            return null;
        }
        System.out.println("\n0. Cancel operation");
        System.out.print("Select refreshment point ID: ");
        try {
            int selection = Integer.parseInt(scanner.nextLine());
            if (selection == 0) {
                System.out.println("Operation cancelled by agency operator.");
                return null;
            }
            for (RefreshmentPoint point : activePoints) {
                if (point.getId() == selection) {
                    System.out.println("✓ Selected: " + point.getName());
                    return point;
                }
            }
            System.out.println("Invalid selection. Please select a valid ID from the active points.");
            return selectRefreshmentPoint(); // Recursive call for invalid selection
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectRefreshmentPoint(); // Recursive call for invalid input
        }
    }
    /**
     * Displays current data of the selected refreshment point.
     * @param point The refreshment point to display.
     */
    private static void displayCurrentData(RefreshmentPoint point) {
        System.out.println("\n--- Current Data for " + point.getName() + " ---");
        System.out.println("ID: " + point.getId());
        System.out.println("Name: " + point.getName());
        System.out.println("Location: " + point.getLocation());
        System.out.println("Status: " + point.getStatus());
        System.out.println("Capacity: " + point.getCapacity());
        System.out.println("Operating Hours: " + point.getOperatingHours());
    }
    /**
     * Displays edit form and collects modified data.
     * Locks the form to prevent multiple submissions.
     * @param originalPoint The original refreshment point data.
     * @return Modified refreshment point or null if cancelled.
     */
    private static RefreshmentPoint displayEditForm(RefreshmentPoint originalPoint) {
        System.out.println("\n--- Edit Form ---");
        System.out.println("Enter new values (press Enter to keep current value):");
        // Lock the form to prevent multiple submissions (Quality Requirement)
        if (isFormLocked) {
            System.out.println("Form is locked. Another operation is in progress.");
            return null;
        }
        isFormLocked = true;
        System.out.println("[Form locked to prevent multiple submissions]");
        try {
            System.out.print("Name [" + originalPoint.getName() + "]: ");
            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                name = originalPoint.getName();
            }
            System.out.print("Location [" + originalPoint.getLocation() + "]: ");
            String location = scanner.nextLine();
            if (location.trim().isEmpty()) {
                location = originalPoint.getLocation();
            }
            System.out.print("Status (Open/Closed/Under Maintenance) [" + originalPoint.getStatus() + "]: ");
            String status = scanner.nextLine();
            if (status.trim().isEmpty()) {
                status = originalPoint.getStatus();
            }
            System.out.print("Capacity [" + originalPoint.getCapacity() + "]: ");
            String capacityInput = scanner.nextLine();
            int capacity = originalPoint.getCapacity();
            if (!capacityInput.trim().isEmpty()) {
                try {
                    capacity = Integer.parseInt(capacityInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Using current value.");
                }
            }
            System.out.print("Operating Hours [" + originalPoint.getOperatingHours() + "]: ");
            String operatingHours = scanner.nextLine();
            if (operatingHours.trim().isEmpty()) {
                operatingHours = originalPoint.getOperatingHours();
            }
            return new RefreshmentPoint(originalPoint.getId(), name, location, status, capacity, operatingHours);
        } catch (Exception e) {
            isFormLocked = false; // Unlock form on error
            System.out.println("Error reading input: " + e.getMessage());
            return null;
        }
    }
    /**
     * Validates the modified data as per Step 4 of use case.
     * @param point The refreshment point to validate.
     * @return true if data is valid, false otherwise.
     */
    private static boolean validateData(RefreshmentPoint point) {
        System.out.println("\n--- Validating Data ---");
        // Check for empty or null values
        if (point.getName() == null || point.getName().trim().isEmpty()) {
            System.out.println("✗ Error: Name cannot be empty.");
            return false;
        }
        if (point.getLocation() == null || point.getLocation().trim().isEmpty()) {
            System.out.println("✗ Error: Location cannot be empty.");
            return false;
        }
        if (point.getStatus() == null || point.getStatus().trim().isEmpty()) {
            System.out.println("✗ Error: Status cannot be empty.");
            return false;
        }
        // Validate status against allowed values
        String status = point.getStatus();
        if (!status.equals("Open") && !status.equals("Closed") && !status.equals("Under Maintenance")) {
            System.out.println("✗ Error: Status must be 'Open', 'Closed', or 'Under Maintenance'.");
            return false;
        }
        // Validate capacity
        if (point.getCapacity() <= 0) {
            System.out.println("✗ Error: Capacity must be a positive number.");
            return false;
        }
        if (point.getCapacity() > 1000) {
            System.out.println("✗ Error: Capacity cannot exceed 1000.");
            return false;
        }
        if (point.getOperatingHours() == null || point.getOperatingHours().trim().isEmpty()) {
            System.out.println("✗ Error: Operating hours cannot be empty.");
            return false;
        }
        System.out.println("✓ All data is valid.");
        return true;
    }
    /**
     * Asks for user confirmation before saving changes (Step 5).
     * @param modifiedPoint The modified refreshment point.
     * @param originalPoint The original refreshment point.
     * @return true if user confirms, false otherwise.
     */
    private static boolean confirmOperation(RefreshmentPoint modifiedPoint, RefreshmentPoint originalPoint) {
        System.out.println("\n--- Confirm Changes ---");
        System.out.println("Please review the changes:");
        System.out.println("\nCurrent Data:");
        System.out.println("Name: " + originalPoint.getName());
        System.out.println("Location: " + originalPoint.getLocation());
        System.out.println("Status: " + originalPoint.getStatus());
        System.out.println("Capacity: " + originalPoint.getCapacity());
        System.out.println("Operating Hours: " + originalPoint.getOperatingHours());
        System.out.println("\nNew Data:");
        System.out.println("Name: " + modifiedPoint.getName());
        System.out.println("Location: " + modifiedPoint.getLocation());
        System.out.println("Status: " + modifiedPoint.getStatus());
        System.out.println("Capacity: " + modifiedPoint.getCapacity());
        System.out.println("Operating Hours: " + modifiedPoint.getOperatingHours());
        System.out.print("\nDo you want to save these changes? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        return confirmation.equals("yes") || confirmation.equals("y");
    }
    /**
     * Saves the modified data to the system (Step 6).
     * Handles server connection interruption as per exit condition.
     * @param originalPoint The original refreshment point.
     * @param modifiedPoint The modified refreshment point.
     */
    private static void saveModifiedData(RefreshmentPoint originalPoint, RefreshmentPoint modifiedPoint) {
        try {
            System.out.println("\n--- Saving Data ---");
            // Check server connection (Exit condition: Interruption of connection)
            if (!checkServerConnection()) {
                handleError("ETOUR server connection interrupted. Data not saved.");
                return;
            }
            // Update the point in the list
            boolean found = false;
            for (int i = 0; i < refreshmentPoints.size(); i++) {
                if (refreshmentPoints.get(i).getId() == originalPoint.getId()) {
                    refreshmentPoints.set(i, modifiedPoint);
                    found = true;
                    break;
                }
            }
            if (!found) {
                handleError("Refreshment point not found in database.");
                return;
            }
            System.out.println("\n✓ Data successfully saved to ETOUR server!");
            System.out.println("✓ Refreshment point '" + modifiedPoint.getName() + "' has been updated.");
            System.out.println("✓ System has reported the information required by the point of rest.");
            // Exit condition satisfied: System has been reporting the information
            isFormLocked = false; // Unlock form after successful operation
        } catch (Exception e) {
            handleError("Error saving data: " + e.getMessage());
        }
    }
    /**
     * Simulates server connection check.
     * @return true if server is connected, false otherwise.
     */
    private static boolean checkServerConnection() {
        System.out.println("Connecting to ETOUR server...");
        try {
            // Simulate network connectivity check with random success
            Thread.sleep(500); // Simulate network delay
            boolean connected = Math.random() > 0.2; // 80% success rate
            if (connected) {
                System.out.println("✓ Connected to ETOUR server.");
            } else {
                System.out.println("✗ Connection to ETOUR server failed.");
            }
            return connected;
        } catch (InterruptedException e) {
            System.out.println("✗ Server connection interrupted.");
            return false;
        }
    }
    /**
     * Handles error conditions as per the Errored use case.
     * @param errorMessage The error message to display.
     */
    private static void handleError(String errorMessage) {
        System.out.println("\n✗ ERROR: " + errorMessage);
        System.out.println("Activating Errored use case...");
        // Reset form lock on error
        isFormLocked = false;
        // Log error details
        System.out.println("Error details logged: " + 
            java.time.LocalDateTime.now() + " | User: " + currentUser);
        System.out.println("Please contact system administrator if problem persists.");
    }
    /**
     * Displays all refreshment points for reference.
     */
    private static void viewAllRefreshmentPoints() {
        System.out.println("\n--- All Refreshment Points ---");
        System.out.println("ID\tName\t\t\tLocation\t\tStatus\t\tCapacity\tHours");
        System.out.println("------------------------------------------------------------------------------------------------");
        for (RefreshmentPoint point : refreshmentPoints) {
            System.out.printf("%d\t%-20s\t%-20s\t%-15s\t%d\t\t%s%n",
                point.getId(),
                point.getName(),
                point.getLocation().length() > 20 ? point.getLocation().substring(0, 17) + "..." : point.getLocation(),
                point.getStatus(),
                point.getCapacity(),
                point.getOperatingHours());
        }
        System.out.println("\nTotal points: " + refreshmentPoints.size());
    }
}