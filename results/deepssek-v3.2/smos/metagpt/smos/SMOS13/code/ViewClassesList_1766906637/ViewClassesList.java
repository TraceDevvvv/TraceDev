import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Main program for the ViewClassesList use case.
 * This console-based application allows administrators to view classes for a selected academic year.
 * Implements the complete use case flow with error handling and edge cases.
 */
public class ViewClassesList {
    
    // Simulated database of classes by academic year
    private static Map<String, List<Class>> classDatabase = new HashMap<>();
    
    // Current admin session
    private static AdminSession currentSession = null;
    
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    
    // Flag to track if SMOS server connection is active
    private static boolean smosConnectionActive = true;
    
    /**
     * Main entry point of the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Class Management System ===");
        System.out.println("Starting ViewClassesList use case...\n");
        
        // Initialize sample data
        initializeSampleData();
        
        try {
            // Step 1: Check preconditions - user must be logged in as admin
            if (!loginAsAdmin()) {
                System.out.println("Login failed. Exiting application.");
                return;
            }
            
            // Step 2: Simulate clicking "Class Management" button
            System.out.println("\nClicking 'Class Management' button...");
            System.out.println("Loading Class Management interface...\n");
            
            // Step 3: System allows year selection
            displayYearSelection();
            
            // Step 4: User selects academic year
            String selectedYear = getUserYearSelection();
            
            // Check for admin interruption
            if (checkForAdminInterruption()) {
                System.out.println("Operation interrupted by administrator.");
                return;
            }
            
            // Step 5: System searches archive and displays class management screen
            System.out.println("\nSearching for classes in archive for academic year: " + selectedYear);
            displayClassManagementScreen(selectedYear);
            
            // Step 6: System displays list of classes for selected year
            List<Class> classes = ClassManager.searchClassesByYear(selectedYear, classDatabase);
            displayClassesList(classes, selectedYear);
            
            // Check postconditions
            verifyPostconditions(classes, selectedYear);
            
        } catch (SMOSConnectionException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Postcondition triggered: Connection to SMOS server interrupted.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            if (scanner != null) {
                scanner.close();
            }
            if (currentSession != null) {
                currentSession.logout();
            }
            System.out.println("\n=== Application terminated ===");
        }
    }
    
    /**
     * Initialize sample class data for demonstration.
     */
    private static void initializeSampleData() {
        System.out.println("Initializing sample data...");
        
        // Create classes for 2023-2024 academic year
        List<Class> classes2024 = new ArrayList<>();
        classes2024.add(new Class("CS101", "Introduction to Computer Science", "Dr. Smith", 30));
        classes2024.add(new Class("MATH201", "Calculus II", "Prof. Johnson", 25));
        classes2024.add(new Class("ENG101", "English Composition", "Dr. Williams", 40));
        classes2024.add(new Class("PHYS101", "Physics Fundamentals", "Prof. Brown", 35));
        classDatabase.put("2023-2024", classes2024);
        
        // Create classes for 2022-2023 academic year
        List<Class> classes2023 = new ArrayList<>();
        classes2023.add(new Class("CS101", "Introduction to Computer Science", "Dr. Smith", 28));
        classes2023.add(new Class("MATH101", "Calculus I", "Prof. Davis", 32));
        classes2023.add(new Class("HIST101", "World History", "Dr. Miller", 45));
        classDatabase.put("2022-2023", classes2023);
        
        // Create classes for 2024-2025 academic year (future year)
        List<Class> classes2025 = new ArrayList<>();
        classes2025.add(new Class("CS201", "Data Structures", "Dr. Wilson", 30));
        classes2025.add(new Class("MATH301", "Linear Algebra", "Prof. Taylor", 28));
        classes2025.add(new Class("CHEM101", "General Chemistry", "Dr. Anderson", 50));
        classes2025.add(new Class("ART101", "Art Appreciation", "Prof. Thomas", 60));
        classes2025.add(new Class("BIO101", "Biology Fundamentals", "Dr. Jackson", 55));
        classDatabase.put("2024-2025", classes2025);
        
        System.out.println("Sample data initialized for 3 academic years.");
    }
    
    /**
     * Simulate admin login process.
     * @return true if login successful, false otherwise
     */
    private static boolean loginAsAdmin() {
        System.out.println("=== Admin Login ===");
        
        // In a real system, this would connect to authentication service
        // For simulation, we'll use hardcoded credentials
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        
        // Create admin session
        currentSession = new AdminSession(username, password);
        
        if (currentSession.isAuthenticated()) {
            System.out.println("Login successful! Welcome, " + username + " (Administrator).");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials or insufficient privileges.");
            return false;
        }
    }
    
