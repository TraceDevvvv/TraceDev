import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * EditOfOnePage class implementing the main use case logic for editing a student's report card.
 * This class handles the complete workflow: displaying forms, capturing user input,
 * validating data, updating report cards, and displaying confirmation messages.
 * 
 * Implements the use case sequence:
 * 1. System displays form with fields to modify (including subject grades)
 * 2. User enters new data and clicks confirmation
 * 3. System displays confirmation message and shows the form with list of students
 */
public class EditOfOnePage {
    private Administrator administrator;
    private Scanner scanner;
    
    /**
     * Constructor for EditOfOnePage
     * 
     * @param administrator The administrator who will perform the edit operation
     */
    public EditOfOnePage(Administrator administrator) {
        this.administrator = administrator;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method to execute the EditOfOnePage use case
     * This method orchestrates the complete workflow for editing a student's report card
     * 
     * @return true if the use case completed successfully, false otherwise
     */
    public boolean executeEditUseCase() {
        System.out.println("\n=== Starting EditOfOnePage Use Case ===");
        
        // Precondition check: Administrator must be logged in
        if (!administrator.isLoggedIn()) {
            System.out.println("Error: Administrator must be logged in to perform this operation.");
            System.out.println("Precondition failed: User is not logged in as administrator.");
            return false;
        }
        
        // Precondition check: Administrator must have carried out 'DisplayedUnapagella'
        // and clicked the edit button (simulated by having students in the system)
        if (administrator.getStudentCount() == 0) {
            System.out.println("Error: No students available to edit.");
            System.out.println("Precondition failed: Must have carried out 'DisplayedUnapagella' first.");
            return false;
        }
        
        // Step 1: Display the form with list of students (simulating DisplayedUnapagella)
        System.out.println("\n--- Step 1: Displaying List of Students ---");
        displayStudentsList();
        
        // Step 2: Get student ID to edit
        System.out.println("\n--- Step 2: Select Student to Edit ---");
        String studentId = promptForStudentId();
        if (studentId == null) {
            return false;
        }
        
        // Step 3: Display current report card for the selected student
        System.out.println("\n--- Step 3: Current Report Card ---");
        Student student = administrator.findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found with ID: " + studentId);
            return false;
        }
        displayStudentReportCard(student);
        
        // Step 4: Display form with fields to modify
        System.out.println("\n--- Step 4: Edit Form ---");
        System.out.println("Enter new grades for the student (leave blank to keep current value).");
        System.out.println("Enter 'done' when finished entering grades.");
        
        // Step 5: Collect new grade data from user
        Map<String, Double> newGrades = collectNewGrades(student.getReportCard());
        
        // Step 6: Confirm and apply changes
        System.out.println("\n--- Step 6: Confirmation ---");
        if (confirmChanges(student, newGrades)) {
            // Apply the changes
            boolean success = administrator.editStudentReportCard(studentId, newGrades);
            
            if (success) {
                // Step 7: Display confirmation message
                System.out.println("\n✓✓✓ CONFIRMATION MESSAGE ✓✓✓");
                System.out.println("The report card for student " + student.getName() + 
                                 " (ID: " + studentId + ") has been successfully updated!");
                
                // Step 8: Display the form with list of students again
                System.out.println("\n--- Step 8: Updated List of Students ---");
                displayStudentsList();
                
                // Postcondition: Report has been modified
                System.out.println("\n✓ Postcondition satisfied: Report for student has been modified.");
                
                // Simulate user interrupting connection to SMOS server
                System.out.println("(Simulation: User interrupts connection to SMOS server)");
                
                return true;
            } else {
                System.out.println("Error: Failed to update report card. Please check if grades are valid (0-100).");
                return false;
            }
        } else {
            System.out.println("Edit operation cancelled by user.");
            return false;
        }
    }
    
