package com.smos.app;

import com.smos.model.Note;
import com.smos.model.Student;
import com.smos.server.SMOSServer;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class to demonstrate the "ViewNotesList" use case.
 * This application simulates an administrator logging in, selecting a student,
 * and viewing their associated notes from the SMOS (School Management and Operations System) server.
 */
public class ViewNotesListApp {

    private final SMOSServer smosServer;
    private final Scanner scanner;

    /**
     * Constructs a new ViewNotesListApp.
     * Initializes the SMOSServer and a Scanner for user input.
     */
    public ViewNotesListApp() {
        this.smosServer = new SMOSServer(); // Initialize the simulated backend server
        this.scanner = new Scanner(System.in); // Initialize scanner for console input
    }

    /**
     * Runs the application, demonstrating the use case.
     */
    public void run() {
        System.out.println("--- SMOS: View Student Notes Application ---");

        // Precondition: The user must be logged in to the system as an administrator.
        // Simulate administrator login
        System.out.println("\nAttempting Administrator Login...");
        boolean loggedIn = smosServer.authenticate("admin", "adminpass"); // Using dummy admin credentials

        if (!loggedIn) {
            System.err.println("Application terminated: Administrator login failed.");
            return;
        }

        // Simulate the administrator taking the case of use "SviewTetTingloregister"
        // and clicking on the "Notes" button associated with a student.
        System.out.println("\nAdministrator logged in successfully. Ready to view student notes.");

        // Prompt for student ID
        System.out.print("Enter Student ID to view notes (e.g., S001, S002, S003): ");
        String studentId = scanner.nextLine();

        // Retrieve student information for display purposes
        Student student = smosServer.getStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student with ID '" + studentId + "' not found.");
            // Even if student not found, getStudentNotes will handle it gracefully by returning an empty list.
            // We proceed to show that behavior.
        } else {
            System.out.println("Viewing notes for student: " + student.getName() + " (ID: " + student.getStudentId() + ")");
        }

        // Event sequence: System shows all the student notes recorded during the school year.
        List<Note> notes = smosServer.getStudentNotes(studentId);

        // Postcondition: The system is displaying the list notes referred to the student.
        if (notes != null && !notes.isEmpty()) {
            System.out.println("\n--- Notes for Student ID: " + studentId + " ---");
            for (Note note : notes) {
                System.out.println("  Note ID: " + note.getNoteId());
                System.out.println("  Date: " + note.getDate());
                System.out.println("  Content: " + note.getContent());
                System.out.println("------------------------------------");
            }
        } else if (notes != null) { // notes is not null but empty
            System.out.println("\nNo notes found for student ID: " + studentId);
        } else { // notes is null, indicating an error like not logged in (though checked above)
            System.err.println("\nFailed to retrieve notes. Please check server status or login.");
        }

        // Postcondition: The administrator interrupts the connection to the SMOS server.
        System.out.println("\nSimulating administrator interrupting the connection...");
        smosServer.interruptConnection();
        System.out.println("SMOS server connection interrupted. Application closing.");

        scanner.close(); // Close the scanner
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ViewNotesListApp app = new ViewNotesListApp();
        app.run();
    }
}