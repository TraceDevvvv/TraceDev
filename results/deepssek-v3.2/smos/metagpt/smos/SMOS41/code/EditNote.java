import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main class for the EditNote application.
 * This program simulates the editing of a disciplinary note by an administrator.
 * It handles the complete use case including preconditions, events, and postconditions.
 */
public class EditNote {
    
    /**
     * Represents a disciplinary note with fields: student, description, teacher, and date.
     */
    static class Note {
        private String student;
        private String description;
        private String teacher;
        private LocalDate date;
        
        public Note(String student, String description, String teacher, LocalDate date) {
            this.student = student;
            this.description = description;
            this.teacher = teacher;
            this.date = date;
        }
        
        public String getStudent() { return student; }
        public void setStudent(String student) { this.student = student; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public String getTeacher() { return teacher; }
        public void setTeacher(String teacher) { this.teacher = teacher; }
        
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        
        @Override
        public String toString() {
            return "Note Details:\n" +
                   "  Student: " + student + "\n" +
                   "  Description: " + description + "\n" +
                   "  Teacher: " + teacher + "\n" +
                   "  Date: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }
    
    /**
     * Simulates the SMOS server connection.
     * In a real application, this would handle actual server communication.
     */
    static class SMOSServer {
        private boolean connected = true;
        
        public boolean isConnected() {
            return connected;
        }
        
        public void setConnected(boolean connected) {
            this.connected = connected;
        }
        
        /**
         * Simulates saving a note to the server.
         * @param note The note to save
         * @return true if save was successful, false otherwise
         */
        public boolean saveNote(Note note) {
            if (!connected) {
                System.out.println("Error: Connection to SMOS server interrupted.");
                return false;
            }
            
            // Simulate server save operation
            System.out.println("Saving note to SMOS server...");
            // In a real application, this would make an API call
            try {
                Thread.sleep(1000); // Simulate network delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            
            System.out.println("Note saved successfully.");
            return true;
        }
    }
    
    /**
     * Main system class that handles the EditNote use case.
     */
    static class EditNoteSystem {
        private Note currentNote;
        private boolean isAdminLoggedIn = false;
        private boolean hasViewedNote = false;
        private SMOSServer server;
        private Scanner scanner;
        
        public EditNoteSystem() {
            this.server = new SMOSServer();
            this.scanner = new Scanner(System.in);
        }
        
        /**
         * Simulates administrator login.
         * In a real application, this would validate credentials.
         */
        public void loginAsAdmin() {
            System.out.println("=== Administrator Login ===");
            System.out.print("Enter admin username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            // Simple validation - in real app, check against database
            if ("admin".equals(username) && "admin123".equals(password)) {
                isAdminLoggedIn = true;
                System.out.println("Login successful. Welcome, Administrator!");
            } else {
                System.out.println("Login failed. Invalid credentials.");
            }
        }
        
        /**
         * Simulates viewing a note (precondition for editing).
         */
        public void viewNoteDetails() {
            if (!isAdminLoggedIn) {
                System.out.println("Error: You must be logged in as administrator.");
                return;
            }
            
            // Create a sample note for demonstration
            currentNote = new Note(
                "John Doe",
                "Disruptive behavior in class",
                "Mr. Smith",
                LocalDate.of(2024, 3, 15)
            );
            
            System.out.println("\n=== Viewing Note Details ===");
            System.out.println(currentNote);
            hasViewedNote = true;
        }
        
        /**
         * Main method for editing a note.
         * Handles the complete event sequence.
         */
        public void editNote() {
            // Check preconditions
            if (!isAdminLoggedIn) {
                System.out.println("Error: You must be logged in as administrator to edit notes.");
                return;
            }
            
            if (!hasViewedNote) {
                System.out.println("Error: You must view the note details before editing.");
                return;
            }
            
            System.out.println("\n=== Edit Disciplinary Note ===");
            System.out.println("Current note details:");
            System.out.println(currentNote);
            System.out.println("\nEnter new values (press Enter to keep current value):");
            
            // Edit student field
            System.out.print("Student [" + currentNote.getStudent() + "]: ");
            String newStudent = scanner.nextLine();
            if (!newStudent.trim().isEmpty()) {
                currentNote.setStudent(newStudent);
            }
            
            // Edit description field
            System.out.print("Description [" + currentNote.getDescription() + "]: ");
            String newDescription = scanner.nextLine();
            if (!newDescription.trim().isEmpty()) {
                currentNote.setDescription(newDescription);
            }
            
            // Edit teacher field
            System.out.print("Teacher [" + currentNote.getTeacher() + "]: ");
            String newTeacher = scanner.nextLine();
            if (!newTeacher.trim().isEmpty()) {
                currentNote.setTeacher(newTeacher);
            }
            
            // Edit date field with validation
            boolean validDate = false;
            while (!validDate) {
                System.out.print("Date (YYYY-MM-DD) [" + currentNote.getDate() + "]: ");
                String newDateStr = scanner.nextLine();
                
                if (newDateStr.trim().isEmpty()) {
                    validDate = true; // Keep current date
                } else {
                    try {
                        LocalDate newDate = LocalDate.parse(newDateStr);
                        currentNote.setDate(newDate);
                        validDate = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
                    }
                }
            }
            
            // Handle save or interrupt
            System.out.println("\nOptions:");
            System.out.println("1. Save changes");
            System.out.println("2. Cancel (administrator interrupt)");
            System.out.print("Enter choice: ");
            
            String choice = scanner.nextLine();
            
            if ("1".equals(choice)) {
                saveChanges();
            } else {
                System.out.println("Operation cancelled by administrator.");
                // Postcondition: Return to registry screen
                returnToRegistry();
            }
        }
        
        /**
         * Saves the changes to the note.
         * Handles server connection interruptions.
         */
        private void saveChanges() {
            System.out.println("\nSaving changes...");
            
            // Simulate potential server connection interruption
            if (Math.random() < 0.1) { // 10% chance of interruption for demonstration
                server.setConnected(false);
            }
            
            boolean saveSuccessful = server.saveNote(currentNote);
            
            if (saveSuccessful) {
                System.out.println("Changes saved successfully!");
                // Postcondition: Note data has been modified
                System.out.println("\nUpdated note details:");
                System.out.println(currentNote);
            }
            
            // Postcondition: Return to registry screen
            returnToRegistry();
        }
        
        /**
         * Simulates returning to the registry screen.
         */
        private void returnToRegistry() {
            System.out.println("\nReturning to registry screen...");
            // In a real application, this would navigate to the registry UI
            System.out.println("=== Registry Screen ===");
            System.out.println("1. View another note");
            System.out.println("2. Edit another note");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
        }
        
        /**
         * Main menu for the system.
         */
        public void showMainMenu() {
            while (true) {
                System.out.println("\n=== Disciplinary Notes System ===");
                System.out.println("1. Login as Administrator");
                System.out.println("2. View Note Details");
                System.out.println("3. Edit Note");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        loginAsAdmin();
                        break;
                    case "2":
                        viewNoteDetails();
                        break;
                    case "3":
                        editNote();
                        break;
                    case "4":
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        
        /**
         * Closes resources.
         */
        public void close() {
            scanner.close();
        }
    }
    
    /**
     * Main method to run the EditNote application.
     */
    public static void main(String[] args) {
        System.out.println("=== EditNote Application ===");
        System.out.println("This program simulates editing a disciplinary note by an administrator.");
        
        EditNoteSystem system = new EditNoteSystem();
        
        try {
            system.showMainMenu();
        } finally {
            system.close();
        }
    }
}