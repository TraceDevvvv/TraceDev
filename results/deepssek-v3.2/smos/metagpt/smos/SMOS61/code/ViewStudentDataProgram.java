import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main program for the ViewStudentData use case.
 * This program simulates a parent viewing their child's attendance and disciplinary information.
 * It displays a summary table and simulates connection to an SMOS server.
 */
public class ViewStudentDataProgram {
    
    /**
     * Represents a student with basic information and attendance records.
     */
    static class Student {
        private String studentId;
        private String name;
        private int grade;
        private List<AttendanceRecord> attendanceRecords;
        
        public Student(String studentId, String name, int grade) {
            this.studentId = studentId;
            this.name = name;
            this.grade = grade;
            this.attendanceRecords = new ArrayList<>();
        }
        
        public String getStudentId() { return studentId; }
        public String getName() { return name; }
        public int getGrade() { return grade; }
        public List<AttendanceRecord> getAttendanceRecords() { return attendanceRecords; }
        
        public void addAttendanceRecord(AttendanceRecord record) {
            attendanceRecords.add(record);
        }
        
        /**
         * Gets attendance records for a specific date range.
         * For simplicity, we return all records in this simulation.
         */
        public List<AttendanceRecord> getAttendanceRecords(LocalDate startDate, LocalDate endDate) {
            List<AttendanceRecord> filtered = new ArrayList<>();
            for (AttendanceRecord record : attendanceRecords) {
                if (!record.getDate().isBefore(startDate) && !record.getDate().isAfter(endDate)) {
                    filtered.add(record);
                }
            }
            return filtered;
        }
    }
    
    /**
     * Represents a single day's attendance record for a student.
     */
    static class AttendanceRecord {
        private LocalDate date;
        private boolean absent;
        private String disciplinaryNote;
        private int delayMinutes;
        private String justification;
        
        public AttendanceRecord(LocalDate date, boolean absent, String disciplinaryNote, 
                               int delayMinutes, String justification) {
            this.date = date;
            this.absent = absent;
            this.disciplinaryNote = disciplinaryNote;
            this.delayMinutes = delayMinutes;
            this.justification = justification;
        }
        
        public LocalDate getDate() { return date; }
        public boolean isAbsent() { return absent; }
        public String getDisciplinaryNote() { return disciplinaryNote; }
        public int getDelayMinutes() { return delayMinutes; }
        public String getJustification() { return justification; }
        
        @Override
        public String toString() {
            return String.format("%s | Absent: %s | Delay: %d min | Note: %s | Justification: %s",
                date, absent ? "Yes" : "No", delayMinutes, 
                disciplinaryNote.isEmpty() ? "None" : disciplinaryNote,
                justification.isEmpty() ? "None" : justification);
        }
    }
    
    /**
     * Represents a parent user who can view their children's data.
     */
    static class Parent {
        private String parentId;
        private String name;
        private List<Student> children;
        
        public Parent(String parentId, String name) {
            this.parentId = parentId;
            this.name = name;
            this.children = new ArrayList<>();
        }
        
        public String getParentId() { return parentId; }
        public String getName() { return name; }
        public List<Student> getChildren() { return children; }
        
        public void addChild(Student student) {
            children.add(student);
        }
        
        /**
         * Simulates the parent clicking the "Register" button for a specific child.
         * Returns the selected child or null if invalid selection.
         */
        public Student selectChild(int childIndex) {
            if (childIndex >= 0 && childIndex < children.size()) {
                return children.get(childIndex);
            }
            return null;
        }
    }
    
    /**
     * Generates and displays a summary table of attendance records.
     */
    static class SummaryTable {
        
