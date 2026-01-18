package com.example.insertnote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main program to simulate the InsertNote use case.
 * This program demonstrates the sequence of events for inserting a disciplinary note:
 * 1. Show form with fields (student, date, teacher, description)
 * 2. User fills the form
 * 3. User clicks "Save"
 * 4. System saves note and sends email notification to parent
 * 
 * Postconditions:
 * - Note data entered into system
 * - Notification sent to parent
 * - Returns to registry screen
 * - SMOS server connection interrupted
 */
public class InsertNoteProgram {
    
    // Serv used by the program
    private NoteService noteService;
    private EmailService emailService;
    private Scanner scanner;
    
    /**
     * Constructor initializes serv and scanner
     */
    public InsertNoteProgram() {
        this.noteService = new NoteService();
        this.emailService = new EmailService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method - entry point of the program
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== InsertNote Use Case Simulation ===");
        System.out.println("Preconditions checked:");
        System.out.println("1. User logged in as administrator ✓");
        System.out.println("2. 'SviewTetTingloregister' use case completed ✓");
        System.out.println("3. 'ViewElonconote' use case completed ✓");
        System.out.println("4. 'New Note' button clicked ✓\n");
        
        InsertNoteProgram program = new InsertNoteProgram();
        program.runUseCase();
        
        System.out.println("\n=== Use Case Completed Successfully ===");
    }
    
    /**
     * Runs the complete InsertNote use case sequence
     */
    private void runUseCase() {
        try {
            // Event 1: Show form with fields
            System.out.println("Event 1: System shows form with the following fields:");
            System.out.println("----------------------------------------");
            
            // Event 2: User fills out the form
            Note note = fillNoteForm();
            
            // Event 3: User clicks "Save"
            System.out.println("\nEvent 3: User clicks 'Save' button");
            
            // Event 4: Save the note (includes sending notification)
            System.out.println("\nEvent 4: System processing...");
            boolean success = noteService.saveNote(note);
            
            if (success) {
                System.out.println("✓ Note saved successfully!");
                System.out.println("✓ Email notification sent as part of save process!");
                
                // Postconditions
                handlePostconditions();
            } else {
                System.out.println("✗ Failed to save note. Please try again.");
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please restart the process with valid data.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            scanner.close();
            System.out.println("\nResources cleaned up.");
        }
    }
    
    /**
     * Simulates user filling out the note form
     * @return Note object with user-provided data
     * @throws IllegalArgumentException if validation fails
     */
    private Note fillNoteForm() throws IllegalArgumentException {
        System.out.println("\nEvent 2: User fills out the form");
        System.out.println("----------------------------------------");
        
        // Get student information
        System.out.print("Enter student name: ");
        String student = scanner.nextLine().trim();
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        
        // Get date with validation
        LocalDate date = null;
        while (date == null) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(dateStr);
                // Validate date is not in the future
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Error: Date cannot be in the future");
                    date = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use YYYY-MM-DD");
            }
        }
        
        // Get teacher information
        System.out.print("Enter teacher name: ");
        String teacher = scanner.nextLine().trim();
        if (teacher.isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be empty");
        }
        
        // Get description
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        
        // Create and return the note
        Note note = new Note(student, date, teacher, description);
        System.out.println("\nForm completed successfully!");
        System.out.println("Preview:");
        System.out.println(note);
        
        return note;
    }
    
    /**
     * Handles postconditions after note insertion
     */
    private void handlePostconditions() {
        System.out.println("\n=== Postconditions ===");
        System.out.println("1. Note data entered into system ✓");
        System.out.println("2. Notification sent to parent ✓");
        
        // Return to registry screen
        System.out.println("3. Returning to registry screen...");
        simulateRegistryScreen();
        
        // SMOS server connection interrupted
        System.out.println("4. SMOS server connection interrupted");
        interruptSMOSConnection();
    }
    
    /**
     * Simulates returning to registry screen
     */
    private void simulateRegistryScreen() {
        System.out.println("   Displaying registry screen with all disciplinary notes...");
        // In a real application, this would navigate to the registry screen
        try {
            Thread.sleep(1000); // Simulate brief delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   Registry screen loaded successfully ✓");
    }
    
    /**
     * Simulates interrupting SMOS server connection
     */
    private void interruptSMOSConnection() {
        System.out.println("   Interrupting connection to SMOS server...");
        // In a real application, this would close the SMOS connection
        try {
            Thread.sleep(500); // Simulate brief delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   SMOS server connection interrupted ✓");
    }
    
    /**
     * Getter for NoteService (for testing purposes)
     * @return NoteService instance
     */
    protected NoteService getNoteService() {
        return noteService;
    }
    
    /**
     * Getter for EmailService (for testing purposes)
     * @return EmailService instance
     */
    protected EmailService getEmailService() {
        return emailService;
    }
}
