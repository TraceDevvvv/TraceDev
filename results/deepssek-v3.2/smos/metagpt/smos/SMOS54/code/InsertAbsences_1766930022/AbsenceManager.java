import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * AbsenceManager class manages students, displays them, records absences,
 * saves data to server, and sends notifications via EmailService.
 * This simulates the main business logic for the InsertAbsences use case.
 */
public class AbsenceManager {
    private List<Student> students;
    private EmailService emailService;
    private Scanner scanner;
    private boolean dataChanged = false;

    /**
     * Constructor initializes AbsenceManager with sample students.
     * In a real application, students would be loaded from a database.
     */
    public AbsenceManager() {
        this.students = new ArrayList<>();
        this.emailService = new EmailService();
        this.scanner = new Scanner(System.in);
        
        // Initialize with sample student data for demonstration
        initializeSampleStudents();
    }

    /**
     * Initializes sample students for demonstration.
     * In a real application, this would load from a database based on selected class.
     */
    private void initializeSampleStudents() {
        students.add(new Student(1, "John Smith", "parent.john@example.com"));
        students.add(new Student(2, "Emma Johnson", "parent.emma@example.com"));
        students.add(new Student(3, "Michael Brown", "parent.michael@example.com"));
        students.add(new Student(4, "Sophia Williams", "parent.sophia@example.com"));
        students.add(new Student(5, "David Miller", "parent.david@example.com"));
    }

    /**
     * Displays all students with their current absence status.
     * Simulates the screen showing pupils with radio buttons (present/absent).
     */
    public void displayStudents() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ATA CLASS - STUDENT ATTENDANCE RECORD");
        System.out.println("=".repeat(60));
        System.out.println("ID\tStudent Name\t\tParent Email\t\tStatus");
        System.out.println("-".repeat(60));
        
