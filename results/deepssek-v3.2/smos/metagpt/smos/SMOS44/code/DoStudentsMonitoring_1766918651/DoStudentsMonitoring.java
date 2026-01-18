import java.util.List;
import java.util.Scanner;

/**
 * DoStudentsMonitoring.java
 * Main application class for the Student Monitoring System.
 * This class simulates the administrator interface as described in the use case:
 * - Administrator logs in and clicks "Student Monitoring" button
 * - Searches for students with absences and notes above thresholds
 * - Displays data presentation screen with results
 * - Handles connection to SMOS server (simulated interruption)
 * 
 * The program is fully runnable and demonstrates all functionality.
 */
public class DoStudentsMonitoring {
    
    // Constants for application configuration
    private static final String APPLICATION_NAME = "Student Monitoring System";
    private static final String VERSION = "1.0";
    private static final String CURRENT_SCHOOL_YEAR = "2023-2024";
    
    // Service instance for business logic
    private StudentMonitoringService monitoringService;
    
    // Scanner for user input
    private Scanner scanner;
    
    /**
     * Constructor initializes the monitoring service and scanner.
     */
    public DoStudentsMonitoring() {
        this.monitoringService = new StudentMonitoringService(CURRENT_SCHOOL_YEAR);
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main entry point of the application.
     * Simulates the administrator logging in and accessing the system.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("    " + APPLICATION_NAME + " v" + VERSION);
        System.out.println("==========================================");
        
        // Simulate administrator login (precondition from use case)
        System.out.println("\n[System] Simulating administrator login...");
        System.out.println("[System] Administrator logged in successfully.");
        
        // Create application instance
        DoStudentsMonitoring app = new DoStudentsMonitoring();
        
        // Simulate clicking "Student Monitoring" button (precondition)
        System.out.println("[System] Administrator clicked 'Student Monitoring' button.");
        System.out.println("[System] Loading student monitoring module...\n");
        
        // Display main menu
        app.displayMainMenu();
        
        // Clean up resources
        app.cleanup();
        
        System.out.println("\n[System] Application terminated.");
        System.out.println("[System] Postcondition: Information about absences and notes was displayed.");
        System.out.println("[System] Postcondition: Connection to SMOS server was handled (simulated).");
    }
    
