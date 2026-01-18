import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EnterAbsencesAdminSystem - Main class that simulates the complete use case flow
 * for administrator entering absences. This class demonstrates all steps from
 * login to data entry and email notification.
 * 
 * The system follows this sequence:
 * 1. Administrator logs in
 * 2. Selects a date (screen updates accordingly)
 * 3. Fills out form with absent/present students and saves
 * 4. Sends data to server (triggers email notifications)
 * 5. Displays updated log data
 * 6. Handles operation interruption and SMOS server reconnection
 */
public class EnterAbsencesAdminSystem {
    private static final Logger LOGGER = Logger.getLogger(EnterAbsencesAdminSystem.class.getName());
    
    private Administrator admin;
    private List<Student> sampleStudents;
    private Scanner scanner;
    
    /**
     * Constructor initializes the system with sample data.
     */
    public EnterAbsencesAdminSystem() {
        // Initialize scanner for user input
        scanner = new Scanner(System.in);
        
        // Create sample students for demonstration
        initializeSampleStudents();
        
        // Create administrator with credentials
        admin = new Administrator("admin", "password123");
        
        LOGGER.info("EnterAbsencesAdminSystem initialized");
    }
    
    /**
     * Initializes sample student data for demonstration.
     */
    private void initializeSampleStudents() {
        sampleStudents = new ArrayList<>();
        
        // Create sample students with realistic data
        sampleStudents.add(new Student("S001", "John Smith", "parent.smith@example.com"));
        sampleStudents.add(new Student("S002", "Emma Johnson", "emma.parent@example.com"));
        sampleStudents.add(new Student("S003", "Michael Brown", "mbrown.parent@example.com"));
        sampleStudents.add(new Student("S004", "Sarah Davis", "sdavis.parent@example.com"));
        sampleStudents.add(new Student("S005", "James Wilson", "jwilson.parent@example.com"));
        
        LOGGER.info("Initialized " + sampleStudents.size() + " sample students");
    }
    