        /**
         * Displays a formatted summary table for the given student's records.
         * @param student The student whose records to display
         * @param records The attendance records to include in the table
         */
        public static void display(Student student, List<AttendanceRecord> records) {
            System.out.println("\n" + "=".repeat(100));
            System.out.println("SUMMARY TABLE FOR STUDENT: " + student.getName() + 
                             " (ID: " + student.getStudentId() + ", Grade: " + student.getGrade() + ")");
            System.out.println("=".repeat(100));
            System.out.printf("%-12s | %-8s | %-8s | %-30s | %-20s%n", 
                "Date", "Absent", "Delay", "Disciplinary Note", "Justification");
            System.out.println("-".repeat(100));
            
            if (records.isEmpty()) {
                System.out.println("No attendance records found for the selected period.");
            } else {
                for (AttendanceRecord record : records) {
                    System.out.printf("%-12s | %-8s | %-8s | %-30s | %-20s%n",
                        record.getDate(),
                        record.isAbsent() ? "Yes" : "No",
                        record.getDelayMinutes() + " min",
                        record.getDisciplinaryNote().isEmpty() ? "None" : 
                            (record.getDisciplinaryNote().length() > 28 ? 
                             record.getDisciplinaryNote().substring(0, 25) + "..." : 
                             record.getDisciplinaryNote()),
                        record.getJustification().isEmpty() ? "None" : 
                            (record.getJustification().length() > 18 ? 
                             record.getJustification().substring(0, 15) + "..." : 
                             record.getJustification()));
                }
            }
            
            // Display summary statistics
            int totalAbsences = 0;
            int totalDelays = 0;
            int recordsWithNotes = 0;
            
            for (AttendanceRecord record : records) {
                if (record.isAbsent()) totalAbsences++;
                if (record.getDelayMinutes() > 0) totalDelays++;
                if (!record.getDisciplinaryNote().isEmpty()) recordsWithNotes++;
            }
            
            System.out.println("-".repeat(100));
            System.out.println("SUMMARY STATISTICS:");
            System.out.println("Total records: " + records.size());
            System.out.println("Total absences: " + totalAbsences);
            System.out.println("Days with delays: " + totalDelays);
            System.out.println("Days with disciplinary notes: " + recordsWithNotes);
            System.out.println("=".repeat(100));
        }
    }
    
    /**
     * Simulates connection to the SMOS (School Management Operating System) server.
     * In a real system, this would handle actual network connections.
     */
    static class SMOSConnector {
        private boolean connected;
        private String serverUrl;
        
        public SMOSConnector(String serverUrl) {
            this.serverUrl = serverUrl;
            this.connected = false;
        }
        
        /**
         * Simulates connecting to the SMOS server.
         * @return true if connection successful, false otherwise
         */
        public boolean connect() {
            System.out.println("\nAttempting to connect to SMOS server at: " + serverUrl);
            // Simulate connection attempt with random success/failure
            try {
                // Simulate network delay
                Thread.sleep(500);
                // Simulate 90% success rate
                connected = Math.random() > 0.1;
                
                if (connected) {
                    System.out.println("✓ Successfully connected to SMOS server.");
                } else {
                    System.out.println("✗ Failed to connect to SMOS server. Server may be interrupted.");
                }
            } catch (InterruptedException e) {
                System.out.println("✗ Connection interrupted: " + e.getMessage());
                connected = false;
            }
            return connected;
        }
        
        /**
         * Simulates fetching student data from the SMOS server.
         * In reality, this would make an API call.
         */
        public List<AttendanceRecord> fetchStudentData(String studentId) {
            if (!connected) {
                System.out.println("Cannot fetch data: Not connected to SMOS server.");
                return new ArrayList<>();
            }
            
            System.out.println("Fetching data for student ID: " + studentId + " from SMOS server...");
            // Simulate data fetching delay
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Data fetch interrupted: " + e.getMessage());
            }
            
            // Return simulated data
            return generateMockAttendanceData();
        }
        
        /**
         * Generates mock attendance data for demonstration.
         * In a real system, this would come from the actual database.
         */
        private List<AttendanceRecord> generateMockAttendanceData() {
            List<AttendanceRecord> records = new ArrayList<>();
            LocalDate today = LocalDate.now();
            
            // Generate records for the past 7 days
            for (int i = 6; i >= 0; i--) {
                LocalDate date = today.minusDays(i);
                boolean absent = i == 2 || i == 5; // Absent on day 2 and 5
                String note = "";
                int delay = 0;
                String justification = "";
                
                // Add some variations
                if (i == 1) {
                    note = "Late to class";
                    delay = 15;
                    justification = "Traffic congestion";
                } else if (i == 3) {
                    note = "Disruptive behavior";
                    justification = "Parent notified";
                } else if (i == 4) {
                    delay = 5;
                    justification = "Bus delay";
                }
                
                records.add(new AttendanceRecord(date, absent, note, delay, justification));
            }
            
            System.out.println("✓ Retrieved " + records.size() + " attendance records from SMOS server.");
            return records;
        }
        
