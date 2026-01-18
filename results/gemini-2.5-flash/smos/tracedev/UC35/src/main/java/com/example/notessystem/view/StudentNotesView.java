package com.example.notessystem.view;

import com.example.notessystem.controller.StudentNotesController;
import com.example.notessystem.dto.NoteDisplayDto;

import java.util.List;

/**
 * Presentation Layer: Implements MVC/MVVM principles for UI management.
 * This class is responsible for displaying student notes to the user.
 */
public class StudentNotesView {
    // Represents the selected student's ID, satisfying R2'
    private String studentId;
    private StudentNotesController controller;

    /**
     * Sets the controller for this view. This allows the view to delegate user actions to the controller.
     * @param controller The StudentNotesController instance.
     */
    public void setController(StudentNotesController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of student notes in the UI (simulated by console output).
     * This is the final step in the successful notes retrieval sequence.
     * @param notes The list of {@link NoteDisplayDto} to display.
     */
    public void displayNotes(List<NoteDisplayDto> notes) {
        System.out.println("\nView: Displaying Notes for Student ID: " + studentId + " (View --> Administrator)");
        if (notes == null || notes.isEmpty()) {
            System.out.println("------------------------------------");
            System.out.println("No notes found for student ID: " + studentId);
            System.out.println("------------------------------------");
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("Student Notes:");
        for (NoteDisplayDto note : notes) {
            System.out.println("  ID: " + note.getNoteId());
            System.out.println("  Date: " + note.getDate());
            System.out.println("  Author: " + note.getAuthor());
            System.out.println("  Content: " + note.getContent());
            System.out.println("  ---");
        }
        System.out.println("------------------------------------");
        // Note: Exit Condition: system displaying notes
    }

    /**
     * Displays an error message in the UI (simulated by console output).
     * This is part of the alternative flow in the sequence diagram.
     * @param errorMessage The message describing the error.
     */
    public void displayErrorMessage(String errorMessage) {
        System.err.println("\nView: Displaying Error Message (View --x Administrator)");
        System.err.println("------------------------------------");
        System.err.println("Error: " + errorMessage);
        System.err.println("Unable to retrieve notes. Please try again later.");
        System.err.println("------------------------------------");
    }


    /**
     * Simulates an Administrator clicking the "Notes" button for a given student.
     * This is the entry point for the "View Student Notes" use case.
     * It sets the studentId and then delegates to the controller.
     * @param studentId The ID of the student whose notes are requested.
     */
    public void requestStudentNotes(String studentId) {
        this.studentId = studentId; // Represents the selected student's ID, satisfying R2'
        System.out.println("\nAdministrator -> View: clicks \"Notes\" button for studentId: " + studentId);

        // Note: Entry Conditions Met:
        // - Administrator logged in (R1) - simulated implicitly before this call.
        // - Student selected (R2) - studentId is provided.
        // - Notes list requested (R3) - implied by clicking the button.
        // (No direct modifications to sequence for these, as per R1, R2, R3 audit recommendations)

        if (controller == null) {
            System.err.println("View: Error: Controller is not set. Cannot process request.");
            return;
        }
        // View -> Controller call
        controller.requestStudentNotes(studentId);
    }
}