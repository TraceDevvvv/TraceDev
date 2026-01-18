import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the DeleteOfOnePage use case.
 * Simulates deletion of a report card by an administrator.
 */
public class DeleteOfOnePage {
    
    /**
     * Represents a student's report card with grades for different subjects.
     */
    static class ReportCard {
        private String studentId;
        private String studentName;
        private Map<String, Integer> grades; // Subject -> Grade
        
        public ReportCard(String studentId, String studentName) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.grades = new HashMap<>();
        }
        
        public void addGrade(String subject, int grade) {
            grades.put(subject, grade);
        }
        
        public String getStudentId() {
            return studentId;
        }
        
        public String getStudentName() {
            return studentName;
        }
        
        public Map<String, Integer> getGrades() {
            return new HashMap<>(grades);
        }
        
        @Override
        public String toString() {
            return "ReportCard{" +
                   "studentId='" + studentId + '\'' +
                   ", studentName='" + studentName + '\'' +
                   ", grades=" + grades +
                   '}';
        }
    }
    
    /**
     * Represents a class with multiple students and their report cards.
     */
    static class SchoolClass {
        private String className;
        private List<ReportCard> reportCards;
        
        public SchoolClass(String className) {
            this.className = className;
            this.reportCards = new ArrayList<>();
        }
        
        public void addReportCard(ReportCard reportCard) {
            reportCards.add(reportCard);
        }
        
        public boolean removeReportCard(String studentId) {
            return reportCards.removeIf(rc -> rc.getStudentId().equals(studentId));
        }
        
        public List<ReportCard> getReportCards() {
            return new ArrayList<>(reportCards);
        }
        
        public String getClassName() {
            return className;
        }
        
        @Override
        public String toString() {
            return "Class: " + className + " (Students: " + reportCards.size() + ")";
        }
    }
    
    /**
     * Simulates connection to SMOS server.
     * In a real system, this would handle actual server communication.
     */
    static class SMOSServerConnection {
        private boolean connected;
        
        public SMOSServerConnection() {
            this.connected = false;
        }
        
        /**
         * Simulates connecting to SMOS server.
         * @return true if connection successful, false otherwise
         */
        public boolean connect() {
            System.out.println("Connecting to SMOS server...");
            // Simulate connection attempt
            connected = true;
            System.out.println("Connected to SMOS server successfully.");
            return connected;
        }
        
        /**
         * Simulates disconnecting from SMOS server.
         */
        public void disconnect() {
            if (connected) {
                System.out.println("Disconnecting from SMOS server...");
                connected = false;
                System.out.println("Disconnected from SMOS server.");
            }
        }
        
        /**
         * Simulates server interruption as mentioned in postconditions.
         */
        public void interruptConnection() {
            System.out.println("SMOS server connection interrupted (as per postconditions).");
            connected = false;
        }
        
        public boolean isConnected() {
            return connected;
        }
    }
    
    /**
     * Main system class that handles the deletion process.
     */
    static class ReportCardSystem {
        private List<SchoolClass> classes;
        private SMOSServerConnection serverConnection;
        private Scanner scanner;
        private boolean administratorLoggedIn;
        
        public ReportCardSystem() {
            this.classes = new ArrayList<>();
            this.serverConnection = new SMOSServerConnection();
            this.scanner = new Scanner(System.in);
            this.administratorLoggedIn = false;
            initializeSampleData();
        }
        
        /**
         * Initialize sample data for demonstration.
         */
        private void initializeSampleData() {
            // Create sample class
            SchoolClass mathClass = new SchoolClass("Mathematics 101");
            
            // Create sample report cards
            ReportCard student1 = new ReportCard("S001", "John Doe");
            student1.addGrade("Algebra", 85);
            student1.addGrade("Geometry", 90);
            
            ReportCard student2 = new ReportCard("S002", "Jane Smith");
            student2.addGrade("Algebra", 92);
            student2.addGrade("Geometry", 88);
            
            mathClass.addReportCard(student1);
            mathClass.addReportCard(student2);
            
            classes.add(mathClass);
        }
        
        /**
         * Simulates administrator login.
         * @return true if login successful, false otherwise
         */
        public boolean administratorLogin() {
            System.out.println("Administrator login...");
            System.out.print("Enter administrator username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            // Simple authentication simulation
            if ("admin".equals(username) && "admin123".equals(password)) {
                administratorLoggedIn = true;
                System.out.println("Login successful. Welcome Administrator!");
                return true;
            } else {
                System.out.println("Login failed. Invalid credentials.");
                return false;
            }
        }
        
        /**
         * Displays all classes and their report cards.
         */
        public void displayClasses() {
            System.out.println("\n=== List of Classes ===");
            for (int i = 0; i < classes.size(); i++) {
                SchoolClass schoolClass = classes.get(i);
                System.out.println((i + 1) + ". " + schoolClass);
                
                List<ReportCard> reportCards = schoolClass.getReportCards();
                for (ReportCard rc : reportCards) {
                    System.out.println("   - " + rc.getStudentName() + " (ID: " + rc.getStudentId() + ")");
                }
            }
            System.out.println("=======================\n");
        }
        
        /**
         * Simulates displaying a specific report card (precondition).
         * @param studentId The ID of the student whose report card to display
         * @return The report card if found, null otherwise
         */
        public ReportCard displayReportCard(String studentId) {
            for (SchoolClass schoolClass : classes) {
                for (ReportCard rc : schoolClass.getReportCards()) {
                    if (rc.getStudentId().equals(studentId)) {
                        System.out.println("\n=== Displaying Report Card ===");
                        System.out.println("Student: " + rc.getStudentName());
                        System.out.println("ID: " + rc.getStudentId());
                        System.out.println("Grades:");
                        for (Map.Entry<String, Integer> entry : rc.getGrades().entrySet()) {
                            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
                        }
                        System.out.println("=============================\n");
                        return rc;
                    }
                }
            }
            System.out.println("Report card not found for student ID: " + studentId);
            return null;
        }
        
        /**
         * Main method to execute the deletion process.
         * Follows the use case flow: preconditions, event sequence, postconditions.
         */
        public void executeDeletionProcess() {
            System.out.println("=== DeleteOfOnePage Use Case ===");
            
            // Precondition 1: Administrator login
            if (!administratorLoggedIn) {
                if (!administratorLogin()) {
                    System.out.println("Cannot proceed without administrator login.");
                    return;
                }
            }
            
            // Precondition 2: Connect to SMOS server
            if (!serverConnection.connect()) {
                System.out.println("Failed to connect to SMOS server. Cannot proceed.");
                return;
            }
            
            // Display classes (part of the flow)
            displayClasses();
            
            // Get student ID for deletion
            System.out.print("Enter student ID to delete report card: ");
            String studentId = scanner.nextLine();
            
            // Precondition 3: Display the report card (user took the case of use "Displaying a report card")
            ReportCard reportCard = displayReportCard(studentId);
            if (reportCard == null) {
                System.out.println("Cannot proceed. Report card not found.");
                serverConnection.disconnect();
                return;
            }
            
            // Event sequence step 1: System displays confirmation form
            System.out.println("\n=== Confirm Deletion ===");
            System.out.println("Are you sure you want to delete the report card for:");
            System.out.println("Student: " + reportCard.getStudentName());
            System.out.println("ID: " + reportCard.getStudentId());
            System.out.print("Press 'Y' to confirm deletion, any other key to cancel: ");
            
            // Event sequence step 2: User accepts cancellation by pressing confirmation key
            String confirmation = scanner.nextLine();
            
            if ("Y".equalsIgnoreCase(confirmation)) {
                // Perform deletion
                boolean deleted = false;
                for (SchoolClass schoolClass : classes) {
                    if (schoolClass.removeReportCard(studentId)) {
                        deleted = true;
                        break;
                    }
                }
                
                if (deleted) {
                    // Event sequence step 3: Display success message
                    System.out.println("\n✓ Report card deleted successfully!");
                    
                    // Postcondition 1: Report relative to a student is canceled
                    System.out.println("✓ Report for student " + reportCard.getStudentName() + " has been canceled.");
                    
                    // Display updated list of classes
                    System.out.println("\nUpdated list of classes:");
                    displayClasses();
                } else {
                    System.out.println("\n✗ Failed to delete report card. It may have already been deleted.");
                }
            } else {
                System.out.println("\nDeletion cancelled by user.");
            }
            
            // Postcondition 2: Connection to the interrupted SMOS server
            serverConnection.interruptConnection();
            
            System.out.println("\n=== Process Complete ===");
        }
        
        /**
         * Clean up resources.
         */
        public void cleanup() {
            scanner.close();
            if (serverConnection.isConnected()) {
                serverConnection.disconnect();
            }
        }
    }
    
    /**
     * Main method to run the program.
     */
    public static void main(String[] args) {
        System.out.println("Starting Report Card Deletion System...\n");
        
        ReportCardSystem system = new ReportCardSystem();
        
        try {
            system.executeDeletionProcess();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            system.cleanup();
        }
        
        System.out.println("\nProgram terminated.");
    }
}