        public void disconnect() {
            if (connected) {
                System.out.println("Disconnecting from SMOS server...");
                connected = false;
            }
        }
        
        public boolean isConnected() {
            return connected;
        }
    }
    
    /**
     * Main method - entry point of the program.
     * Simulates the complete ViewStudentData use case flow.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=".repeat(60));
        System.out.println("SCHOOL PARENT PORTAL - VIEW STUDENT DATA");
        System.out.println("=".repeat(60));
        
        // Simulate preconditions: User is logged in as parent
        System.out.println("\n[Precondition Check]");
        System.out.println("✓ User is logged in as a parent.");
        
        // Create a parent with children
        Parent parent = new Parent("P12345", "John Smith");
        
        // Create sample students (children)
        Student student1 = new Student("S1001", "Emma Smith", 5);
        Student student2 = new Student("S1002", "Liam Smith", 7);
        
        parent.addChild(student1);
        parent.addChild(student2);
        
        // Display parent's children
        System.out.println("\nParent: " + parent.getName() + " (ID: " + parent.getParentId() + ")");
        System.out.println("Children:");
        for (int i = 0; i < parent.getChildren().size(); i++) {
            Student child = parent.getChildren().get(i);
            System.out.println("  " + (i + 1) + ". " + child.getName() + 
                             " (ID: " + child.getStudentId() + ", Grade " + child.getGrade() + ")");
        }
        
        // Simulate user clicking "Register" button for a child
        System.out.print("\nEnter the number of the child to view (1-" + parent.getChildren().size() + "): ");
        int childChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Student selectedChild = parent.selectChild(childChoice - 1);
        
        if (selectedChild == null) {
            System.out.println("Invalid selection. Exiting program.");
            scanner.close();
            return;
        }
        
        System.out.println("\n[Event Sequence]");
        System.out.println("1. Parent selected child: " + selectedChild.getName());
        
        // Create and connect to SMOS server
        SMOSConnector smosConnector = new SMOSConnector("https://smos.schoolsystem.edu/api");
        boolean connected = smosConnector.connect();
        
        // Fetch attendance data from SMOS server
        List<AttendanceRecord> attendanceRecords;
        if (connected) {
            attendanceRecords = smosConnector.fetchStudentData(selectedChild.getStudentId());
            // Add records to student object
            for (AttendanceRecord record : attendanceRecords) {
                selectedChild.addAttendanceRecord(record);
            }
        } else {
            System.out.println("\nUsing cached data for demonstration...");
            // Generate mock data if connection failed
            attendanceRecords = generateFallbackData();
            for (AttendanceRecord record : attendanceRecords) {
                selectedChild.addAttendanceRecord(record);
            }
        }
        
        // Display summary table
        System.out.println("\n2. System displays summary table:");
        SummaryTable.display(selectedChild, attendanceRecords);
        
        // Simulate postconditions
        System.out.println("\n[Postconditions]");
        System.out.println("✓ Information relating to " + selectedChild.getName() + " is displayed.");
        System.out.println("✓ Connection to SMOS server: " + (smosConnector.isConnected() ? "ACTIVE" : "INTERRUPTED"));
        
        // Additional options
        System.out.println("\n[Additional Options]");
        System.out.println("Would you like to:");
        System.out.println("1. View another child");
        System.out.println("2. Exit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            System.out.println("\nRestarting selection process...");
            // Note: In a real application, this would loop back to the selection
            System.out.println("(This would restart the process in a full implementation)");
        }
        
        // Cleanup
        smosConnector.disconnect();
        scanner.close();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Thank you for using the School Parent Portal.");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Generates fallback data when SMOS server connection fails.
     */
    private static List<AttendanceRecord> generateFallbackData() {
        List<AttendanceRecord> records = new ArrayList<>();
        LocalDate today = LocalDate.now();
        
        // Generate 5 days of sample data
        String[] notes = {"", "Homework not submitted", "", "Talking in class", ""};
        int[] delays = {0, 0, 10, 0, 5};
        boolean[] absences = {false, true, false, false, false};
        String[] justifications = {"", "Sick", "Bus late", "Parent conference", "Car trouble"};
        
        for (int i = 4; i >= 0; i--) {
            records.add(new AttendanceRecord(
                today.minusDays(i),
                absences[i],
                notes[i],
                delays[i],
                justifications[i]
            ));
        }
        
        return records;
    }
}