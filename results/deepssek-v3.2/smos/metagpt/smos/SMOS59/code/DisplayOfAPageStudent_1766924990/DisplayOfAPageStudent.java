import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for DisplayOfAPageStudent use case.
 * Simulates the student report display system with console interface.
 * Handles the sequence: user login -> request online report -> view reports -> select report -> view details.
 * Includes handling for SMOS server interruption as mentioned in postconditions.
 */
public class DisplayOfAPageStudent {
    
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Student Report Display System ===");
        
        // Precondition: User has been logged in as a student
        // In this simulation, we assume the user is already logged in
        
        try {
            // Simulate the flow based on the use case
            runStudentReportSystem();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cleanup resources
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Main system flow for the student report display use case.
     * Follows the sequence described in the use case:
     * 1. System displays student's reports from archive (Event sequence step 1)
     * 2. User selects a report card (Event sequence step 2)
     * 3. System displays details of the selected report card (Event sequence step 3)
     */
    private static void runStudentReportSystem() {
        System.out.println("\nUser is logged in as a student.");
        System.out.println("User clicks on 'Online report'...");
        
        // Step 1: The system displays the student's reports from the archive
        List<Report> studentReports = fetchStudentReportsFromArchive();
        
        if (studentReports.isEmpty()) {
            System.out.println("No reports found in the archive.");
            return;
        }
        
        displayReportsList(studentReports);
        
        // Step 2: User selects a report card
        Report selectedReport = selectReportCard(studentReports);
        
        if (selectedReport == null) {
            System.out.println("No report selected. Exiting system.");
            return;
        }
        
        // Step 3: System displays details of the selected report card
        displayReportDetails(selectedReport);
        
        // Postcondition: Connection to SMOS server interrupted
        System.out.println("\n[Postcondition Notice: Connection to SMOS server interrupted]");
    }
    
    /**
     * Fetches student reports from archive (simulated).
     * In a real system, this would connect to a database or service.
     * 
     * @return List of Report objects for the logged-in student
     */
    private static List<Report> fetchStudentReportsFromArchive() {
        System.out.println("\nFetching student reports from archive...");
        
        // Simulate potential server interruption
        if (shouldSimulateServerInterruption()) {
            throw new RuntimeException("Connection to SMOS server interrupted while fetching reports");
        }
        
        // Create sample reports for demonstration
        List<Report> reports = new ArrayList<>();
        
        // Sample data - in real system this would come from database
        reports.add(new Report("2023 Fall Semester", "Fall 2023", 85.5, "Good performance overall", 
            Map.of("Mathematics", 88.0, "Physics", 82.0, "Chemistry", 87.0)));
        reports.add(new Report("2023 Spring Semester", "Spring 2023", 78.5, "Average performance", 
            Map.of("Mathematics", 75.0, "Physics", 80.0, "English", 81.0)));
        reports.add(new Report("2022 Fall Semester", "Fall 2022", 92.0, "Excellent performance", 
            Map.of("Mathematics", 95.0, "Physics", 91.0, "Chemistry", 90.0)));
        
        return reports;
    }
    
    /**
     * Displays the list of available reports to the user.
     * 
     * @param reports List of Report objects to display
     */
    private static void displayReportsList(List<Report> reports) {
        System.out.println("\n=== Available Reports ===");
        System.out.println("Select a report card by entering its number:");
        
        for (int i = 0; i < reports.size(); i++) {
            Report report = reports.get(i);
            System.out.printf("%d. %s - %s (Overall: %.1f)%n", 
                i + 1, report.getTitle(), report.getSemester(), report.getOverallGrade());
        }
        System.out.println("0. Exit");
    }
    
    /**
     * Handles user selection of a report card.
     * 
     * @param reports List of available Report objects
     * @return Selected Report object, or null if user chooses to exit
     */
    private static Report selectReportCard(List<Report> reports) {
        System.out.print("\nEnter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (choice == 0) {
                return null;
            }
            
            if (choice < 1 || choice > reports.size()) {
                System.out.println("Invalid choice. Please select a valid report number.");
                // Try again recursively
                return selectReportCard(reports);
            }
            
            return reports.get(choice - 1);
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
            return selectReportCard(reports);
        }
    }
    
    /**
     * Displays detailed information for a selected report.
     * 
     * @param report The Report object to display
     */
    private static void displayReportDetails(Report report) {
        System.out.println("\n=== Report Details ===");
        System.out.println("Title: " + report.getTitle());
        System.out.println("Semester: " + report.getSemester());
        System.out.printf("Overall Grade: %.1f%n", report.getOverallGrade());
        System.out.println("Comments: " + report.getComments());
        
        System.out.println("\n--- Subject Grades ---");
        Map<String, Double> subjectGrades = report.getSubjectGrades();
        if (subjectGrades != null && !subjectGrades.isEmpty()) {
            for (Map.Entry<String, Double> entry : subjectGrades.entrySet()) {
                System.out.printf("  %s: %.1f%n", entry.getKey(), entry.getValue());
            }
        } else {
            System.out.println("  No subject grades available.");
        }
        
        System.out.println("\n--- Report Summary ---");
        System.out.println("Generated on: " + report.getGenerationDate());
        System.out.println("Report ID: " + report.getReportId());
    }
    
    /**
     * Simulates random server interruption (for demonstration purposes).
     * In a real system, this would be an actual network/server check.
     * 
     * @return true if server interruption should be simulated (10% chance for demo)
     */
    private static boolean shouldSimulateServerInterruption() {
        // For demonstration, give a 10% chance of server interruption
        // In production, this would be based on actual network/connection status
        return Math.random() < 0.1;
    }
}