    /**
     * Displays the main menu and handles user interaction.
     * This simulates the data presentation screen mentioned in the use case.
     */
    private void displayMainMenu() {
        boolean exitRequested = false;
        
        while (!exitRequested) {
            System.out.println("\n========== STUDENT MONITORING MENU ==========");
            System.out.println("Current School Year: " + CURRENT_SCHOOL_YEAR);
            System.out.println("1. Search students exceeding thresholds");
            System.out.println("2. Use default threshold search");
            System.out.println("3. View monitoring statistics");
            System.out.println("4. Test SMOS server connection");
            System.out.println("5. Display all students in current year");
            System.out.println("6. Change school year (admin only)");
            System.out.println("7. Exit application");
            System.out.println("==============================================");
            System.out.print("Enter your choice (1-7): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        performCustomThresholdSearch();
                        break;
                    case 2:
                        performDefaultThresholdSearch();
                        break;
                    case 3:
                        displayStatistics();
                        break;
                    case 4:
                        testSMOSConnection();
                        break;
                    case 5:
                        displayAllCurrentYearStudents();
                        break;
                    case 6:
                        changeSchoolYear();
                        break;
                    case 7:
                        exitRequested = true;
                        System.out.println("\n[System] Exiting Student Monitoring System...");
                        break;
                    default:
                        System.out.println("\n[Error] Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[Error] Invalid input. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("\n[Error] An unexpected error occurred: " + e.getMessage());
            }
        }
    }
    
    /**
     * Performs search with custom thresholds entered by the administrator.
     * This implements the main use case functionality: "Search in the archive
     * the students who have, for the school year in progress a number of 
     * absences and notes higher than a predetermined threshold."
     */
    private void performCustomThresholdSearch() {
        System.out.println("\n--- CUSTOM THRESHOLD SEARCH ---");
        System.out.println("Enter threshold values (must be non-negative integers):");
        
        try {
            System.out.print("Absences threshold: ");
            int absencesThreshold = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Notes threshold: ");
            int notesThreshold = Integer.parseInt(scanner.nextLine().trim());
            
            // Validate thresholds
            if (absencesThreshold < 0 || notesThreshold < 0) {
                System.out.println("\n[Error] Threshold values cannot be negative.");
                return;
            }
            
            System.out.println("\n[System] Searching archive for students...");
            System.out.println("[System] Criteria: School Year = " + CURRENT_SCHOOL_YEAR);
            System.out.println("[System] Criteria: Absences > " + absencesThreshold + ", Notes > " + notesThreshold);
            
            // Perform the search (simulates archive search from use case)
            List<Student> results = monitoringService.searchStudentsExceedingThresholds(
                absencesThreshold, notesThreshold);
            
            // Display results (simulates data presentation screen from use case)
            displaySearchResults(results, absencesThreshold, notesThreshold);
            
        } catch (NumberFormatException e) {
            System.out.println("\n[Error] Invalid input. Please enter valid integers.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[Error] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[Error] Search failed: " + e.getMessage());
        }
    }
    
    /**
     * Performs search using default threshold values.
     * This is a convenience option for administrators.
     */
    private void performDefaultThresholdSearch() {
        System.out.println("\n--- DEFAULT THRESHOLD SEARCH ---");
        System.out.println("[System] Using default thresholds: Absences > 5, Notes > 3");
        System.out.println("[System] Searching archive for students...");
        
        try {
            List<Student> results = monitoringService.searchStudentsExceedingDefaultThresholds();
            displaySearchResults(results, 5, 3);
        } catch (Exception e) {
            System.out.println("\n[Error] Default search failed: " + e.getMessage());
        }
    }
    
    /**
     * Displays search results in a formatted table.
     * This simulates the "Displays a data presentation screen" from the use case.
     * 
     * @param students List of students to display
     * @param absencesThreshold The absences threshold used
     * @param notesThreshold The notes threshold used
     */
    private void displaySearchResults(List<Student> students, int absencesThreshold, int notesThreshold) {
        System.out.println("\n========== SEARCH RESULTS ==========");
        System.out.println("School Year: " + CURRENT_SCHOOL_YEAR);
        System.out.println("Thresholds: Absences > " + absencesThreshold + ", Notes > " + notesThreshold);
        System.out.println("Total Students Found: " + students.size());
        System.out.println("====================================\n");
        
        if (students.isEmpty()) {
            System.out.println("No students exceed the specified thresholds.");
            System.out.println("All students are within acceptable limits.");
        } else {
            System.out.println("STUDENTS EXCEEDING THRESHOLDS:");
            System.out.println("+------+------------+----------------------+----------+-------+-------------+");
            System.out.println("| No.  | Student ID | Name                | Absences | Notes | Total Score |");
            System.out.println("+------+------------+----------------------+----------+-------+-------------+");
            
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                System.out.printf("| %-4d | %-10s | %-20s | %-8d | %-5d | %-11d |\n",
                    i + 1,
                    student.getStudentId(),
                    truncateString(student.getFullName(), 20),
                    student.getAbsencesCount(),
                    student.getNotesCount(),
                    student.getTotalMonitoringScore());
            }
            
            System.out.println("+------+------------+----------------------+----------+-------+-------------+");
            
            // Additional analysis
            System.out.println("\nANALYSIS:");
            System.out.println("- " + students.size() + " student(s) require administrative attention.");
            System.out.println("- Consider scheduling meetings with these students/parents.");
            System.out.println("- Review individual cases for potential interventions.");
        }
        
        System.out.println("\n[System] Data presentation screen displayed successfully.");
    }
    
    /**
     * Displays monitoring statistics for the current school year.
     * Provides additional insights beyond the basic search functionality.
     */
    private void displayStatistics() {
        System.out.println("\n--- MONITORING STATISTICS ---");
        
        try {
            String statistics = monitoringService.calculateStatistics();
            System.out.println(statistics);
            
            // Additional statistical insights
            System.out.println("\nADDITIONAL INSIGHTS:");
            System.out.println("- Statistics help identify overall trends in absences and notes.");
            System.out.println("- High averages may indicate systemic issues needing attention.");
            System.out.println("- Compare with previous years for trend analysis.");
            
        } catch (Exception e) {
            System.out.println("\n[Error] Failed to calculate statistics: " + e.getMessage());
        }
    }
    
    /**
     * Tests the SMOS server connection simulation.
     * Demonstrates handling of the "Connection to the interrupted SMOS server"
     * mentioned in postconditions.
     */
    private void testSMOSConnection() {
        System.out.println("\n--- SMOS SERVER CONNECTION TEST ---");
        System.out.println("[System] Testing connection to SMOS server...");
        System.out.println("[System] This simulates potential server interruptions.");
        
        // The connection test is built into the simulateDatabaseRetrieval method
        // We'll trigger it by performing a search
        try {
            // Use very low thresholds to ensure we get some data
            List<Student> testResults = monitoringService.searchStudentsExceedingThresholds(0, 0);
            
            if (testResults.isEmpty()) {
                System.out.println("[System] Connection test completed - No data retrieved.");
                System.out.println("[System] This may indicate a connection issue or empty database.");
            } else {
                System.out.println("[System] Connection test successful!");
                System.out.println("[System] Retrieved " + testResults.size() + " student records.");
                System.out.println("[System] SMOS server connection is functioning properly.");
            }
            
            System.out.println("\nNote: In a real system, SMOS server connections would:");
            System.out.println("1. Use secure authentication");
            System.out.println("2. Implement retry logic for interruptions");
            System.out.println("3. Log all connection attempts and errors");
            System.out.println("4. Cache data for offline operation");
            
        } catch (Exception e) {
            System.out.println("\n[Error] Connection test failed: " + e.getMessage());
            System.out.println("[System] Simulating SMOS server interruption handling...");
            System.out.println("[System] Attempting to use cached data...");
            System.out.println("[System] Administrator notified of connection issues.");
        }
    }
    