    /**
     * Main method - entry point of the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Configure logging level
        Logger.getLogger("").setLevel(Level.INFO);
        
        LOGGER.info("=== EnterAbsencesAdmin System Starting ===");
        
        // Create and run the system
        EnterAbsencesAdminSystem system = new EnterAbsencesAdminSystem();
        system.run();
        
        LOGGER.info("=== EnterAbsencesAdmin System Completed ===");
    }
    
    /**
     * Main execution flow of the system.
     * Follows the use case sequence step by step.
     */
    public void run() {
        System.out.println("\n=== Welcome to EnterAbsencesAdmin System ===\n");
        
        // Step 0: Administrator login (precondition)
        if (!performLogin()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        
        // Step 1: Administrator selects date
        LocalDate selectedDate = selectDate();
        if (selectedDate == null) {
            System.out.println("Date selection cancelled. Exiting system.");
            admin.logout();
            return;
        }
        
        // Step 2: Update screen displayed according to the date
        updateScreenForDate(selectedDate);
        
        // Step 3: Fill out form with absent/present students and save
        List<Administrator.StudentAttendance> attendanceRecords = fillOutForm(selectedDate);
        if (attendanceRecords.isEmpty()) {
            System.out.println("No attendance data entered. Operation cancelled.");
            admin.logout();
            return;
        }
        
        // Save the data
        List<AbsenceRecord> savedRecords = saveAttendanceData(selectedDate, attendanceRecords);
        if (savedRecords.isEmpty()) {
            System.out.println("Failed to save attendance data.");
            admin.logout();
            return;
        }
        
        // Step 4: Send data to server (triggers email notifications)
        List<Student> notifiedStudents = sendDataToServer(selectedDate);
        
        // Step 5: Display updated log data
        displayUpdatedLogData(selectedDate);
        
        // Step 6: Demonstrate interruption handling (simulated)
        simulateInterruption();
        
        // Logout and clean up
        admin.logout();
        scanner.close();
        
        System.out.println("\n=== Operation Completed Successfully ===");
        System.out.println("Summary:");
        System.out.println("- Date: " + selectedDate);
        System.out.println("- Students processed: " + savedRecords.size());
        System.out.println("- Parents notified: " + notifiedStudents.size());
        System.out.println("- System remains on registry screen (simulated)");
    }
    
    /**
     * Performs administrator login.
     * 
     * @return true if login successful, false otherwise
     */
    private boolean performLogin() {
        System.out.println("=== Administrator Login ===");
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        
        boolean loginSuccess = admin.login(username, password);
        
        if (loginSuccess) {
            System.out.println("✓ Login successful! Welcome, " + username + ".");
            return true;
        } else {
            System.out.println("✗ Login failed. Invalid credentials.");
            return false;
        }
    }
    
    /**
     * Prompts administrator to select a date.
     * 
     * @return Selected LocalDate, or null if cancelled
     */
    private LocalDate selectDate() {
        System.out.println("\n=== Date Selection ===");
        System.out.println("Enter date for attendance records (YYYY-MM-DD):");
        System.out.println("Press Enter for today's date, or type 'cancel' to exit.");
        
        String input = scanner.nextLine().trim();
        
        if (input.equalsIgnoreCase("cancel")) {
            return null;
        }
        
        LocalDate selectedDate;
        if (input.isEmpty()) {
            selectedDate = LocalDate.now();
            System.out.println("Using today's date: " + selectedDate);
        } else {
            try {
                selectedDate = LocalDate.parse(input);
                System.out.println("Selected date: " + selectedDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                System.out.println("Using today's date instead: " + LocalDate.now());
                selectedDate = LocalDate.now();
            }
        }
        
        // Validate date is not in the future
        if (selectedDate.isAfter(LocalDate.now())) {
            System.out.println("Warning: Selected date is in the future.");
            System.out.print("Continue anyway? (yes/no): ");
            String confirm = scanner.nextLine().trim();
            if (!confirm.equalsIgnoreCase("yes")) {
                return selectDate(); // Recursive call to select again
            }
        }
        
        return selectedDate;
    }
    
    /**
     * Updates screen display according to the selected date.
     * Shows existing records for that date.
     * 
     * @param date The date to display records for
     */
    private void updateScreenForDate(LocalDate date) {
        System.out.println("\n=== Updating Screen for " + date + " ===");
        
        try {
            List<AbsenceRecord> existingRecords = admin.updateScreenForDate(date);
            
            if (existingRecords.isEmpty()) {
                System.out.println("No existing attendance records found for " + date);
                System.out.println("Screen shows empty attendance form.");
            } else {
                System.out.println("Found " + existingRecords.size() + " existing records:");
                for (AbsenceRecord record : existingRecords) {
                    System.out.println("  - " + record.toSummaryString());
                }
                System.out.println("Screen updated with existing data.");
            }
            
            System.out.println("✓ Screen update completed.");
            
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
            throw e; // Re-throw as this is a critical error
        }
    }
    
    /**
     * Simulates filling out the attendance form.
     * Administrator marks each student as present, absent, or late.
     * 
     * @param date The date for the attendance records
     * @return List of StudentAttendance records
     */
    private List<Administrator.StudentAttendance> fillOutForm(LocalDate date) {
        System.out.println("\n=== Filling Out Attendance Form ===");
        System.out.println("Mark attendance for each student on " + date);
        System.out.println("Options: P=Present, A=Absent, L=Late, S=Skip");
        
        List<Administrator.StudentAttendance> attendanceRecords = new ArrayList<>();
        
        for (Student student : sampleStudents) {
            System.out.println("\nStudent: " + student.getName() + " (ID: " + student.getId() + ")");
            System.out.print("Attendance [P/A/L/S]: ");
            
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.equals("S")) {
                System.out.println("Skipping this student.");
                continue;
            }
            
            AbsenceRecord.AttendanceStatus status;
            switch (input) {
                case "P":
                    status = AbsenceRecord.AttendanceStatus.PRESENT;
                    System.out.println("Marked as Present");
                    break;
                case "A":
                    status = AbsenceRecord.AttendanceStatus.ABSENT;
                    System.out.println("Marked as Absent");
                    break;
                case "L":
                    status = AbsenceRecord.AttendanceStatus.LATE;
                    System.out.println("Marked as Late");
                    break;
                default:
                    System.out.println("Invalid input. Defaulting to Present.");
                    status = AbsenceRecord.AttendanceStatus.PRESENT;
                    break;
            }
            
            attendanceRecords.add(new Administrator.StudentAttendance(student, status));
        }
        
        System.out.println("\nForm completed for " + attendanceRecords.size() + " students.");
        return attendanceRecords;
    }
    
    /**
     * Saves the attendance data entered in the form.
     * 
     * @param date The date of attendance
     * @param attendanceRecords The attendance data to save
     * @return List of saved AbsenceRecord objects
     */
    private List<AbsenceRecord> saveAttendanceData(LocalDate date, 
                                                   List<Administrator.StudentAttendance> attendanceRecords) {
        System.out.println("\n=== Saving Attendance Data ===");
        
        System.out.print("Add optional notes for all records (or press Enter for none): ");
        String notes = scanner.nextLine().trim();
        
        if (!notes.isEmpty()) {
            System.out.println("Notes added: " + notes);
        }
        
        try {
            List<AbsenceRecord> savedRecords = admin.enterAbsenceData(date, attendanceRecords, notes);
            
            if (savedRecords.isEmpty()) {
                System.out.println("✗ No records were saved.");
            } else {
                System.out.println("✓ Successfully saved " + savedRecords.size() + " attendance records.");
                
                // Count by status
                int presentCount = 0, absentCount = 0, lateCount = 0;
                for (AbsenceRecord record : savedRecords) {
                    if (record.isPresent()) presentCount++;
                    if (record.isAbsent()) absentCount++;
                    if (record.isLate()) lateCount++;
                }
                
                System.out.println("  - Present: " + presentCount);
                System.out.println("  - Absent: " + absentCount);
                System.out.println("  - Late: " + lateCount);
            }
            
            return savedRecords;
            
        } catch (Exception e) {
            System.out.println("✗ Error saving attendance data: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Failed to save attendance data", e);
            return new ArrayList<>();
        }
    }
    
    /**
     * Sends data to server and triggers email notifications for absent students.
     * 
     * @param date The date of records to send
     * @return List of students whose parents were notified
     */
    private List<Student> sendDataToServer(LocalDate date) {
        System.out.println("\n=== Sending Data to Server ===");
        System.out.println("Sending attendance data for " + date + " to server...");
        System.out.println("This will trigger email notifications to parents of absent students.");
        
        System.out.print("Confirm send? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Data sending cancelled.");
            return new ArrayList<>();
        }
        
        try {
            List<Student> notifiedStudents = admin.sendDataToServer(date);
            
            if (notifiedStudents.isEmpty()) {
                System.out.println("✓ Data sent to server successfully.");
                System.out.println("  No absent students found, so no emails were sent.");
            } else {
                System.out.println("✓ Data sent to server successfully.");
                System.out.println("  Email notifications sent to " + notifiedStudents.size() + " parents:");
                
                for (Student student : notifiedStudents) {
                    System.out.println("    - " + student.getName() + "'s parent at " + student.getParentEmail());
                }
            }
            
            return notifiedStudents;
            
        } catch (Exception e) {
            System.out.println("✗ Error sending data to server: " + e.getMessage());
            LOGGER.log(Level.SEVERE, "Failed to send data to server", e);
            return new ArrayList<>();
        }
    }
    
