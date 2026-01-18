import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the ViewRegister use case.
 * This program simulates a class registry view system for professors.
 * It displays class register information including date, absences, 
 * disciplinary notes, delays, and justifications.
 */
public class ViewRegister {
    
    /**
     * Represents a single student record in the class register.
     * Contains all information needed for the register view.
     */
    static class StudentRecord {
        private String studentName;
        private LocalDate date;
        private boolean isAbsent;
        private String disciplinaryNotes;
        private int delayMinutes; // 0 means no delay
        private String justification;
        
        public StudentRecord(String studentName, LocalDate date, boolean isAbsent, 
                            String disciplinaryNotes, int delayMinutes, String justification) {
            this.studentName = studentName;
            this.date = date;
            this.isAbsent = isAbsent;
            this.disciplinaryNotes = disciplinaryNotes;
            this.delayMinutes = delayMinutes;
            this.justification = justification;
        }
        
        // Getters for all fields
        public String getStudentName() { return studentName; }
        public LocalDate getDate() { return date; }
        public boolean isAbsent() { return isAbsent; }
        public String getDisciplinaryNotes() { return disciplinaryNotes; }
        public int getDelayMinutes() { return delayMinutes; }
        public String getJustification() { return justification; }
        
        @Override
        public String toString() {
            return String.format("Student: %s | Date: %s | Absent: %s | Delay: %d min | Notes: %s | Justification: %s",
                studentName, date, isAbsent ? "Yes" : "No", delayMinutes, 
                disciplinaryNotes.isEmpty() ? "None" : disciplinaryNotes,
                justification.isEmpty() ? "None" : justification);
        }
    }
    
    /**
     * Represents a class register containing multiple student records.
     * This is the main data structure for the register view.
     */
    static class ClassRegister {
        private String className;
        private String professorName;
        private List<StudentRecord> records;
        
        public ClassRegister(String className, String professorName) {
            this.className = className;
            this.professorName = professorName;
            this.records = new ArrayList<>();
        }
        
        public void addStudentRecord(StudentRecord record) {
            records.add(record);
        }
        
        public String getClassName() { return className; }
        public String getProfessorName() { return professorName; }
        public List<StudentRecord> getRecords() { return records; }
        
        /**
         * Displays the complete class register information.
         * This is the main view function that shows all required information.
         */
        public void displayRegister() {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("CLASS REGISTER VIEW");
            System.out.println("=".repeat(80));
            System.out.println("Class: " + className);
            System.out.println("Professor: " + professorName);
            System.out.println("=".repeat(80));
            
            if (records.isEmpty()) {
                System.out.println("No student records available for this class.");
                return;
            }
            
            // Group records by date for better organization
            Map<LocalDate, List<StudentRecord>> recordsByDate = new HashMap<>();
            for (StudentRecord record : records) {
                recordsByDate.computeIfAbsent(record.getDate(), k -> new ArrayList<>()).add(record);
            }
            
            // Display records sorted by date
            List<LocalDate> sortedDates = new ArrayList<>(recordsByDate.keySet());
            sortedDates.sort(LocalDate::compareTo);
            
            for (LocalDate date : sortedDates) {
                System.out.println("\nDate: " + date);
                System.out.println("-".repeat(60));
                
                List<StudentRecord> dailyRecords = recordsByDate.get(date);
                int absencesCount = 0;
                int delaysCount = 0;
                
                for (StudentRecord record : dailyRecords) {
                    System.out.println(record);
                    if (record.isAbsent()) absencesCount++;
                    if (record.getDelayMinutes() > 0) delaysCount++;
                }
                
                // Display summary for the day
                System.out.println("\nDaily Summary:");
                System.out.println("  Total students: " + dailyRecords.size());
                System.out.println("  Absences: " + absencesCount);
                System.out.println("  Delays: " + delaysCount);
            }
            
            // Display overall statistics
            displayOverallStatistics();
        }
        
        /**
         * Calculates and displays overall statistics for the class register.
         * Handles edge cases like empty records.
         */
        private void displayOverallStatistics() {
            if (records.isEmpty()) {
                return;
            }
            
            int totalAbsences = 0;
            int totalDelays = 0;
            int studentsWithDisciplinaryNotes = 0;
            int studentsWithJustifications = 0;
            
            for (StudentRecord record : records) {
                if (record.isAbsent()) totalAbsences++;
                if (record.getDelayMinutes() > 0) totalDelays++;
                if (!record.getDisciplinaryNotes().isEmpty()) studentsWithDisciplinaryNotes++;
                if (!record.getJustification().isEmpty()) studentsWithJustifications++;
            }
            
            System.out.println("\n" + "=".repeat(80));
            System.out.println("OVERALL STATISTICS");
            System.out.println("=".repeat(80));
            System.out.println("Total records: " + records.size());
            System.out.println("Total absences: " + totalAbsences);
            System.out.println("Total delays: " + totalDelays);
            System.out.println("Students with disciplinary notes: " + studentsWithDisciplinaryNotes);
            System.out.println("Students with justifications: " + studentsWithJustifications);
        }
    }
    
    /**
     * Simulates the SMOS server connection mentioned in postconditions.
     * This is a mock implementation since we don't have actual server details.
     */
    static class SMOSServerConnection {
        private boolean connected;
        
        public SMOSServerConnection() {
            this.connected = false;
        }
        
        /**
         * Attempts to connect to the SMOS server.
         * Simulates the interrupted connection mentioned in requirements.
         */
        public void connect() {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("ATTEMPTING TO CONNECT TO SMOS SERVER...");
            
            // Simulate connection attempt with possible interruption
            try {
                // Simulate network delay
                Thread.sleep(1000);
                
                // Simulate interrupted connection (as mentioned in postconditions)
                System.out.println("Connection to SMOS server was interrupted.");
                System.out.println("Please check your network connection and try again.");
                this.connected = false;
                
            } catch (InterruptedException e) {
                System.out.println("Connection attempt was interrupted.");
                this.connected = false;
            }
        }
        
