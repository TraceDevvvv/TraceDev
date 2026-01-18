import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the ViewListofJustifications use case.
 * This program simulates an administrator viewing a student's absences
 * with color coding: justified absences in green, absences to justify in red.
 */
public class ViewListofJustifications {
    
    /**
     * Represents a student absence record.
     */
    static class Absence {
        private LocalDate date;
        private String reason;
        private boolean justified;
        
        public Absence(LocalDate date, String reason, boolean justified) {
            this.date = date;
            this.reason = reason;
            this.justified = justified;
        }
        
        public LocalDate getDate() {
            return date;
        }
        
        public String getReason() {
            return reason;
        }
        
        public boolean isJustified() {
            return justified;
        }
        
        @Override
        public String toString() {
            return date + " - " + reason + " - " + (justified ? "Justified" : "To Justify");
        }
    }
    
    /**
     * Represents a student with their absences.
     */
    static class Student {
        private String id;
        private String name;
        private List<Absence> absences;
        
        public Student(String id, String name) {
            this.id = id;
            this.name = name;
            this.absences = new ArrayList<>();
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public List<Absence> getAbsences() {
            return absences;
        }
        
        public void addAbsence(Absence absence) {
            absences.add(absence);
        }
    }
    
    /**
     * Simulates the SMOS server connection.
     */
    static class SMOSServer {
        private boolean connected;
        
        public SMOSServer() {
            this.connected = true;
        }
        
        public boolean isConnected() {
            return connected;
        }
        
        /**
         * Simulates interrupting the connection to SMOS server.
         */
        public void interruptConnection() {
            this.connected = false;
            System.out.println("Connection to SMOS server interrupted.");
        }
        
        /**
         * Simulates fetching student data from SMOS server.
         */
        public Student fetchStudentData(String studentId) {
            if (!connected) {
                throw new IllegalStateException("Cannot fetch data: SMOS server connection interrupted");
            }
            
            // Simulate fetching student data
            Student student = new Student(studentId, "John Doe");
            
            // Add sample absences for the current year
            student.addAbsence(new Absence(LocalDate.of(2024, 1, 15), "Medical appointment", true));
            student.addAbsence(new Absence(LocalDate.of(2024, 2, 20), "Family emergency", true));
            student.addAbsence(new Absence(LocalDate.of(2024, 3, 10), "Unexplained absence", false));
            student.addAbsence(new Absence(LocalDate.of(2024, 4, 5), "Sports competition", true));
            student.addAbsence(new Absence(LocalDate.of(2024, 5, 12), "Unexplained absence", false));
            
            return student;
        }
    }
    
    /**
     * Authentication system to verify administrator login.
     */
    static class AuthenticationSystem {
        private boolean loggedIn;
        private boolean isAdministrator;
        
        public AuthenticationSystem() {
            this.loggedIn = false;
            this.isAdministrator = false;
        }
        
        /**
         * Simulates administrator login.
         * Precondition: User must be logged in as administrator.
         */
        public boolean loginAsAdministrator(String username, String password) {
            // Simplified authentication - in real system, this would validate credentials
            if ("admin".equals(username) && "admin123".equals(password)) {
                loggedIn = true;
                isAdministrator = true;
                System.out.println("Administrator login successful.");
                return true;
            }
            loggedIn = false;
            isAdministrator = false;
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
        
        public boolean isLoggedInAsAdministrator() {
            return loggedIn && isAdministrator;
        }
        
        public void logout() {
            loggedIn = false;
            isAdministrator = false;
            System.out.println("Logged out.");
        }
    }
    
    /**
     * Main application class that orchestrates the use case.
     */
    static class JustificationViewer {
        private AuthenticationSystem authSystem;
        private SMOSServer smosServer;
        private Scanner scanner;
        
        public JustificationViewer() {
            this.authSystem = new AuthenticationSystem();
            this.smosServer = new SMOSServer();
            this.scanner = new Scanner(System.in);
        }
        
        /**
         * Main method to execute the ViewListofJustifications use case.
         */
        public void executeUseCase() {
            try {
                // Step 1: Administrator login (precondition)
                if (!performAdministratorLogin()) {
                    System.out.println("Cannot proceed: Administrator authentication failed.");
                    return;
                }
                
                // Step 2: Simulate that user has carried out 'SplitTaTtAlTeloregistration' case
                // and clicked 'justification' button for a student
                System.out.println("\n=== SplitTaTtAlTeloregistration case completed ===");
                System.out.println("Justification button clicked for a student.");
                
                // Step 3: Get student ID (simulating selection from previous case)
                System.out.print("\nEnter student ID to view justifications: ");
                String studentId = scanner.nextLine();
                
                if (studentId == null || studentId.trim().isEmpty()) {
                    System.out.println("Error: Student ID cannot be empty.");
                    return;
                }
                
                // Step 4: Fetch student data and display absences
                displayStudentAbsences(studentId);
                
                // Step 5: Postcondition - interrupt SMOS server connection
                smosServer.interruptConnection();
                
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // Cleanup
                authSystem.logout();
                scanner.close();
            }
        }
        
        private boolean performAdministratorLogin() {
            System.out.println("=== Administrator Login ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            return authSystem.loginAsAdministrator(username, password);
        }
        
        /**
         * Displays all absences of the student with color coding.
         * Justified absences displayed in green, those to justify in red.
         */
        private void displayStudentAbsences(String studentId) {
            System.out.println("\n=== Viewing Justifications for Student: " + studentId + " ===");
            
            try {
                // Fetch student data from SMOS server
                Student student = smosServer.fetchStudentData(studentId);
                
                System.out.println("Student: " + student.getName() + " (ID: " + student.getId() + ")");
                System.out.println("\nAbsences during the year:");
                System.out.println("-------------------------");
                
                List<Absence> absences = student.getAbsences();
                
                if (absences.isEmpty()) {
                    System.out.println("No absences recorded for this student.");
                    return;
                }
                
                // Display each absence with color coding
                for (Absence absence : absences) {
                    String displayText = absence.toString();
                    
                    // Apply color coding based on justification status
                    if (absence.isJustified()) {
                        // Green for justified absences
                        System.out.println("\u001B[32m" + displayText + "\u001B[0m");
                    } else {
                        // Red for absences to justify
                        System.out.println("\u001B[31m" + displayText + "\u001B[0m");
                    }
                }
                
                // Display summary statistics
                long justifiedCount = absences.stream().filter(Absence::isJustified).count();
                long toJustifyCount = absences.size() - justifiedCount;
                
                System.out.println("\nSummary:");
                System.out.println("Total absences: " + absences.size());
                System.out.println("Justified absences: " + justifiedCount);
                System.out.println("Absences to justify: " + toJustifyCount);
                
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error fetching student data: " + e.getMessage());
            }
        }
    }
    
    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        System.out.println("=== View List of Justifications System ===");
        System.out.println("Use Case: ViewListofJustifications");
        System.out.println("Actor: Administrator\n");
        
        JustificationViewer viewer = new JustificationViewer();
        viewer.executeUseCase();
        
        System.out.println("\n=== Program completed ===");
    }
}