    /**
     * Displays updated log data after processing.
     * 
     * @param date The date to display logs for
     */
    private void displayUpdatedLogData(LocalDate date) {
        System.out.println("\n=== Updated Log Data ===");
        
        try {
            String logData = admin.displayUpdatedLogData(date);
            System.out.println(logData);
            System.out.println("✓ Log data displayed successfully.");
        } catch (Exception e) {
            System.out.println("✗ Error displaying log data: " + e.getMessage());
        }
    }
    
    /**
     * Simulates an operation interruption and SMOS server reconnection.
     * Demonstrates the system's ability to handle interruptions gracefully.
     */
    private void simulateInterruption() {
        System.out.println("\n=== Simulating Operation Interruption ===");
        System.out.println("(This demonstrates interruption handling as per use case postconditions)");
        
        System.out.print("Simulate interruption? (yes/no): ");
        String simulate = scanner.nextLine().trim();
        
        if (simulate.equalsIgnoreCase("yes")) {
            System.out.println("Simulating administrator interruption...");
            
            // Simulate interruption
            admin.interruptOperation();
            
            System.out.println("✓ Interruption handled.");
            System.out.println("  - System remains on registry screen");
            System.out.println("  - Connection to SMOS server checked/re-established");
        } else {
            System.out.println("Interruption simulation skipped.");
        }
    }
    
    /**
     * Gets the administrator instance (for testing purposes).
     * 
     * @return The Administrator instance
     */
    public Administrator getAdmin() {
        return admin;
    }
    
    /**
     * Gets the sample students (for testing purposes).
     * 
     * @return List of sample students
     */
    public List<Student> getSampleStudents() {
        return new ArrayList<>(sampleStudents);
    }
}