        public boolean isConnected() {
            return connected;
        }
    }
    
    /**
     * Simulates the professor user interface and main program flow.
     * This handles the sequence of events described in the use case.
     */
    static class ProfessorInterface {
        private String loggedInProfessor;
        private Map<String, ClassRegister> professorClasses;
        private Scanner scanner;
        
        public ProfessorInterface(String professorName) {
            this.loggedInProfessor = professorName;
            this.professorClasses = new HashMap<>();
            this.scanner = new Scanner(System.in);
            initializeSampleData();
        }
        
        /**
         * Initializes sample data for demonstration purposes.
         * In a real system, this would load from a database or file.
         */
        private void initializeSampleData() {
            // Create sample class registers
            ClassRegister mathClass = new ClassRegister("Mathematics 101", loggedInProfessor);
            ClassRegister physicsClass = new ClassRegister("Physics 201", loggedInProfessor);
            ClassRegister csClass = new ClassRegister("Computer Science 301", loggedInProfessor);
            
            // Add sample student records to Math class
            mathClass.addStudentRecord(new StudentRecord("John Doe", LocalDate.of(2024, 1, 15), 
                false, "Participated well", 0, ""));
            mathClass.addStudentRecord(new StudentRecord("Jane Smith", LocalDate.of(2024, 1, 15), 
                true, "", 0, "Medical appointment"));
            mathClass.addStudentRecord(new StudentRecord("Bob Johnson", LocalDate.of(2024, 1, 15), 
                false, "Disruptive behavior", 15, "Traffic jam"));
            
            // Add sample student records to Physics class
            physicsClass.addStudentRecord(new StudentRecord("Alice Brown", LocalDate.of(2024, 1, 16), 
                false, "Excellent participation", 0, ""));
            physicsClass.addStudentRecord(new StudentRecord("Charlie Wilson", LocalDate.of(2024, 1, 16), 
                false, "", 5, ""));
            
            // Add sample student records to CS class
            csClass.addStudentRecord(new StudentRecord("David Lee", LocalDate.of(2024, 1, 17), 
                false, "Helped other students", 0, ""));
            csClass.addStudentRecord(new StudentRecord("Emma Garcia", LocalDate.of(2024, 1, 17), 
                true, "", 0, "Family emergency"));
            
            // Store the classes
            professorClasses.put("MATH101", mathClass);
            professorClasses.put("PHYS201", physicsClass);
            professorClasses.put("CS301", csClass);
        }
        
        /**
         * Displays the list of classes taught by the professor.
         * This simulates the "VisualLancoclasses" view mentioned in preconditions.
         */
        public void displayClasses() {
            System.out.println("\n" + "=".repeat(80));
            System.out.println("WELCOME, PROFESSOR " + loggedInProfessor.toUpperCase());
            System.out.println("=".repeat(80));
            System.out.println("Your Classes (VisualLancoclasses View):");
            System.out.println("-".repeat(60));
            
            int index = 1;
            for (Map.Entry<String, ClassRegister> entry : professorClasses.entrySet()) {
                System.out.println(index + ". " + entry.getValue().getClassName() + 
                                 " [Code: " + entry.getKey() + "]");
                index++;
            }
            System.out.println("-".repeat(60));
            System.out.println("Enter the class code to view register (or 'exit' to quit):");
        }
        
        /**
         * Handles the user interaction for selecting and viewing a class register.
         * Implements the main event sequence from the use case.
         */
        public void handleUserInteraction() {
            boolean running = true;
            
            while (running) {
                displayClasses();
                String input = scanner.nextLine().trim().toUpperCase();
                
                if (input.equalsIgnoreCase("exit")) {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    continue;
                }
                
                if (professorClasses.containsKey(input)) {
                    // Simulate clicking the "Register" button
                    System.out.println("\n" + "=".repeat(80));
                    System.out.println("CLICKING 'REGISTER' BUTTON FOR CLASS: " + input);
                    System.out.println("=".repeat(80));
                    
                    // Display the class register information (Event sequence step 1)
                    ClassRegister selectedClass = professorClasses.get(input);
                    selectedClass.displayRegister();
                    
                    // Simulate SMOS server connection (Postcondition)
                    SMOSServerConnection smosConnection = new SMOSServerConnection();
                    smosConnection.connect();
                    
                    System.out.println("\nPress Enter to return to class list...");
                    scanner.nextLine();
                    
                } else {
                    System.out.println("Invalid class code. Please try again.");
                }
            }
        }
        
        /**
         * Closes resources properly.
         */
        public void close() {
            scanner.close();
        }
    }
    
    /**
     * Main method - entry point of the program.
     * Creates the professor interface and starts the user interaction.
     */
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("CLASS REGISTRY VIEW SYSTEM - VIEWREGISTER USE CASE");
        System.out.println("=".repeat(80));
        
        // Simulate professor login (Precondition: user is logged in as teacher)
        System.out.println("\nSimulating professor login...");
        System.out.println("User logged in as: Professor Smith");
        
        // Create professor interface with sample data
        ProfessorInterface professorUI = new ProfessorInterface("Professor Smith");
        
        try {
            // Start the main user interaction loop
            professorUI.handleUserInteraction();
        } finally {
            // Ensure resources are closed
            professorUI.close();
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PROGRAM EXECUTION COMPLETE");
        System.out.println("=".repeat(80));
    }
}