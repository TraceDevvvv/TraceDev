'''
ViewRegister.java
Main program that simulates the "ViewRegister" use case.
Handles professor login, class selection, and display of registry information.
Also simulates SMOS server connection interruption.
'''
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class ViewRegister {
    /**
     * Data class representing a single entry in a class register.
     * Holds information about date, absences, disciplinary notes, delays, and justifications.
     */
    static class RegisterEntry {
        private String date;
        private String absences;
        private String disciplinaryNotes;
        private String delays;
        private String justification;
        /**
         * Constructor for a register entry.
         * @param date Date of the entry.
         * @param absences Number of absences.
         * @param disciplinaryNotes Disciplinary notes.
         * @param delays Number of delays.
         * @param justification Justification for absences/delays.
         */
        public RegisterEntry(String date, String absences, String disciplinaryNotes, String delays, String justification) {
            this.date = date;
            this.absences = absences;
            this.disciplinaryNotes = disciplinaryNotes;
            this.delays = delays;
            this.justification = justification;
        }
        /**
         * Converts the entry to a formatted string for display.
         * @return Formatted string representing the entry.
         */
        public String toDisplayString() {
            return String.format("Date: %s | Absences: %s | Disciplinary Notes: %s | Delays: %s | Justification: %s",
                    date, absences, disciplinaryNotes, delays, justification);
        }
        // Getters for table display
        public String getDate() { return date; }
        public String getAbsences() { return absences; }
        public String getDisciplinaryNotes() { return disciplinaryNotes; }
        public String getDelays() { return delays; }
        public String getJustification() { return justification; }
    }
    /**
     * Manages a collection of RegisterEntry objects for a specific class.
     * Simulates the data storage for a class register.
     */
    static class ClassRegistry {
        private String className;
        private List<RegisterEntry> entries;
        /**
         * Constructor for a class registry.
         * @param className Name of the class.
         */
        public ClassRegistry(String className) {
            this.className = className;
            this.entries = new ArrayList<>();
            // Populate with sample data for demonstration
            initializeSampleData();
        }
        /**
         * Initializes the registry with sample data for demonstration.
         */
        private void initializeSampleData() {
            entries.add(new RegisterEntry("2023-10-01", "2", "None", "1", "Traffic"));
            entries.add(new RegisterEntry("2023-10-02", "0", "Disruptive behavior", "0", "N/A"));
            entries.add(new RegisterEntry("2023-10-03", "1", "None", "3", "Public transport delay"));
            entries.add(new RegisterEntry("2023-10-04", "0", "Late submission", "2", "Family emergency"));
            entries.add(new RegisterEntry("2023-10-05", "3", "None", "0", "Medical appointment"));
        }
        /**
         * Retrieves all register entries for this class.
         * @return List of RegisterEntry objects.
         */
        public List<RegisterEntry> getAllEntries() {
            return entries;
        }
        /**
         * Gets the class name.
         * @return Name of the class.
         */
        public String getClassName() {
            return className;
        }
    }
    /**
     * Simulates the process of a professor viewing a class register.
     * This represents the main flow of the use case.
     */
    public static void viewClassRegister() {
        Scanner scanner = new Scanner(System.in);
        // Simulate preconditions: Professor is logged in and has viewed classes
        System.out.println("=== Professor Class Registry View ===");
        System.out.println("Status: Logged in as Professor.");
        System.out.println("Available Classes:");
        String[] classes = {"Mathematics 101", "Physics 201", "Chemistry 301"};
        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + ". " + classes[i] + " [Register]");
        }
        // User clicks "Register" button (simulated by selection)
        System.out.print("\nEnter the number of the class to view register (1-3): ");
        int choice = -1;
        // Handle invalid input gracefully
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            scanner.close();
            return;
        }
        if (choice < 1 || choice > 3) {
            System.out.println("Invalid selection. Please choose a number between 1 and 3.");
            scanner.close();
            return;
        }
        String selectedClass = classes[choice - 1];
        System.out.println("\n=== Loading Register for: " + selectedClass + " ===");
        // Simulate system displaying register information
        displayRegisterInformation(selectedClass);
        // Simulate postconditions: Information shown and SMOS server connection interrupted
        System.out.println("\n=== Postconditions ===");
        System.out.println("✓ Class registry information has been shown.");
        simulateSMOSServerInterruption();
        scanner.close();
    }
    /**
     * Displays the register information for a selected class.
     * @param className Name of the selected class.
     */
    private static void displayRegisterInformation(String className) {
        // Create a registry for the selected class
        ClassRegistry registry = new ClassRegistry(className);
        System.out.println("\nClass Register Details:");
        System.out.println("=".repeat(80));
        System.out.printf("%-12s %-10s %-20s %-8s %-20s%n", 
                         "Date", "Absences", "Disciplinary Notes", "Delays", "Justification");
        System.out.println("=".repeat(80));
        // Display each entry in the register
        for (RegisterEntry entry : registry.getAllEntries()) {
            System.out.printf("%-12s %-10s %-20s %-8s %-20s%n",
                    entry.getDate(),
                    entry.getAbsences(),
                    entry.getDisciplinaryNotes(),
                    entry.getDelays(),
                    entry.getJustification());
        }
        System.out.println("=".repeat(80));
    }
    /**
     * Simulates the interruption of connection to SMOS server.
     * This is part of the postconditions mentioned in the use case.
     */
    private static void simulateSMOSServerInterruption() {
        System.out.println("⚠  Connection to SMOS server interrupted.");
        System.out.println("   (Simulated: Network timeout after displaying data)");
    }
    /**
     * Main entry point of the program.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("Starting Class Registry View System...\n");
        try {
            viewClassRegister();
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            System.err.println("Simulating graceful shutdown...");
            simulateSMOSServerInterruption();
        }
        System.out.println("\n=== System Operation Complete ===");
    }
}