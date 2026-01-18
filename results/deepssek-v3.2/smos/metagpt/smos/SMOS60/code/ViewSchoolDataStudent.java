import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the ViewSchoolDataStudent application.
 * This program simulates a student viewing their class registry data
 * after clicking the "Digital Log" button in a school system.
 * It demonstrates searching archive data and displaying student information.
 */
public class ViewSchoolDataStudent {
    
    /**
     * Represents a single entry in a student's class registry.
     * Contains data for a specific date including absences, notes, delays, and justifications.
     */
    static class RegistryEntry {
        private LocalDate date;
        private boolean absent;
        private String disciplinaryNote;
        private int delayMinutes;
        private String justification;
        
        public RegistryEntry(LocalDate date, boolean absent, String disciplinaryNote, 
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
            return String.format("Date: %s | Absent: %s | Delay: %d min | Note: %s | Justification: %s",
                date, absent ? "Yes" : "No", delayMinutes, 
                disciplinaryNote.isEmpty() ? "None" : disciplinaryNote,
                justification.isEmpty() ? "None" : justification);
        }
    }
    
    /**
     * Represents a student's class registry containing multiple entries.
     * Simulates the data that would be retrieved from the school archive.
     */
    static class ClassRegister {
        private String studentId;
        private String className;
        private List<RegistryEntry> entries;
        
        public ClassRegister(String studentId, String className) {
            this.studentId = studentId;
            this.className = className;
            this.entries = new ArrayList<>();
        }
        
        public void addEntry(RegistryEntry entry) {
            entries.add(entry);
        }
        
        public List<RegistryEntry> getEntries() {
            return new ArrayList<>(entries); // Return copy for encapsulation
        }
        
        public String getStudentId() { return studentId; }
        public String getClassName() { return className; }
        
        /**
         * Calculates total absences from all registry entries.
         * @return count of days marked as absent
         */
        public int getTotalAbsences() {
            return (int) entries.stream().filter(RegistryEntry::isAbsent).count();
        }
        
        /**
         * Calculates total delay minutes from all registry entries.
         * @return sum of all delay minutes
         */
        public int getTotalDelayMinutes() {
            return entries.stream().mapToInt(RegistryEntry::getDelayMinutes).sum();
        }
        
        /**
         * Gets all disciplinary notes (non-empty ones).
         * @return list of disciplinary notes
         */
        public List<String> getDisciplinaryNotes() {
            List<String> notes = new ArrayList<>();
            for (RegistryEntry entry : entries) {
                if (!entry.getDisciplinaryNote().isEmpty()) {
                    notes.add(entry.getDisciplinaryNote());
                }
            }
            return notes;
        }
    }
    
    /**
     * Simulates the SMOS server connection and data retrieval.
     * In a real system, this would connect to an actual server.
     */
    static class SMOSServerSimulator {
        private boolean connected = false;
        
        /**
         * Simulates connecting to the SMOS server.
         * @return true if connection successful
         */
        public boolean connect() {
            System.out.println("Connecting to SMOS server...");
            // Simulate connection attempt
            try {
                Thread.sleep(500); // Simulate network delay
                connected = true;
                System.out.println("Connected to SMOS server successfully.");
                return true;
            } catch (InterruptedException e) {
                System.out.println("Connection interrupted.");
                return false;
            }
        }
        
        /**
         * Simulates searching archive for class register data.
         * In reality, this would query a database or external service.
         * @param studentId the student ID to search for
         * @return ClassRegister object with simulated data
         */
        public ClassRegister searchClassRegister(String studentId) {
            if (!connected) {
                System.out.println("Error: Not connected to SMOS server.");
                return null;
            }
            
            System.out.println("Searching archive for class register data...");
            
            // Simulate search delay
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Search interrupted.");
                return null;
            }
            
            // Create simulated data for demonstration
            ClassRegister register = new ClassRegister(studentId, "10th Grade - Class A");
            
            // Add sample entries for the past 5 days
            LocalDate today = LocalDate.now();
            register.addEntry(new RegistryEntry(today.minusDays(4), false, "", 0, ""));
            register.addEntry(new RegistryEntry(today.minusDays(3), true, "", 0, "Medical appointment"));
            register.addEntry(new RegistryEntry(today.minusDays(2), false, "Disrupted class", 15, "Traffic"));
            register.addEntry(new RegistryEntry(today.minusDays(1), false, "", 5, ""));
            register.addEntry(new RegistryEntry(today, false, "", 0, ""));
            
