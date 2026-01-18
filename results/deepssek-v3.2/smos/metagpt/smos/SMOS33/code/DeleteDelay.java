import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the DeleteDelay use case.
 * This program simulates the deletion of a student's late entry by an administrator.
 */
public class DeleteDelay {
    
    /**
     * Represents a student's late entry record in the system.
     */
    static class LateEntry {
        private String studentId;
        private String studentName;
        private LocalDate date;
        private String caseId;
        
        public LateEntry(String studentId, String studentName, LocalDate date, String caseId) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.date = date;
            this.caseId = caseId;
        }
        
        public String getStudentId() { return studentId; }
        public String getStudentName() { return studentName; }
        public LocalDate getDate() { return date; }
        public String getCaseId() { return caseId; }
        
        @Override
        public String toString() {
            return "Student: " + studentName + " (" + studentId + "), Date: " + 
                   date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + 
                   ", Case: " + caseId;
        }
    }
    
    /**
     * Represents the system's archive of late entries.
     */
    static class Archive {
        private List<LateEntry> entries;
        
        public Archive() {
            entries = new ArrayList<>();
            // Initialize with some sample data for demonstration
            entries.add(new LateEntry("S001", "John Doe", LocalDate.of(2024, 1, 15), "SplitTaTtAlloreGloregistration"));
            entries.add(new LateEntry("S002", "Jane Smith", LocalDate.of(2024, 1, 16), "SplitTaTtAlloreGloregistration"));
            entries.add(new LateEntry("S003", "Bob Johnson", LocalDate.of(2024, 1, 15), "SplitTaTtAlloreGloregistration"));
        }
        
        /**
         * Retrieves all late entries for a specific date and case.
         * @param date The date to filter by
         * @param caseId The case ID to filter by
         * @return List of matching late entries
         */
        public List<LateEntry> getEntriesByDateAndCase(LocalDate date, String caseId) {
            List<LateEntry> result = new ArrayList<>();
            for (LateEntry entry : entries) {
                if (entry.getDate().equals(date) && entry.getCaseId().equals(caseId)) {
                    result.add(entry);
                }
            }
            return result;
        }
        
        /**
         * Deletes a late entry from the archive.
         * @param entry The entry to delete
         * @return true if deletion was successful, false otherwise
         */
        public boolean deleteEntry(LateEntry entry) {
            return entries.remove(entry);
        }
        
        /**
         * Gets all entries in the archive (for debugging purposes).
         * @return List of all entries
         */
        public List<LateEntry> getAllEntries() {
            return new ArrayList<>(entries);
        }
    }
    
    /**
     * Represents the system's user authentication and session management.
     */
    static class AuthenticationSystem {
        private boolean isLoggedIn = false;
        private boolean isAdmin = false;
        
        /**
         * Simulates user login.
         * @param username The username
         * @param password The password
         * @return true if login successful, false otherwise
         */
        public boolean login(String username, String password) {
            // In a real system, this would validate against a database
            if ("admin".equals(username) && "admin123".equals(password)) {
                isLoggedIn = true;
                isAdmin = true;
                return true;
            }
            return false;
        }
        
        public boolean isLoggedIn() { return isLoggedIn; }
        public boolean isAdmin() { return isAdmin; }
        
        /**
         * Logs out the current user.
         */
        public void logout() {
            isLoggedIn = false;
            isAdmin = false;
        }
    }
    
    /**
     * Main method - entry point of the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationSystem auth = new AuthenticationSystem();
        Archive archive = new Archive();
        
        System.out.println("=== Delete Delay System ===");
        
        // Step 1: Administrator login (precondition)
        System.out.println("\nPlease login as administrator:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (!auth.login(username, password)) {
            System.out.println("Login failed. Only administrators can access this system.");
            return;
        }
        
        System.out.println("Login successful! Welcome, Administrator.");
        
        // Main program loop
        boolean running = true;
        while (running) {
            System.out.println("\n=== Registry Screen ===");
            System.out.println("1. View all late entries");
            System.out.println("2. Delete a delay");
            System.out.println("3. Logout and exit");
            System.out.print("Select option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    // View all entries
                    System.out.println("\nAll late entries in archive:");
                    List<LateEntry> allEntries = archive.getAllEntries();
                    if (allEntries.isEmpty()) {
                        System.out.println("No late entries found.");
                    } else {
                        for (int i = 0; i < allEntries.size(); i++) {
                            System.out.println((i + 1) + ". " + allEntries.get(i));
                        }
                    }
                    break;
                    
                case "2":
                    // Delete delay process
                    try {
                        System.out.println("\n=== Delete Delay Process ===");
                        
                        // Step 1: Select date (precondition)
                        System.out.print("Enter date to eliminate delay (yyyy-MM-dd): ");
                        String dateStr = scanner.nextLine();
                        LocalDate selectedDate;
                        
                        try {
                            selectedDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                            break;
                        }
                        
                        // Step 2: Update screen based on selected date (Event sequence step 1)
                        String caseId = "SplitTaTtAlloreGloregistration";
                        List<LateEntry> entriesForDate = archive.getEntriesByDateAndCase(selectedDate, caseId);
                        
                        System.out.println("\nLate entries for " + selectedDate + " in case '" + caseId + "':");
                        if (entriesForDate.isEmpty()) {
                            System.out.println("No late entries found for this date.");
                            break;
                        }
                        
                        for (int i = 0; i < entriesForDate.size(); i++) {
                            System.out.println((i + 1) + ". " + entriesForDate.get(i));
                        }
                        
                        // Step 3: Remove late input and save (Event sequence step 2)
                        System.out.print("\nEnter the number of the entry to delete (or 0 to cancel): ");
                        int entryChoice = Integer.parseInt(scanner.nextLine());
                        
                        if (entryChoice == 0) {
                            System.out.println("Operation cancelled by administrator.");
                            // Postcondition: Administrator interrupts the operation
                            break;
                        }
                        
                        if (entryChoice < 1 || entryChoice > entriesForDate.size()) {
                            System.out.println("Invalid selection.");
                            break;
                        }
                        
                        LateEntry selectedEntry = entriesForDate.get(entryChoice - 1);
                        
                        // Simulate potential server interruption
                        System.out.print("Simulate server interruption? (y/n): ");
                        String interrupt = scanner.nextLine();
                        if ("y".equalsIgnoreCase(interrupt)) {
                            System.out.println("ERROR: Connection to SMOS server interrupted!");
                            // Postcondition: Connection to SMOS server interrupted
                            break;
                        }
                        
                        // Step 4: Delete from archive (Event sequence step 3)
                        System.out.print("Confirm deletion of: " + selectedEntry + " (y/n): ");
                        String confirm = scanner.nextLine();
                        
                        if ("y".equalsIgnoreCase(confirm)) {
                            boolean deleted = archive.deleteEntry(selectedEntry);
                            if (deleted) {
                                System.out.println("Delay successfully eliminated!");
                                // Postcondition: The system has eliminated the delay
                            } else {
                                System.out.println("Failed to delete the entry.");
                            }
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                        
                        // Postcondition: The system remains on the registry screen
                        // (achieved by returning to the main loop)
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;
                    
                case "3":
                    // Logout and exit
                    auth.logout();
                    System.out.println("Logged out successfully. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
}