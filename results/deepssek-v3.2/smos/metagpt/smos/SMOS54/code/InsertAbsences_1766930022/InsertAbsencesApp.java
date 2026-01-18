import java.util.Scanner;

/**
 * InsertAbsencesApp is the main class that simulates the InsertAbsences use case.
 * It handles user login, class selection, and orchestrates the absence management process.
 * This is the entry point of the Java program.
 */
public class InsertAbsencesApp {
    private static Scanner scanner = new Scanner(System.in);
    private static AbsenceManager absenceManager;
    private static boolean loggedIn = false;
    private static boolean classSelected = false;

    /**
     * Main method - entry point of the application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("ATA SCHOOL - ABSENCE MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        
        try {
            runApplication();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    /**
     * Main application loop that handles the complete use case flow.
     */
    private static void runApplication() {
        // Step 1: Login (simulated)
        login();
        
        // Step 2: Class selection (simulated)
        selectClass();
        
        // Step 3: Initialize absence manager for the selected class
        initializeAbsenceManager();
        
        // Step 4: Main interaction loop
        mainLoop();
    }

    /**
     * Simulates user login as ATA staff.
     * Precondition: User must be logged in as ATA staff.
     */
    private static void login() {
        System.out.println("\n--- LOGIN ---");
        System.out.println("Precondition: User must be logged in as ATA staff.");
        
        // In a real application, this would involve authentication
        // For simulation, we'll just ask for confirmation
        System.out.print("Are you logged in as ATA staff? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes") || response.equals("y")) {
            loggedIn = true;
            System.out.println("✓ Login successful. Welcome, ATA staff!");
        } else {
            System.out.println("✗ You must be logged in as ATA staff to use this system.");
            System.out.println("Exiting application...");
            System.exit(0);
        }
    }

    /**
     * Simulates class selection by ATA staff.
     * Precondition: User has carried out the use case ViewLancoclassiata and selected a class.
     */
    private static void selectClass() {
        if (!loggedIn) {
            System.out.println("Error: User not logged in.");
            return;
        }
        
        System.out.println("\n--- CLASS SELECTION ---");
        System.out.println("Precondition: User has selected a class from ViewLancoclassiata.");
        
        // Display available classes (simulated)
        System.out.println("Available Classes:");
        System.out.println("1. Class 10A (Mathematics)");
        System.out.println("2. Class 10B (Science)");
        System.out.println("3. Class 10C (Literature)");
        
        System.out.print("\nSelect a class (1-3) or press Enter for default (Class 10A): ");
        String input = scanner.nextLine().trim();
        
        int classChoice;
        try {
            classChoice = input.isEmpty() ? 1 : Integer.parseInt(input);
            if (classChoice < 1 || classChoice > 3) {
                System.out.println("Invalid choice. Defaulting to Class 10A.");
                classChoice = 1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Defaulting to Class 10A.");
            classChoice = 1;
        }
        
        String[] classNames = {"Class 10A (Mathematics)", "Class 10B (Science)", "Class 10C (Literature)"};
        System.out.println("✓ Selected: " + classNames[classChoice - 1]);
        classSelected = true;
    }

    /**
     * Initializes the AbsenceManager for the selected class.
     * In a real application, this would load students from a database.
     */
    private static void initializeAbsenceManager() {
        if (!loggedIn || !classSelected) {
            System.out.println("Error: Cannot initialize - login or class selection incomplete.");
            return;
        }
        
        System.out.println("\n--- INITIALIZING CLASS DATA ---");
        System.out.println("Loading student data for selected class...");
        
        absenceManager = new AbsenceManager();
        
        System.out.println("✓ Class data loaded successfully.");
        System.out.println("System is ready to record absences.");
    }

    /**
     * Main interaction loop that handles the core use case events.
     */
    private static void mainLoop() {
        if (absenceManager == null) {
            System.out.println("Error: AbsenceManager not initialized.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("INSERT ABSENCES - MAIN INTERFACE");
        System.out.println("=".repeat(60));
        
        // Event 1: System shows screen with pupils and radio buttons
        // (Simulated by displaying students with current status)
        absenceManager.displayStudents();
        
        boolean exitRequested = false;
        
        while (!exitRequested) {
            System.out.println("\nOptions:");
            System.out.println("1. Mark students absent/present");
            System.out.println("2. Save absences (simulates Save button click)");
            System.out.println("3. View current attendance");
            System.out.println("4. Exit application");
            System.out.print("\nEnter your choice (1-4): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    // Event 2: User selects absent students
                    handleAbsenceSelection();
                    break;
                    
                case "2":
                    // Event 3: User clicks Save
                    // Event 4: System sends data to server and sends notifications
                    handleSaveOperation();
                    break;
                    
                case "3":
                    // Show current attendance status
                    absenceManager.displayStudents();
                    break;
                    
                case "4":
                    exitRequested = true;
                    System.out.println("\nExiting application...");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }

    /**
     * Handles the absence selection process.
     * Simulates user selecting radio buttons for absent students.
     */
    private static void handleAbsenceSelection() {
        System.out.println("\n--- MARK STUDENTS ABSENT/PRESENT ---");
        System.out.println("Instructions:");
        System.out.println("- Enter student IDs to mark as absent (comma-separated)");
        System.out.println("- To mark a student as present, omit their ID");
        System.out.println("- Type 'cancel' to interrupt the operation");
        System.out.println("- Type 'save' to save and send notifications");
        
        absenceManager.selectAbsentStudents();
    }

    /**
     * Handles the save operation, including server communication and notifications.
     * Simulates the "Save" button click and subsequent system actions.
     */
    private static void handleSaveOperation() {
        System.out.println("\n--- SAVE ABSENCES ---");
        System.out.println("Clicking Save button...");
        
        // This triggers the save process in AbsenceManager
        absenceManager.saveAbsences();
        
        // Postcondition: Absence data has been entered and notifications sent
        // Postcondition: Initial screen is shown again (handled by saveAbsences)
        System.out.println("\n✓ Use case completed successfully.");
        System.out.println("Postconditions:");
        System.out.println("1. Absence data has been entered in the system");
        System.out.println("2. Notifications sent to parents (where possible)");
        System.out.println("3. Initial screen is shown again");
    }

    /**
     * Cleans up resources before application exit.
     */
    private static void cleanup() {
        System.out.println("\n--- CLEANUP ---");
        
        if (absenceManager != null) {
            absenceManager.close();
            System.out.println("✓ AbsenceManager resources released.");
        }
        
        if (scanner != null) {
            scanner.close();
            System.out.println("✓ Scanner resources released.");
        }
        
        System.out.println("\nThank you for using ATA School Absence Management System!");
        System.out.println("==========================================");
    }

    /**
     * Handles connection interruption to the SMOS server.
     * This is an edge case handler that would be called in real scenarios.
     */
    private static void handleConnectionInterruption() {
        System.out.println("\n⚠️  WARNING: Connection to SMOS server interrupted!");
        System.out.println("Attempting to handle the interruption...");
        
        // In a real application, this would implement retry logic or fallback
        System.out.println("Please check your network connection and try again.");
        System.out.println("You can continue working offline and save later.");
    }

    /**
     * Handles user interruption of the operation.
     * This is an edge case handler for when the user interrupts the operation.
     */
    private static void handleUserInterruption() {
        System.out.println("\n⚠️  User has interrupted the operation.");
        absenceManager.handleUserInterruption();
    }
}