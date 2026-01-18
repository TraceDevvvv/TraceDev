/**
 * Main application for the DeleteOfOnePage use case.
 * This program simulates an administrator deleting a report card from a system.
 * It handles login, report card display, deletion confirmation, and postconditions.
 */
import java.util.*;
public class DeleteOfOnePage {
    // Inner class for administrator authentication
    static class AdminAuthenticator {
        /**
         * Simulates administrator login.
         * @param username The administrator username.
         * @param password The administrator password.
         * @return true if credentials are valid, false otherwise.
         */
        public static boolean login(String username, String password) {
            // For demonstration, accept a hardcoded admin credential.
            return "admin".equals(username) && "admin123".equals(password);
        }
        /**
         * Simulates administrator logout.
         */
        public static void logout() {
            System.out.println("Administrator logged out.");
        }
    }
    // Inner class representing a student report card
    static class ReportCard {
        private int studentId;
        private String studentName;
        private String className;
        private Map<String, Double> grades; // Subject -> Grade
        /**
         * Constructs a ReportCard with specified details.
         * @param studentId Unique identifier for the student.
         * @param studentName Name of the student.
         * @param className Name of the class.
         * @param grades Map of subjects and corresponding grades.
         */
        public ReportCard(int studentId, String studentName, String className, Map<String, Double> grades) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.className = className;
            this.grades = new HashMap<>(grades);
        }
        // Getters
        public int getStudentId() { return studentId; }
        public String getStudentName() { return studentName; }
        public String getClassName() { return className; }
        public Map<String, Double> getGrades() { return new HashMap<>(grades); }
        /**
         * Returns a string representation of the report card.
         * @return Formatted string with student and grade details.
         */
        @Override
        public String toString() {
            return String.format("ReportCard{studentId=%d, studentName='%s', className='%s', grades=%s}",
                                studentId, studentName, className, grades);
        }
    }
    // Inner class for managing report cards
    static class ReportCardManager {
        private Map<Integer, ReportCard> reportCards; // studentId -> ReportCard
        private static ReportCardManager instance;
        /**
         * Private constructor to enforce singleton pattern.
         * Initializes with sample report cards.
         */
        private ReportCardManager() {
            reportCards = new HashMap<>();
            // Sample data for demonstration
            addSampleData();
        }
        /**
         * Returns the singleton instance of ReportCardManager.
         * @return The singleton instance.
         */
        public static synchronized ReportCardManager getInstance() {
            if (instance == null) {
                instance = new ReportCardManager();
            }
            return instance;
        }
        /**
         * Adds sample report cards to the manager for demonstration.
         */
        private void addSampleData() {
            Map<String, Double> grades1 = new HashMap<>();
            grades1.put("Math", 95.5);
            grades1.put("Science", 88.0);
            grades1.put("History", 92.0);
            reportCards.put(101, new ReportCard(101, "Alice Johnson", "Class 10A", grades1));
            Map<String, Double> grades2 = new HashMap<>();
            grades2.put("Math", 78.0);
            grades2.put("Science", 85.5);
            grades2.put("History", 90.0);
            reportCards.put(102, new ReportCard(102, "Bob Smith", "Class 10B", grades2));
            Map<String, Double> grades3 = new HashMap<>();
            grades3.put("Math", 88.5);
            grades3.put("Science", 92.0);
            grades3.put("History", 87.5);
            reportCards.put(103, new ReportCard(103, "Charlie Brown", "Class 10A", grades3));
        }
        /**
         * Displays a specific report card by student ID.
         * @param studentId The ID of the student whose report card to display.
         * @return The ReportCard object if found, null otherwise.
         */
        public ReportCard displayReportCard(int studentId) {
            return reportCards.get(studentId);
        }
        /**
         * Deletes a report card from the system.
         * @param studentId The ID of the student whose report card to delete.
         * @return true if deletion was successful, false if report card not found.
         */
        public boolean deleteReportCard(int studentId) {
            if (reportCards.containsKey(studentId)) {
                reportCards.remove(studentId);
                return true;
            }
            return false;
        }
        /**
         * Retrieves a list of all unique class names from report cards.
         * @return Set of class names.
         */
        public Set<String> getClassList() {
            Set<String> classes = new HashSet<>();
            for (ReportCard rc : reportCards.values()) {
                classes.add(rc.getClassName());
            }
            return classes;
        }
        /**
         * Retrieves all report cards for a given class.
         * @param className The name of the class.
         * @return List of ReportCard objects in the class.
         */
        public List<ReportCard> getReportCardsByClass(String className) {
            List<ReportCard> classReportCards = new ArrayList<>();
            for (ReportCard rc : reportCards.values()) {
                if (rc.getClassName().equals(className)) {
                    classReportCards.add(rc);
                }
            }
            return classReportCards;
        }
        /**
         * Lists all report cards in the system.
         */
        public void listAllReportCards() {
            if (reportCards.isEmpty()) {
                System.out.println("No report cards available.");
            } else {
                System.out.println("All Report Cards:");
                for (ReportCard rc : reportCards.values()) {
                    System.out.println(rc);
                }
            }
        }
    }
    // Inner class for SMOS server connection simulation
    static class SMOSServerConnector {
        private boolean connected;
        /**
         * Constructs an SMOSServerConnector with initial disconnected state.
         */
        public SMOSServerConnector() {
            this.connected = false;
        }
        /**
         * Simulates connecting to the SMOS server.
         * @return true if connection is successful, false otherwise.
         */
        public boolean connect() {
            if (!connected) {
                connected = true;
                System.out.println("Connected to SMOS server.");
                return true;
            }
            System.out.println("Already connected to SMOS server.");
            return false;
        }
        /**
         * Simulates disconnecting from the SMOS server.
         * @return true if disconnection is successful, false otherwise.
         */
        public boolean disconnect() {
            if (connected) {
                connected = false;
                System.out.println("Disconnected from SMOS server.");
                return true;
            }
            System.out.println("Already disconnected from SMOS server.");
            return false;
        }
        /**
         * Checks the current connection status.
         * @return true if connected, false otherwise.
         */
        public boolean isConnected() {
            return connected;
        }
    }
    /**
     * Main method - entry point of the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportCardManager reportCardManager = ReportCardManager.getInstance();
        SMOSServerConnector serverConnector = new SMOSServerConnector();
        System.out.println("=== Report Card Deletion System ===\n");
        // Precondition: SMOS server connection is already established as part of system initialization
        System.out.println("System Status: SMOS server connection established.");
        serverConnector.connect(); // Simulate existing connection as per preconditions
        // Precondition: Administrator is already logged in (simulate active session)
        System.out.println("Administrator session active.");
        System.out.println("(Note: The administrator is already logged in as per preconditions.)\n");
        // Step: Display available report cards for selection
        displayReportCards(reportCardManager);
        // Get student ID for deletion
        System.out.print("Enter Student ID to delete report card (or 0 to exit): ");
        int studentId = 0;
        try {
            studentId = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a numeric Student ID.");
            logoutAndExit(scanner, serverConnector);
            return;
        }
        scanner.nextLine(); // consume newline
        if (studentId == 0) {
            logoutAndExit(scanner, serverConnector);
            return;
        }
        // Precondition: Display the report card (simulating "Displaying a report card" case)
        ReportCard reportCard = reportCardManager.displayReportCard(studentId);
        if (reportCard == null) {
            System.out.println("Error: Report card not found for Student ID " + studentId);
            logoutAndExit(scanner, serverConnector);
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
                displayClassList(reportCardManager);
            } else {
                System.out.println("\n\u2717 ERROR: Failed to delete report card.");
            }
        } else if (confirmation.equals("no")) {
            System.out.println("\nDeletion cancelled by user.");
        } else {
            System.out.println("\nInvalid input. Deletion cancelled.");
        }
        logoutAndExit(scanner, serverConnector);
    }
    /**
     * Displays all report cards in the system for selection.
     * @param reportCardManager The report card manager instance.
     */
    private static void displayReportCards(ReportCardManager reportCardManager) {
        System.out.println("=== Available Report Cards ===");
        reportCardManager.listAllReportCards();
        System.out.println();
    }
    /**
     * Displays the list of classes after deletion.
     * @param reportCardManager The report card manager instance.
     */
    private static void displayClassList(ReportCardManager reportCardManager) {
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
     * @param scanner Scanner to close.
     * @param serverConnector SMOS server connector to disconnect.
     */
    private static void logoutAndExit(Scanner scanner, SMOSServerConnector serverConnector) {
        // Ensure SMOS server is disconnected (if still connected)
        if (serverConnector.isConnected()) {
            serverConnector.disconnect();
        }
        System.out.println("\n=== Application terminated ===");
        scanner.close();
        System.exit(0);
    }
}