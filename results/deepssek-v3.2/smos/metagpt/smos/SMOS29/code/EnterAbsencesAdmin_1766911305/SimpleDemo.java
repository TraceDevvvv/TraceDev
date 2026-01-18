import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SimpleDemo - A simple demonstration class that runs a predefined scenario
 * of the EnterAbsencesAdmin system without requiring user input.
 * 
 * This class demonstrates the complete use case flow:
 * 1. Administrator logs in
 * 2. Updates screen for selected date
 * 3. Enters attendance data for students
 * 4. Saves data and triggers email notifications
 * 5. Displays updated log data
 * 6. Handles operation interruption
 * 
 * The demonstration uses predefined data and simulates all system interactions.
 */
public class SimpleDemo {
    private static final Logger LOGGER = Logger.getLogger(SimpleDemo.class.getName());
    
    /**
     * Main method - entry point for the demonstration.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Configure logging level
        Logger.getLogger("").setLevel(Level.INFO);
        
        System.out.println("==========================================");
        System.out.println("ENTER ABSENCES ADMIN SYSTEM - DEMONSTRATION");
        System.out.println("==========================================\n");
        
        try {
            // Run the complete demonstration
            runDemo();
            
            System.out.println("\n==========================================");
            System.out.println("DEMONSTRATION COMPLETED SUCCESSFULLY");
            System.out.println("==========================================");
        } catch (Exception e) {
            System.err.println("\n==========================================");
            System.err.println("DEMONSTRATION FAILED");
            System.err.println("==========================================");
            LOGGER.log(Level.SEVERE, "Demonstration failed: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
    
    /**
     * Runs the complete demonstration of the EnterAbsencesAdmin use case.
     */
    private static void runDemo() {
        // Step 0: Create administrator and login (Precondition)
        System.out.println("STEP 0: ADMINISTRATOR LOGIN");
        System.out.println("----------------------------");
        Administrator admin = new Administrator("admin", "secure123");
        boolean loginSuccess = admin.login("admin", "secure123");
        
        if (!loginSuccess) {
            System.out.println("ERROR: Login failed. Cannot proceed.");
            return;
        }
        System.out.println("✓ Administrator logged in successfully\n");
        
        // Step 1: Select date and update screen
        System.out.println("STEP 1: SELECT DATE AND UPDATE SCREEN");
        System.out.println("--------------------------------------");
        LocalDate selectedDate = LocalDate.of(2024, 12, 28); // Using a fixed date for demo
        System.out.println("Selected date: " + selectedDate);
        
        // Update screen with existing records for this date
        List<AbsenceRecord> existingRecords = admin.updateScreenForDate(selectedDate);
        System.out.println("✓ Screen updated. Found " + existingRecords.size() + " existing records\n");
        
        // Step 2: Create sample students for the demonstration
        System.out.println("STEP 2: CREATE SAMPLE STUDENTS");
        System.out.println("------------------------------");
        List<Student> demoStudents = createSampleStudents();
        System.out.println("Created " + demoStudents.size() + " sample students:\n");
        
        for (Student student : demoStudents) {
            System.out.println("  - " + student.getName() + " (ID: " + student.getId() + 
                             ", Parent: " + student.getParentEmail() + ")");
        }
        System.out.println();
        
        // Step 3: Fill out attendance form
        System.out.println("STEP 3: FILL OUT ATTENDANCE FORM");
        System.out.println("--------------------------------");
        System.out.println("Marking attendance for " + demoStudents.size() + " students on " + selectedDate);
        
        List<Administrator.StudentAttendance> attendanceRecords = new ArrayList<>();
        
        // Student 1: Absent (sick)
        attendanceRecords.add(new Administrator.StudentAttendance(
            demoStudents.get(0), AbsenceRecord.AttendanceStatus.ABSENT));
        System.out.println("  - " + demoStudents.get(0).getName() + ": Marked as ABSENT (sick)");
        
        // Student 2: Present
        attendanceRecords.add(new Administrator.StudentAttendance(
            demoStudents.get(1), AbsenceRecord.AttendanceStatus.PRESENT));
        System.out.println("  - " + demoStudents.get(1).getName() + ": Marked as PRESENT");
        
        // Student 3: Late
        attendanceRecords.add(new Administrator.StudentAttendance(
            demoStudents.get(2), AbsenceRecord.AttendanceStatus.LATE));
        System.out.println("  - " + demoStudents.get(2).getName() + ": Marked as LATE (traffic)");
        
        // Student 4: Present
        attendanceRecords.add(new Administrator.StudentAttendance(
            demoStudents.get(3), AbsenceRecord.AttendanceStatus.PRESENT));
        System.out.println("  - " + demoStudents.get(3).getName() + ": Marked as PRESENT");
        
        // Student 5: Absent (family trip)
        attendanceRecords.add(new Administrator.StudentAttendance(
            demoStudents.get(4), AbsenceRecord.AttendanceStatus.ABSENT));
        System.out.println("  - " + demoStudents.get(4).getName() + ": Marked as ABSENT (family trip)\n");
        
        // Step 4: Save attendance data
        System.out.println("STEP 4: SAVE ATTENDANCE DATA");
        System.out.println("----------------------------");
        String notes = "Administrator entered attendance data. " +
                      "Student 1 reported sick, Student 3 arrived late due to traffic, " +
                      "Student 5 on family trip with prior notice.";
        
        List<AbsenceRecord> savedRecords = admin.enterAbsenceData(
            selectedDate, attendanceRecords, notes);
        
        System.out.println("✓ Attendance data saved successfully");
        System.out.println("  Total records saved: " + savedRecords.size());
        System.out.println("  Notes: " + notes + "\n");
        
        // Step 5: Send data to server (triggers email notifications)
        System.out.println("STEP 5: SEND DATA TO SERVER");
        System.out.println("---------------------------");
        System.out.println("Sending data to server for processing...");
        System.out.println("This will trigger email notifications to parents of absent students.");
        
        List<Student> notifiedStudents = admin.sendDataToServer(selectedDate);
        
        System.out.println("✓ Data sent to server successfully");
        System.out.println("  Email notifications sent to " + notifiedStudents.size() + " parents:");
        for (Student student : notifiedStudents) {
            System.out.println("    - " + student.getName() + "'s parent at " + student.getParentEmail());
        }
        System.out.println();
        
        // Step 6: Display updated log data
        System.out.println("STEP 6: DISPLAY UPDATED LOG DATA");
        System.out.println("--------------------------------");
        String logData = admin.displayUpdatedLogData(selectedDate);
        System.out.println(logData);
        System.out.println("✓ Log data displayed successfully\n");
        
        // Step 7: Demonstrate interruption handling (Postcondition)
        System.out.println("STEP 7: HANDLE OPERATION INTERRUPTION");
        System.out.println("-------------------------------------");
        System.out.println("Simulating administrator interruption...");
        System.out.println("(As per use case postconditions)");
        
        admin.interruptOperation();
        
        System.out.println("✓ Interruption handled gracefully");
        System.out.println("  - System remains on registry screen");
        System.out.println("  - SMOS server connection maintained\n");
        
        // Step 8: Cleanup and logout
        System.out.println("STEP 8: CLEANUP AND LOGOUT");
        System.out.println("--------------------------");
        admin.logout();
        System.out.println("✓ Administrator logged out");
        System.out.println("✓ Demonstration completed\n");
        
        // Display final summary
        System.out.println("DEMONSTRATION SUMMARY:");
        System.out.println("======================");
        System.out.println("Date: " + selectedDate);
        System.out.println("Total students processed: " + savedRecords.size());
        
        // Count attendance statuses
        int presentCount = 0, absentCount = 0, lateCount = 0;
        for (AbsenceRecord record : savedRecords) {
            if (record.isPresent()) presentCount++;
            if (record.isAbsent()) absentCount++;
            if (record.isLate()) lateCount++;
        }
        
        System.out.println("Present: " + presentCount);
        System.out.println("Absent: " + absentCount + " (email notifications sent)");
        System.out.println("Late: " + lateCount);
        System.out.println("Email notifications: " + notifiedStudents.size());
        System.out.println("System status: Remains on registry screen");
    }
    
    /**
     * Creates a list of sample students for the demonstration.
     * 
     * @return List of sample Student objects
     */
    private static List<Student> createSampleStudents() {
        List<Student> students = new ArrayList<>();
        
        students.add(new Student("S2024001", "Emma Johnson", "emma.johnson.parent@example.com"));
        students.add(new Student("S2024002", "Michael Chen", "michael.chen.parent@example.com"));
        students.add(new Student("S2024003", "Sophia Williams", "sophia.williams.parent@example.com"));
        students.add(new Student("S2024004", "James Miller", "james.miller.parent@example.com"));
        students.add(new Student("S2024005", "Olivia Davis", "olivia.davis.parent@example.com"));
        
        return students;
    }
    
    /**
     * Helper method to print a separator line for better output formatting.
     */
    private static void printSeparator() {
        System.out.println("\n" + "=".repeat(60) + "\n");
    }
}