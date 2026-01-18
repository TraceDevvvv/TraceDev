import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Main Entry Point for the Class Register System
 * Program Name: ViewDetailsSingleRegister
 * Description: This system allows administrators to view details of class registers,
 * including student attendance information (absent/present/late) and manage 
 * justifications and disciplinary notes.
 * 
 * Preconditions: 
 * - User must be logged in as administrator
 * - User must have selected a register from the "ViewingElCoregistri" screen
 * 
 * Postconditions:
 * - Displays details of a class registry
 * - Connection to SMOS server is interrupted (simulated)
 */
public class Main {
    // System configuration
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password123";
    private static boolean isLoggedIn = false;
    private static Administrator currentAdmin;
    private static SMOSServerConnection smosConnection;
    
    public static void main(String[] args) {
        System.out.println("=== Class Register System - View Details Single Register ===\n");
        
        // Initialize SMOS server connection
        smosConnection = new SMOSServerConnection();
        
        try {
            // Simulate administrator login
            if (login()) {
                System.out.println("Login successful!\n");
                
                // Simulate selecting a register from "ViewingElCoregistri" screen
                ClassRegister selectedRegister = selectRegister();
                
                if (selectedRegister != null) {
                    // Display register details as per use case
                    displayRegisterDetails(selectedRegister);
                    
                    // Handle forms for justifications and disciplinary notes
                    handleForms(selectedRegister);
                    
                    // Simulate connection interruption to SMOS server (as per postconditions)
                    interruptSMOSConnection();
                }
            } else {
                System.out.println("Login failed. Exiting system.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            if (smosConnection != null) {
                smosConnection.disconnect();
            }
        }
    }
    
    /**
     * Simulates administrator login process
     * @return true if login successful, false otherwise
     */
    private static boolean login() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Administrator Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            currentAdmin = new Administrator(username, "System Administrator");
            isLoggedIn = true;
            return true;
        }
        
