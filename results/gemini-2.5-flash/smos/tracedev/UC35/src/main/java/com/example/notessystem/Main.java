package com.example.notessystem;

import com.example.notessystem.controller.StudentNotesController;
import com.example.notessystem.infrastructure.Database;
import com.example.notessystem.repository.impl.NoteRepositoryImpl;
import com.example.notessystem.service.AuthenticationService;
import com.example.notessystem.service.StudentNotesService;
import com.example.notessystem.service.StudentService;
import com.example.notessystem.view.StudentNotesView;

/**
 * Main application class to demonstrate the "View Student Notes" use case.
 * This class sets up the application context by wiring all dependencies
 * and then simulates the Administrator's interaction with the system.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Student Notes Application ---");

        // 1. Infrastructure Layer setup
        Database database = new Database();

        // 2. Data Access Layer setup
        NoteRepositoryImpl noteRepository = new NoteRepositoryImpl(database);

        // 3. Service Layer setup
        StudentNotesService studentNotesService = new StudentNotesService(noteRepository);
        AuthenticationService authenticationService = new AuthenticationService();
        StudentService studentService = new StudentService(database); // StudentService also uses Database

        // 4. Presentation Layer setup
        StudentNotesView studentNotesView = new StudentNotesView();
        // The controller manages the view and service.
        StudentNotesController studentNotesController = new StudentNotesController(
                studentNotesService,
                studentNotesView,
                authenticationService,
                studentService
        );

        // --- Simulate the Use Case: View Student Notes ---

        // Scenario 1: Successful Notes Retrieval for an existing student (S001)
        System.out.println("\n*** Scenario 1: Retrieve notes for student S001 ***");
        studentNotesView.requestStudentNotes("S001");

        // Scenario 2: Successful Notes Retrieval for another existing student (S002)
        System.out.println("\n*** Scenario 2: Retrieve notes for student S002 ***");
        studentNotesView.requestStudentNotes("S002");

        // Scenario 3: Simulate Connection Interruption / Error Path (for "errorStudent")
        System.out.println("\n*** Scenario 3: Simulate connection error for 'errorStudent' ***");
        studentNotesView.requestStudentNotes("errorStudent");

        // Scenario 4: Request notes for a non-existent student
        System.out.println("\n*** Scenario 4: Retrieve notes for non-existent student S999 ***");
        studentNotesView.requestStudentNotes("S999");

        System.out.println("\n--- Student Notes Application Finished ---");
    }
}