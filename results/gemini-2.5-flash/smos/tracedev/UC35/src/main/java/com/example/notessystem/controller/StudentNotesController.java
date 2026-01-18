package com.example.notessystem.controller;

import com.example.notessystem.dto.NoteDisplayDto;
import com.example.notessystem.service.AuthenticationService;
import com.example.notessystem.service.StudentNotesService;
import com.example.notessystem.service.StudentService;
import com.example.notessystem.view.StudentNotesView;
import com.example.notessystem.exception.ConnectionException;

import java.util.List;

/**
 * Presentation Layer: The controller handles user input and orchestrates the flow
 * for displaying student notes. It manages the {@link StudentNotesView} and
 * interacts with the {@link StudentNotesService}.
 */
public class StudentNotesController {

    private StudentNotesService studentNotesService;
    private StudentNotesView studentNotesView;
    private AuthenticationService authenticationService; // Uses authentication (R1)
    private StudentService studentService; // Uses StudentService (R2)

    /**
     * Constructs a StudentNotesController with its required dependencies.
     * @param studentNotesService The service responsible for note-related business logic.
     * @param studentNotesView The view responsible for displaying notes.
     * @param authenticationService The service for user authentication.
     * @param studentService The service for student management.
     */
    public StudentNotesController(StudentNotesService studentNotesService,
                                  StudentNotesView studentNotesView,
                                  AuthenticationService authenticationService,
                                  StudentService studentService) {
        this.studentNotesService = studentNotesService;
        this.studentNotesView = studentNotesView;
        this.authenticationService = authenticationService;
        this.studentService = studentService;
        this.studentNotesView.setController(this); // Injects this controller into the view
    }

    /**
     * Initiates the process of requesting and displaying student notes.
     * This method orchestrates calls to the service layer and updates the view.
     *
     * @param studentId The ID of the student whose notes are to be retrieved.
     */
    public void requestStudentNotes(String studentId) {
        System.out.println("Controller: requestStudentNotes received for studentId: " + studentId);

        // Precondition checks (as per R1, R2 notes on Class Diagram):
        // In a real application, userId would come from the session. For this simulation, we assume 'admin'.
        if (!authenticationService.isAuthenticated("admin")) {
            studentNotesView.displayErrorMessage("Authentication failed. Administrator not logged in.");
            return;
        }
        if (studentService.getStudentById(studentId) == null && !"errorStudent".equals(studentId)) { // Allow errorStudent for testing
            studentNotesView.displayErrorMessage("Student with ID '" + studentId + "' not found.");
            return;
        }


        try {
            // Controller -> Service call
            List<NoteDisplayDto> notes = studentNotesService.getStudentNotes(studentId);

            // Controller -> View call
            studentNotesView.displayNotes(notes);
        } catch (ConnectionException e) {
            // Service --x Controller and Controller --x View for error path
            System.err.println("Controller: Error: Failed to retrieve notes for student " + studentId + ". " + e.getMessage());
            studentNotesView.displayErrorMessage("Notes retrieval failed: " + e.getMessage());
        }
    }
}