    /**
     * Display the list of all students in the system
     * This simulates the 'DisplayedUnapagella' functionality
     */
    private void displayStudentsList() {
        System.out.println("List of Students:");
        System.out.println("=================");
        
        java.util.List<Student> students = administrator.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d. ID: %-10s Name: %-20s\n", 
                            i + 1, student.getStudentId(), student.getName());
        }
        System.out.println("=================");
    }
    
    /**
     * Prompt the user to enter a student ID for editing
     * 
     * @return The student ID entered by user, or null if cancelled
     */
    private String promptForStudentId() {
        System.out.print("Enter the Student ID to edit (or 'cancel' to abort): ");
        String input = scanner.nextLine().trim();
        
        if (input.equalsIgnoreCase("cancel")) {
            System.out.println("Operation cancelled.");
            return null;
        }
        
        // Validate student exists
        Student student = administrator.findStudentById(input);
        if (student == null) {
            System.out.println("Error: Student with ID '" + input + "' not found.");
            return promptForStudentId(); // Recursive retry
        }
        
        return input;
    }
    
    /**
     * Display the current report card for a student
     * 
     * @param student The student whose report card to display
     */
    private void displayStudentReportCard(Student student) {
        System.out.println("Student: " + student.getName() + " (ID: " + student.getStudentId() + ")");
        System.out.println(student.getReportCard().toString());
    }
    
    /**
     * Collect new grades from the user
     * 
     * @param currentReportCard The current report card to show existing grades
     * @return Map of subject names to new grade values
     */
    private Map<String, Double> collectNewGrades(ReportCard currentReportCard) {
        Map<String, Double> newGrades = new HashMap<>();
        Map<String, Double> currentGrades = currentReportCard.getAllGrades();
        
        if (currentGrades.isEmpty()) {
            System.out.println("No existing grades found. You can add new subjects.");
            System.out.println("Format: <subject_name> <grade> (grade must be 0-100)");
        } else {
            System.out.println("Current grades:");
            for (Map.Entry<String, Double> entry : currentGrades.entrySet()) {
                System.out.printf("  %s: %.2f\n", entry.getKey(), entry.getValue());
            }
            System.out.println("Enter subject name and new grade, or just subject name to remove.");
        }
        
        while (true) {
            System.out.print("\nEnter subject and grade (or 'done' to finish): ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            
            if (input.isEmpty()) {
                System.out.println("Please enter a subject name and grade.");
                continue;
            }
            
            // Parse input
            String[] parts = input.split("\\s+", 2);
            String subject = parts[0].trim();
            
            if (parts.length == 1) {
                // User wants to remove this subject
                System.out.println("Subject '" + subject + "' will be removed.");
                newGrades.put(subject, null); // Using null to indicate removal
                continue;
            }
            
            try {
                double grade = Double.parseDouble(parts[1].trim());
                
                // Validate grade range
                if (!ReportCard.isValidGrade(grade)) {
                    System.out.println("Error: Grade must be between 0 and 100. Please try again.");
                    continue;
                }
                
                newGrades.put(subject, grade);
                System.out.println("Added: " + subject + " = " + grade);
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid grade format. Please enter a number.");
            }
        }
        
        return newGrades;
    }
    
    /**
     * Display the proposed changes and ask for confirmation
     * 
     * @param student The student being edited
     * @param newGrades The new grades to apply
     * @return true if user confirms, false if user cancels
     */
    private boolean confirmChanges(Student student, Map<String, Double> newGrades) {
        if (newGrades.isEmpty()) {
            System.out.println("No changes to apply.");
            return false;
        }
        
        System.out.println("\nProposed changes for student: " + student.getName());
        System.out.println("==========================================");
        
        Map<String, Double> currentGrades = student.getReportCard().getAllGrades();
        
        for (Map.Entry<String, Double> entry : newGrades.entrySet()) {
            String subject = entry.getKey();
            Double newGrade = entry.getValue();
            Double currentGrade = currentGrades.get(subject);
            
            if (newGrade == null) {
                System.out.printf("  REMOVE: %s (current: %.2f)\n", subject, 
                                currentGrade != null ? currentGrade : 0.0);
            } else if (currentGrade == null) {
                System.out.printf("  ADD: %s = %.2f (new)\n", subject, newGrade);
            } else {
                System.out.printf("  UPDATE: %s: %.2f → %.2f\n", 
                                subject, currentGrade, newGrade);
            }
        }
        
        System.out.println("==========================================");
        
        while (true) {
            System.out.print("Confirm changes? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }
    
    /**
     * Close the scanner resource
     * Should be called when done with the EditOfOnePage instance
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Get the administrator associated with this EditOfOnePage instance
     * 
     * @return The administrator object
     */
    public Administrator getAdministrator() {
        return administrator;
    }
    
    /**
     * String representation of EditOfOnePage
     * 
     * @return String describing the EditOfOnePage instance
     */
    @Override
    public String toString() {
        return "EditOfOnePage [Administrator: " + administrator.getUsername() + 
               ", Logged in: " + administrator.isLoggedIn() + "]";
    }
}