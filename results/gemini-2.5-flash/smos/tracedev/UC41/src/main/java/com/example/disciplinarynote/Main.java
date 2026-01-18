
package com.example.disciplinarynote;

import com.example.disciplinarynote.application.AuthenticationService;
import com.example.disciplinarynote.application.ChangeDisciplinaryNoteUseCase;
import com.example.disciplinarynote.dto.UpdateDisciplinaryNoteCommand;
import com.example.disciplinarynote.infrastructure.DataSource;
import com.example.disciplinarynote.infrastructure.DisciplinaryNoteRepositoryImpl;
import com.example.disciplinarynote.infrastructure.RepositoryConnectionException; // Added import for RepositoryConnectionException
import com.example.disciplinarynote.presentation.NoteController;
import com.example.disciplinarynote.presentation.NoteEditFormModel;
import com.example.disciplinarynote.presentation.NoteEditView;
import com.example.disciplinarynote.presentation.RegistryController;

import java.time.LocalDate;

/**
 * Main class to demonstrate the disciplinary note editing functionality.
 * This sets up the dependencies and simulates the user interaction flow
 * as described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Application Simulation ---");

        // 1. Infrastructure Layer Setup
        DataSource dataSource = new DataSource("jdbc:mysql://localhost:3306/dn_db");
        DisciplinaryNoteRepositoryImpl disciplinaryNoteRepository = new DisciplinaryNoteRepositoryImpl(dataSource);

        // 2. Application Layer Setup
        AuthenticationService authenticationService = new AuthenticationService();
        ChangeDisciplinaryNoteUseCase changeDisciplinaryNoteUseCase = new ChangeDisciplinaryNoteUseCase(disciplinaryNoteRepository);

        // 3. Presentation Layer Setup
        NoteEditFormModel noteEditFormModel = new NoteEditFormModel();
        RegistryController registryController = new RegistryController();
        NoteController noteController = new NoteController(changeDisciplinaryNoteUseCase, authenticationService, disciplinaryNoteRepository);
        // NoteEditView needs NoteController and RegistryController references
        NoteEditView noteEditView = new NoteEditView(noteEditFormModel, noteController, registryController);

        // Inject view and registry controller into NoteController
        noteController.setNoteEditView(noteEditView);
        noteController.setRegistryController(registryController);

        System.out.println("\n--- Scenario 1: Happy Path (Successful Save) ---");
        // Admin loads an existing note (simulated by Controller)
        String noteToEditId = "N001";
        noteController.showEditForm(noteToEditId);

        // Admin modifies fields (simulated by View)
        noteEditView.editFields("S001", "Late for class (updated description)", "T001", LocalDate.of(2023, 10, 26));

        // Admin clicks save (simulated by View, triggers Controller)
        noteEditView.clickSave();
        System.out.println("-------------------------------------\n");


        System.out.println("\n--- Scenario 2: Administrator cancels the operation ---");
        noteController.showEditForm("N002"); // Load another note
        noteEditView.editFields("S002", "Misconduct in hallway (attempted update)", "T002", LocalDate.of(2023, 10, 27));
        noteEditView.clickCancel(); // Admin cancels
        System.out.println("-------------------------------------\n");


        System.out.println("\n--- Scenario 3: Authentication failure ---");
        // To simulate authentication failure, we'll temporarily change the authenticationService
        // Normally, this would be handled by a login mechanism.
        // For this demo, let's create a temporary service or use a method to force failure.
        NoteController unauthenticatedController = new NoteController(changeDisciplinaryNoteUseCase, new AuthenticationService() {
            @Override
            public boolean checkLoggedIn() {
                System.out.println("[Application] AuthenticationService: Simulating UNAUTHENTICATED user.");
                return false; // Force unauthenticated state
            }
        }, disciplinaryNoteRepository);
        unauthenticatedController.setNoteEditView(noteEditView);
        unauthenticatedController.setRegistryController(registryController);

        unauthenticatedController.showEditForm("N001");
        noteEditView.editFields("S001", "Attempt to update without auth", "T001", LocalDate.of(2023, 10, 28));
        noteEditView.clickSave(); // This will go through the unauthenticatedController path
        System.out.println("-------------------------------------\n");

        System.out.println("\n--- Scenario 4: Repository connection interruption ---");
        // Configure repository to simulate connection error
        disciplinaryNoteRepository.setSimulateConnectionError(true);

        noteController.showEditForm("N002"); // Load N002
        noteEditView.editFields("S002", "Attempt to update during connection error", "T002", LocalDate.of(2023, 10, 29));
        noteEditView.clickSave(); // This should trigger the RepositoryConnectionException
        disciplinaryNoteRepository.setSimulateConnectionError(false); // Reset for other operations
        System.out.println("-------------------------------------\n");

        System.out.println("\n--- Final State Check (N001 and N002 after all scenarios) ---");
        try {
            System.out.println("Details for N001: " + disciplinaryNoteRepository.getNoteDetailsDto("N001"));
            System.out.println("Details for N002: " + disciplinaryNoteRepository.getNoteDetailsDto("N002"));
        } catch (RepositoryConnectionException e) {
            System.err.println("Could not fetch final state: " + e.getMessage());
        }

        System.out.println("\n--- Application Simulation Ended ---");
    }
}
