/**
 * Main application to run the report card deletion use case.
 * Implements the full user interaction flow as per the use case.
 * The program simulates administrator login, report card display, deletion confirmation, and post-conditions.
 */
import java.util.*;
public class DeleteReportCardApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ReportCardManager reportCardManager = ReportCardManager.getInstance();
    private static SMOSServerConnector serverConnector = new SMOSServerConnector();
    /**
     * Main method - entry point of the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("=== Report Card Deletion System ===\n");
        // Precondition: SMOS server connection is already established as part of system initialization
        System.out.println("System Status: SMOS server connection established.");
        serverConnector.connect(); // Simulate existing connection as per preconditions
        // Precondition: Administrator is already logged in (simulate active session)
        System.out.println("Administrator session active.");
        System.out.println("(Note: The administrator is already logged in as per preconditions.)\n");
        // Step: Display available report cards for selection
        displayReportCards();
        // Get student ID for deletion
        System.out.print("Enter Student ID to delete report card (or 0 to exit): ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (studentId == 0) {
            logoutAndExit();
            return;
        }
        // Precondition: Display the report card (simulating "Displaying a report card" case)
        ReportCard reportCard = reportCardManager.displayReportCard(studentId);
        if (reportCard == null) {
            System.out.println("Error: Report card not found for Student ID " + studentId);
            logoutAndExit();
            return;
        }
        System.out.println("\n--- Displaying Report Card ---");
        System.out.println("Student ID: " + reportCard.getStudentId());
        System.out.println("Student Name: " + reportCard.getStudentName());
        System.out.println("Class: " + reportCard.getClassName());
        System.out.println("Grades: " + reportCard.getGrades());
        System.out.println("--- End of Report Card ---\n");
        // Step: User clicks "Delete" button (simulated)
        System.out.println("Click 'Delete' button to proceed.");
        System.out.print("Press Enter to click Delete...");
        scanner.nextLine();
        // Event sequence: System displays confirmation form
        System.out.println("\n=== Confirmation Required ===");
        System.out.println("Are you sure you want to delete the report card for:");
        System.out.println("Student: " + reportCard.getStudentName() + " (ID: " + studentId + ")");
        System.out.println("This action cannot be undone.");
        System.out.print("Type 'yes' to confirm deletion, or 'no' to cancel: ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        // Event sequence: User accepts cancellation by pressing confirmation key
        if (confirmation.equals("yes")) {
            // Delete the report card
            boolean deleted = reportCardManager.deleteReportCard(studentId);
            if (deleted) {
                System.out.println("\n\u2713 SUCCESS: Report card deleted successfully.");
                // Postcondition: A report relative to a student is canceled
                System.out.println("Postcondition: Report for student " + reportCard.getStudentName() + " has been canceled.");
                // Postcondition: Connection to the interrupted SMOS server
                if (serverConnector.isConnected()) {
                    serverConnector.disconnect();
                    System.out.println("Postcondition: SMOS server connection interrupted.");
                }
                // Event sequence: Display form with list of classes
                displayClassList();
            } else {
                System.out.println("\n\u2717 ERROR: Failed to delete report card.");
            }
        } else {
            System.out.println("\nDeletion cancelled by user.");
        }
        logoutAndExit();
    }
    /**
     * Displays all report cards in the system for selection.
     */
    private static void displayReportCards() {
        System.out.println("=== Available Report Cards ===");
        reportCardManager.listAllReportCards();
        System.out.println();
    }
    /**
     * Displays the list of classes after deletion.
     */
    private static void displayClassList() {
        System.out.println("\n=== Form with List of Classes ===");
        Set<String> classes = reportCardManager.getClassList();
        if (classes.isEmpty()) {
            System.out.println("No classes available.");
        } else {
            System.out.println("Classes:");
            for (String className : classes) {
                System.out.println(" - " + className);
                // Optional: show report cards in each class
                List<ReportCard> classReportCards = reportCardManager.getReportCardsByClass(className);
                System.out.println("   Report Cards in this class: " + classReportCards.size());
                for (ReportCard rc : classReportCards) {
                    System.out.println("     " + rc.getStudentName() + " (ID: " + rc.getStudentId() + ")");
                }
            }
        }
        System.out.println();
    }
    /**
     * Logs out the administrator and exits the application.
     */
    private static void logoutAndExit() {
        // Ensure SMOS server is disconnected (if still connected)
        if (serverConnector.isConnected()) {
            serverConnector.disconnect();
        }
        System.out.println("\n=== Application terminated ===");
        scanner.close();
    }
}