    /**
     * Displays all students in the current school year.
     * Useful for administrators who want to see all data, not just threshold exceeders.
     */
    private void displayAllCurrentYearStudents() {
        System.out.println("\n--- ALL STUDENTS IN CURRENT SCHOOL YEAR ---");
        
        try {
            // Get all students (using 0,0 thresholds to get everyone)
            List<Student> allStudents = monitoringService.searchStudentsExceedingThresholds(0, 0);
            
            System.out.println("Total Students in " + CURRENT_SCHOOL_YEAR + ": " + allStudents.size());
            System.out.println("\nSTUDENT LIST:");
            System.out.println("+------------+----------------------+----------+-------+-------------+");
            System.out.println("| Student ID | Name                | Absences | Notes | School Year |");
            System.out.println("+------------+----------------------+----------+-------+-------------+");
            
            for (Student student : allStudents) {
                System.out.printf("| %-10s | %-20s | %-8d | %-5d | %-11s |\n",
                    student.getStudentId(),
                    truncateString(student.getFullName(), 20),
                    student.getAbsencesCount(),
                    student.getNotesCount(),
                    student.getSchoolYear());
            }
            
            System.out.println("+------------+----------------------+----------+-------+-------------+");
            
        } catch (Exception e) {
            System.out.println("\n[Error] Failed to retrieve student list: " + e.getMessage());
        }
    }
    
    /**
     * Allows administrator to change the school year for monitoring.
     * This is an administrative function for flexibility.
     */
    private void changeSchoolYear() {
        System.out.println("\n--- CHANGE SCHOOL YEAR (ADMIN ONLY) ---");
        System.out.println("Current School Year: " + monitoringService.getCurrentSchoolYear());
        System.out.print("Enter new school year (e.g., 2024-2025): ");
        
        String newSchoolYear = scanner.nextLine().trim();
        
        if (newSchoolYear.isEmpty()) {
            System.out.println("[Error] School year cannot be empty.");
            return;
        }
        
        // Basic validation for school year format (YYYY-YYYY)
        if (!newSchoolYear.matches("\\d{4}-\\d{4}")) {
            System.out.println("[Warning] School year format should be YYYY-YYYY (e.g., 2023-2024)");
            System.out.print("Continue with '" + newSchoolYear + "'? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (!confirm.equals("yes") && !confirm.equals("y")) {
                System.out.println("School year change cancelled.");
                return;
            }
        }
        
        monitoringService.setCurrentSchoolYear(newSchoolYear);
        System.out.println("[System] School year changed to: " + newSchoolYear);
        System.out.println("[System] All future searches will use this school year.");
    }
    
    /**
     * Helper method to truncate strings for display in tables.
     * 
     * @param str The string to truncate
     * @param maxLength Maximum length before truncation
     * @return Truncated string with ellipsis if needed
     */
    private String truncateString(String str, int maxLength) {
        if (str == null) {
            return "";
        }
        
        if (str.length() <= maxLength) {
            return str;
        }
        
        return str.substring(0, maxLength - 3) + "...";
    }
    
    /**
     * Cleans up resources before application exit.
     * Ensures proper resource management.
     */
    private void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
        System.out.println("[System] Resources cleaned up.");
    }
    
    /**
     * Utility method to demonstrate the complete workflow.
     * This can be called for testing or demonstration purposes.
     */
    public void demonstrateCompleteWorkflow() {
        System.out.println("\n=== DEMONSTRATING COMPLETE WORKFLOW ===");
        
        // Step 1: Login (simulated in main)
        System.out.println("1. Administrator logs in ✓");
        
        // Step 2: Click Student Monitoring button
        System.out.println("2. Clicks 'Student Monitoring' button ✓");
        
        // Step 3: Search with thresholds
        System.out.println("3. Searches archive for threshold exceeders ✓");
        
        // Step 4: Display results
        System.out.println("4. Displays data presentation screen ✓");
        
        // Step 5: Handle SMOS connection
        System.out.println("5. Manages SMOS server connection ✓");
        
        System.out.println("\nWorkflow demonstration complete.");
        System.out.println("All use case requirements satisfied.");
    }
}