        for (Student student : students) {
            String status = student.isAbsent() ? "ABSENT" : "PRESENT";
            System.out.printf("%d\t%-20s\t%-25s\t%s%n", 
                student.getId(), 
                student.getName(),
                student.getParentEmail(),
                status);
        }
        System.out.println("=".repeat(60));
        System.out.println("Note: By default all students are marked as PRESENT.");
        System.out.println("Enter student IDs to mark as absent (comma-separated).");
        System.out.println("Type 'save' to save changes or 'cancel' to exit.");
    }

    /**
     * Prompts user to select absent students.
     * Simulates user selecting radio buttons for absent students.
     */
    public void selectAbsentStudents() {
        System.out.print("\nEnter student IDs to mark as absent (comma-separated, e.g., '1,3,5'): ");
        String input = scanner.nextLine().trim().toLowerCase();
        
        // Handle user commands
        if (input.equals("save")) {
            saveAbsences();
            return;
        } else if (input.equals("cancel")) {
            handleUserInterruption();
            return;
        }
        
        // Parse student IDs
        String[] idStrings = input.split(",");
        List<Integer> absentIds = new ArrayList<>();
        
        for (String idStr : idStrings) {
            try {
                int id = Integer.parseInt(idStr.trim());
                if (id > 0 && id <= students.size()) {
                    absentIds.add(id);
                } else {
                    System.out.println("Warning: Student ID " + id + " is not valid. Ignoring.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Warning: '" + idStr + "' is not a valid number. Ignoring.");
            }
        }
        
        // Update absence status for all students
        for (Student student : students) {
            if (absentIds.contains(student.getId())) {
                if (!student.isAbsent()) {
                    student.setAbsent(true);
                    dataChanged = true;
                }
            } else {
                if (student.isAbsent()) {
                    student.setAbsent(false);
                    dataChanged = true;
                }
            }
        }
        
        System.out.println("Attendance updated successfully.");
        displayStudents();
    }

    /**
     * Saves absence data to server and sends notifications for absent students.
     * This simulates the "Save" button click action.
     */
    public void saveAbsences() {
        if (!dataChanged) {
            System.out.println("No changes to save. Returning to main screen.");
            displayStudents();
            return;
        }
        
        System.out.println("\nSaving absence data to server...");
        
        boolean saveSuccessful = saveToServer();
        
        if (saveSuccessful) {
            System.out.println("✓ Data saved to server successfully.");
            sendNotifications();
            dataChanged = false; // Reset change flag
        } else {
            System.out.println("✗ Failed to save data to server. Please try again.");
        }
        
        // Return to initial screen (simulated)
        System.out.println("\nReturning to main screen...\n");
        displayStudents();
    }

    /**
     * Saves absence data to the server.
     * In a real application, this would make an API call to the server.
     * 
     * @return true if save successful, false otherwise
     */
    private boolean saveToServer() {
        try {
            // Simulate server communication with 90% success rate
            System.out.println("Connecting to ATA School Server...");
            Thread.sleep(500); // Simulate network delay
            
            // Simulate 10% chance of server save failure
            if (Math.random() < 0.1) {
                throw new Exception("Server timeout");
            }
            
            // Save each student's absence status
            for (Student student : students) {
                System.out.printf("  Saving: Student %d (%s) - %s%n",
                    student.getId(),
                    student.getName(),
                    student.isAbsent() ? "Absent" : "Present");
            }
            
            return true;
        } catch (InterruptedException e) {
            System.out.println("Save operation interrupted by user.");
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Sends notification emails to parents of absent students.
     * Uses EmailService to handle email sending.
     */
    private void sendNotifications() {
        System.out.println("\nSending absence notifications to parents...");
        
        List<Student> absentStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isAbsent()) {
                absentStudents.add(student);
            }
        }
        
        if (absentStudents.isEmpty()) {
            System.out.println("No absent students. No notifications to send.");
            return;
        }
        
        System.out.println("Found " + absentStudents.size() + " absent student(s).");
        
        int successfulEmails = 0;
        int failedEmails = 0;
        
        for (Student student : absentStudents) {
            try {
                boolean emailSent = emailService.sendAbsenceNotification(
                    student.getId(),
                    student.getName(),
                    student.getParentEmail()
                );
                
                if (emailSent) {
                    successfulEmails++;
                } else {
                    failedEmails++;
                    System.out.println("  Will retry failed email for " + student.getName() + " later.");
                }
                
            } catch (EmailService.EmailException e) {
                System.out.println("  ✗ Email error for " + student.getName() + ": " + e.getMessage());
                failedEmails++;
                
                // Try to reconnect and resend
                if (!emailService.isServerConnected()) {
                    System.out.println("  Attempting to reconnect to email server...");
                    if (emailService.reconnect()) {
                        // Try sending again after reconnection
                        try {
                            if (emailService.sendAbsenceNotification(
                                student.getId(),
                                student.getName(),
                                student.getParentEmail())) {
                                successfulEmails++;
                                failedEmails--;
                            }
                        } catch (EmailService.EmailException retryException) {
                            System.out.println("  ✗ Retry failed: " + retryException.getMessage());
                        }
                    }
                }
            }
        }
        
        System.out.println("\nNotification Summary:");
        System.out.println("  Successful: " + successfulEmails);
        System.out.println("  Failed: " + failedEmails);
        
        if (failedEmails > 0) {
            System.out.println("\nWarning: Some notifications failed to send.");
            System.out.println("Please check email server connection and try again.");
        }
    }

    /**
     * Handles user interruption of the operation.
     * Simulates user canceling the operation.
     */
    public void handleUserInterruption() {
        System.out.println("\nOperation interrupted by user.");
        
        if (dataChanged) {
            System.out.print("You have unsaved changes. Save before exiting? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes") || response.equals("y")) {
                saveAbsences();
            } else {
                System.out.println("Changes discarded. Returning to main screen.");
                dataChanged = false;
            }
        }
        
        // Return to initial screen
        displayStudents();
    }

    /**
     * Main loop for the absence management system.
     * Continues until user decides to exit.
     */
    public void run() {
        System.out.println("ATA School - Absence Management System");
        System.out.println("==========================================");
        
        displayStudents();
        
        // Main interaction loop
        while (true) {
            selectAbsentStudents();
        }
    }

    /**
     * Closes resources used by AbsenceManager.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Returns the list of students (for testing purposes).
     * 
     * @return List of students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Returns whether data has been changed (for testing purposes).
     * 
     * @return true if data changed, false otherwise
     */
    public boolean isDataChanged() {
        return dataChanged;
    }
}