            return register;
        }
        
        /**
         * Simulates disconnecting from the SMOS server.
         * This fulfills the postcondition about interrupting the connection.
         */
        public void disconnect() {
            if (connected) {
                System.out.println("Disconnecting from SMOS server...");
                connected = false;
                System.out.println("Connection to SMOS server interrupted.");
            }
        }
        
        public boolean isConnected() {
            return connected;
        }
    }
    
    /**
     * Main application logic that orchestrates the use case flow.
     */
    static class ViewSchoolDataStudentApp {
        private SMOSServerSimulator server;
        private Scanner scanner;
        
        public ViewSchoolDataStudentApp() {
            this.server = new SMOSServerSimulator();
            this.scanner = new Scanner(System.in);
        }
        
        /**
         * Main workflow for the ViewSchoolDataStudent use case.
         * Implements the sequence of events from the use case description.
         */
        public void run() {
            System.out.println("=== View School Data (Student) ===");
            
            // Precondition: User is logged in as student
            System.out.println("Precondition: User is logged in as student.");
            System.out.println("Simulating click on 'Digital Log' button...\n");
            
            // Step 1: Connect to server and search archive
            if (!server.connect()) {
                System.out.println("Failed to connect to server. Exiting.");
                return;
            }
            
            // In a real system, the student ID would come from the logged-in user session
            String studentId = "STU2024001";
            System.out.println("Searching data for student ID: " + studentId);
            
            // Event 1: Search the archive for class register data
            ClassRegister register = server.searchClassRegister(studentId);
            
            if (register == null) {
                System.out.println("Failed to retrieve class register data.");
                server.disconnect();
                return;
            }
            
            // Event 2: Display the student data
            displayStudentData(register);
            
            // Postcondition: Connection to SMOS server interrupted
            server.disconnect();
            
            System.out.println("\n=== Process Complete ===");
            System.out.println("Postcondition: Student's class registry data was shown.");
            System.out.println("Postcondition: Connection to SMOS server interrupted.");
        }
        
        /**
         * Displays the student data as specified in the use case.
         * Shows date, absences, disciplinary notes, delays and justification for each entry.
         * @param register the class register containing student data
         */
        private void displayStudentData(ClassRegister register) {
            System.out.println("\n=== STUDENT CLASS REGISTRY DATA ===");
            System.out.println("Student ID: " + register.getStudentId());
            System.out.println("Class: " + register.getClassName());
            System.out.println("====================================\n");
            
            List<RegistryEntry> entries = register.getEntries();
            
            if (entries.isEmpty()) {
                System.out.println("No registry data found for this student.");
                return;
            }
            
            // Display individual entries
            System.out.println("DETAILED ENTRIES:");
            System.out.println("-----------------");
            for (RegistryEntry entry : entries) {
                System.out.println(entry);
            }
            
            // Display summary statistics
            System.out.println("\nSUMMARY:");
            System.out.println("--------");
            System.out.println("Total entries: " + entries.size());
            System.out.println("Total absences: " + register.getTotalAbsences());
            System.out.println("Total delay minutes: " + register.getTotalDelayMinutes());
            
            List<String> notes = register.getDisciplinaryNotes();
            System.out.println("Disciplinary notes count: " + notes.size());
            if (!notes.isEmpty()) {
                System.out.println("Disciplinary notes:");
                for (String note : notes) {
                    System.out.println("  - " + note);
                }
            }
            
            // Display entries with justifications
            System.out.println("\nEntries with justifications:");
            boolean foundJustifications = false;
            for (RegistryEntry entry : entries) {
                if (!entry.getJustification().isEmpty()) {
                    System.out.println("  - " + entry.getDate() + ": " + entry.getJustification());
                    foundJustifications = true;
                }
            }
            if (!foundJustifications) {
                System.out.println("  None");
            }
        }
        
        /**
         * Clean up resources.
         */
        public void cleanup() {
            scanner.close();
            if (server.isConnected()) {
                server.disconnect();
            }
        }
    }
    
    /**
     * Main method - entry point of the application.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ViewSchoolDataStudentApp app = new ViewSchoolDataStudentApp();
        
        try {
            app.run();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            app.cleanup();
        }
    }
}