        return false;
    }
    
    /**
     * Simulates selecting a register from the "ViewingElCoregistri" screen
     * @return Selected ClassRegister object
     */
    private static ClassRegister selectRegister() {
        System.out.println("\n=== Selecting Register from ViewingElCoregistri ===");
        
        // Create sample data for demonstration
        ClassRegister register = createSampleRegister();
        
        System.out.println("Register selected: " + register.getClassName() + " - " + register.getTeacherName());
        System.out.println("Clicking 'Details' button...\n");
        
        return register;
    }
    
    /**
     * Displays the details of the selected register as per use case requirement
     * Shows information organized by date, with today's date highlighted
     * @param register The class register to display
     */
    private static void displayRegisterDetails(ClassRegister register) {
        System.out.println("=== Class Register Details ===");
        System.out.println("Class: " + register.getClassName());
        System.out.println("Teacher: " + register.getTeacherName());
        System.out.println("Academic Year: " + register.getAcademicYear());
        System.out.println("Total Students: " + register.getTotalStudents());
        System.out.println("\n");
        
        // Display information organized by date (as per use case)
        displayAttendanceByDate(register);
        
        // Display today's detailed information (as per use case)
        displayTodaysDetails(register);
    }
    
    /**
     * Displays attendance information organized by date
     * @param register The class register containing attendance records
     */
    private static void displayAttendanceByDate(ClassRegister register) {
        System.out.println("=== Attendance Information by Date ===");
        
        Map<LocalDate, AttendanceRecord> attendanceRecords = register.getAttendanceRecords();
        
        if (attendanceRecords.isEmpty()) {
            System.out.println("No attendance records available.");
            return;
        }
        
        // Sort dates chronologically
        List<LocalDate> dates = new ArrayList<>(attendanceRecords.keySet());
        Collections.sort(dates);
        
        // Display records for each date
        for (LocalDate date : dates) {
            AttendanceRecord record = attendanceRecords.get(date);
            System.out.println("\nDate: " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            System.out.println("  Present: " + record.getPresentCount());
            System.out.println("  Absent: " + record.getAbsentCount());
            System.out.println("  Late: " + record.getLateCount());
        }
        System.out.println();
    }
    
    /**
     * Displays detailed information for today's date as per use case requirement
     * Shows list of students with absent/present/late status
     * @param register The class register containing student information
     */
    private static void displayTodaysDetails(ClassRegister register) {
        LocalDate today = LocalDate.now();
        System.out.println("=== Today's Details (" + today + ") ===");
        
        // Get today's attendance record
        AttendanceRecord todaysRecord = register.getAttendanceRecords().get(today);
        
        if (todaysRecord == null) {
            System.out.println("No attendance recorded for today.");
            return;
        }
        
        // Display student list with status
        System.out.println("\nList of Students:");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-20s %-10s %-15s %s%n", "Student Name", "Status", "Arrival Time", "Notes");
        System.out.println("----------------------------------------------------------------");
        
        for (Student student : register.getStudents()) {
            AttendanceStatus status = student.getTodaysStatus();
            String statusStr = status.toString();
            String timeStr = (status == AttendanceStatus.PRESENT || status == AttendanceStatus.LATE) ? 
                           student.getTodaysArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : "N/A";
            String notes = student.getTodaysNotes() != null ? student.getTodaysNotes() : "";
            
            System.out.printf("%-20s %-10s %-15s %s%n", 
                student.getName(), statusStr, timeStr, notes);
        }
        System.out.println("----------------------------------------------------------------\n");
    }
    
    /**
     * Handles forms for managing justifications and disciplinary notes
     * @param register The class register to manage
     */
    private static void handleForms(ClassRegister register) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Forms Management ===");
        System.out.println("1. Manage Justifications");
        System.out.println("2. Manage Disciplinary Notes");
        System.out.println("3. Exit Forms");
        System.out.print("Select option: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    manageJustifications(register);
                    break;
                case 2:
                    manageDisciplinaryNotes(register);
                    break;
                case 3:
                    System.out.println("Exiting forms management.");
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    
    /**
     * Manages justifications for students
     * @param register The class register containing students
     */
    private static void manageJustifications(ClassRegister register) {
        System.out.println("\n=== Manage Justifications ===");
        JustificationForm form = new JustificationForm(register.getStudents(), currentAdmin);
        
        // Display current justifications
        form.displayJustifications();
        
        // Process a sample justification
        if (!register.getStudents().isEmpty()) {
            Student sampleStudent = register.getStudents().get(0);
            form.addJustification(sampleStudent, "Medical appointment", LocalDate.now().minusDays(1));
            System.out.println("Sample justification added for " + sampleStudent.getName());
        }
        
        form.closeForm();
    }
    
    /**
     * Manages disciplinary notes for students
     * @param register The class register containing students
     */
    private static void manageDisciplinaryNotes(ClassRegister register) {
        System.out.println("\n=== Manage Disciplinary Notes ===");
        DisciplinaryNoteForm form = new DisciplinaryNoteForm(register.getStudents(), currentAdmin);
        
        // Display current disciplinary notes
        form.displayNotes();
        
        // Process a sample note
        if (register.getStudents().size() > 1) {
            Student sampleStudent = register.getStudents().get(1);
            form.addNote(sampleStudent, "Late submission of homework", LocalDate.now());
            System.out.println("Sample disciplinary note added for " + sampleStudent.getName());
        }
        
        form.closeForm();
    }
    
    /**
     * Simulates connection interruption to SMOS server (as per postconditions)
     */
    private static void interruptSMOSConnection() {
        System.out.println("\n=== SMOS Server Connection Status ===");
        if (smosConnection != null && smosConnection.isConnected()) {
            System.out.println("Interrupting connection to SMOS server...");
            smosConnection.interrupt();
            System.out.println("Connection to SMOS server interrupted (as per postconditions).");
        }
    }
    
    /**
     * Creates a sample register with test data for demonstration
     * @return A ClassRegister object with sample data
     */
    private static ClassRegister createSampleRegister() {
        // Create a class register
        ClassRegister register = new ClassRegister(
            "Mathematics 101",
            "Dr. Smith",
            "2023-2024",
            "Room 201"
        );
        
        // Add sample students
        register.addStudent(new Student("John Doe", "S001"));
        register.addStudent(new Student("Jane Smith", "S002"));
        register.addStudent(new Student("Bob Johnson", "S003"));
        register.addStudent(new Student("Alice Brown", "S004"));
        register.addStudent(new Student("Charlie Wilson", "S005"));
        
        // Create attendance records for today and yesterday
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        
        AttendanceRecord todaysRecord = new AttendanceRecord(today);
        AttendanceRecord yesterdaysRecord = new AttendanceRecord(yesterday);
        
        // Set today's attendance
        List<Student> students = register.getStudents();
        students.get(0).setAttendance(today, AttendanceStatus.PRESENT, LocalTime.of(8, 30), "On time");
        students.get(1).setAttendance(today, AttendanceStatus.LATE, LocalTime.of(9, 15), "Traffic delay");
        students.get(2).setAttendance(today, AttendanceStatus.ABSENT, null, "Sick");
        students.get(3).setAttendance(today, AttendanceStatus.PRESENT, LocalTime.of(8, 45), "");
        students.get(4).setAttendance(today, AttendanceStatus.PRESENT, LocalTime.of(8, 35), "");
        
        // Set yesterday's attendance
        students.get(0).setAttendance(yesterday, AttendanceStatus.PRESENT, LocalTime.of(8, 30), "");
        students.get(1).setAttendance(yesterday, AttendanceStatus.PRESENT, LocalTime.of(8, 40), "");
        students.get(2).setAttendance(yesterday, AttendanceStatus.ABSENT, null, "Family emergency");
        students.get(3).setAttendance(yesterday, AttendanceStatus.LATE, LocalTime.of(9, 5), "Missed bus");
        students.get(4).setAttendance(yesterday, AttendanceStatus.PRESENT, LocalTime.of(8, 35), "");
        
        // Update record counts
        todaysRecord.updateCounts(students, today);
        yesterdaysRecord.updateCounts(students, yesterday);
        
        // Add records to register
        register.addAttendanceRecord(todaysRecord);
        register.addAttendanceRecord(yesterdaysRecord);
        
        return register;
    }
}