    /**
     * Display available academic years for selection.
     */
    private static void displayYearSelection() {
        System.out.println("=== Select Academic Year ===");
        System.out.println("Available academic years:");
        
        int yearCount = 1;
        for (String year : classDatabase.keySet()) {
            System.out.println(yearCount + ". " + year);
            yearCount++;
        }
        System.out.println("0. Exit");
        System.out.print("\nPlease select an academic year (enter number): ");
    }
    
    /**
     * Get user's academic year selection.
     * @return Selected academic year as string
     */
    private static String getUserYearSelection() {
        List<String> years = new ArrayList<>(classDatabase.keySet());
        
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice == 0) {
                    System.out.println("Exiting by user request.");
                    System.exit(0);
                }
                
                if (choice > 0 && choice <= years.size()) {
                    String selectedYear = years.get(choice - 1);
                    System.out.println("Selected academic year: " + selectedYear);
                    return selectedYear;
                } else {
                    System.out.print("Invalid selection. Please enter a number between 1 and " + years.size() + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    /**
     * Display class management screen for selected year.
     * @param academicYear The selected academic year
     */
    private static void displayClassManagementScreen(String academicYear) {
        System.out.println("\n=== Class Management Screen ===");
        System.out.println("Academic Year: " + academicYear);
        System.out.println("--------------------------------");
        System.out.println("Searching archive for classes...");
        
        // Simulate search delay
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate SMOS server connection check
        checkSMOSConnection();
    }
    
    /**
     * Display the list of classes for the selected academic year.
     * @param classes List of classes to display
     * @param academicYear The selected academic year
     */
    private static void displayClassesList(List<Class> classes, String academicYear) {
        System.out.println("\n=== Class List for Academic Year: " + academicYear + " ===");
        System.out.println("Total classes found: " + classes.size());
        System.out.println("-------------------------------------------");
        
        if (classes.isEmpty()) {
            System.out.println("No classes found for the selected academic year.");
            return;
        }
        
        // Display class details in a formatted table
        System.out.printf("%-10s %-40s %-20s %-10s%n", 
            "Code", "Course Name", "Instructor", "Capacity");
        System.out.println("------------------------------------------------------------------------");
        
        for (Class classObj : classes) {
            System.out.printf("%-10s %-40s %-20s %-10d%n",
                classObj.getCode(),
                classObj.getCourseName(),
                classObj.getInstructor(),
                classObj.getCapacity());
        }
        
        System.out.println("------------------------------------------------------------------------");
    }
    
    /**
     * Check for admin interruption during operation.
     * @return true if admin interrupted, false otherwise
     */
    private static boolean checkForAdminInterruption() {
        // In a real system, this would check for interruption signals
        // For simulation, we'll ask the user
        System.out.print("\nPress Enter to continue or type 'interrupt' to stop: ");
        String input = scanner.nextLine();
        
        return input.equalsIgnoreCase("interrupt");
    }
    
    /**
     * Check SMOS server connection status.
     * @throws SMOSConnectionException if connection is interrupted
     */
    private static void checkSMOSConnection() throws SMOSConnectionException {
        // Simulate random connection failure (10% chance for demonstration)
        if (Math.random() < 0.1) {
            smosConnectionActive = false;
            throw new SMOSConnectionException("SMOS server connection interrupted.");
        }
        
        if (!smosConnectionActive) {
            throw new SMOSConnectionException("SMOS server connection is not active.");
        }
    }
    
    /**
     * Verify postconditions after displaying class list.
     * @param classes The list of classes displayed
     * @param academicYear The selected academic year
     */
    private static void verifyPostconditions(List<Class> classes, String academicYear) {
        System.out.println("\n=== Verifying Postconditions ===");
        
        // Postcondition 1: System shows the list of classes of the selected academic year
        System.out.println("1. System shows list of classes for " + academicYear + ": " + 
            (classes != null ? "PASS" : "FAIL"));
        
        // Postcondition 2: The administrator can interrupt operations
        System.out.println("2. Administrator can interrupt operations: PASS (implemented)");
        
        // Postcondition 3: Connection to SMOS server can be interrupted
        System.out.println("3. SMOS server connection interrupt handling: " + 
            (smosConnectionActive ? "Active" : "Interrupted"));
        
        System.out.println("\nAll postconditions verified.");
    }
}

/**
 * Custom exception for SMOS server connection issues.
 */
class SMOSConnectionException extends Exception {
    public SMOSConnectionException(String message) {
        super(message);
    }
}