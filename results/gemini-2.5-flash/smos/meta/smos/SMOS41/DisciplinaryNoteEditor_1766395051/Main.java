package com.example.disciplinarynote;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main class to demonstrate the DisciplinaryNoteEditor functionality.
 * It simulates user interaction, administrator login, and various scenarios
 * including successful edits, unauthorized access, note not found,
 * invalid input, administrator interruption, and simulated server issues.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize serv
        InMemoryNoteRepository noteRepository = new InMemoryNoteRepository();
        MockAuthService authService = new MockAuthService(false); // Start with no admin logged in
        DisciplinaryNoteEditor editor = new DisciplinaryNoteEditor(authService, noteRepository);

        // Populate repository with some initial notes
        DisciplinaryNote note1 = new DisciplinaryNote("NOTE001", "Alice Smith", "Disruptive behavior in class.", "Mr. Johnson", LocalDate.of(2023, 10, 26));
        DisciplinaryNote note2 = new DisciplinaryNote("NOTE002", "Bob Williams", "Late submission of homework.", "Ms. Davis", LocalDate.of(2023, 10, 20));
        noteRepository.addNoteForSetup(note1);
        noteRepository.addNoteForSetup(note2);

        System.out.println("--- Initial Notes in System ---");
        noteRepository.getNoteById("NOTE001").ifPresent(System.out::println);
        noteRepository.getNoteById("NOTE002").ifPresent(System.out::println);
        System.out.println("-------------------------------\n");

        // Scenario 1: Attempt to edit without admin login (Precondition failure)
        System.out.println("--- Scenario 1: Attempt to edit without admin login ---");
        try {
            editor.editNote("NOTE001", "Alice Smith-Jones", null, null, null);
            System.out.println("ERROR: Should not have been able to edit without admin login.");
        } catch (SecurityException e) {
            System.out.println("SUCCESS: Caught expected exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Caught unexpected exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        System.out.println("------------------------------------------------------\n");

        // Simulate admin login
        authService.setAdminLoggedIn(true);
        System.out.println("Administrator logged in: " + authService.isAdminLoggedIn() + "\n");

        // Scenario 2: Successful Edit of a note
        System.out.println("--- Scenario 2: Successful Edit of Note001 ---");
        try {
            System.out.println("Before edit: " + noteRepository.getNoteById("NOTE001").orElse(null));
            DisciplinaryNote updatedNote = editor.editNote(
                    "NOTE001",
                    "Alice Smith-Jones", // Change student name
                    "Repeated disruptive behavior during lessons.", // Change description
                    "Mr. Johnson",
                    LocalDate.of(2023, 10, 27) // Change date
            );
            System.out.println("SUCCESS: Note edited. New details: " + updatedNote);
            System.out.println("After edit (from repository): " + noteRepository.getNoteById("NOTE001").orElse(null));
        } catch (Exception e) {
            System.out.println("ERROR: Failed to edit note: " + e.getMessage());
        }
        System.out.println("---------------------------------------------\n");

        // Scenario 3: Edit only one field (description) for Note002
        System.out.println("--- Scenario 3: Edit only description for Note002 ---");
        try {
            System.out.println("Before edit: " + noteRepository.getNoteById("NOTE002").orElse(null));
            DisciplinaryNote updatedNote = editor.editNote(
                    "NOTE002",
                    null, // No change to student name
                    "Late submission of homework for the third time this month.", // Change description
                    null, // No change to teacher name
                    null  // No change to date
            );
            System.out.println("SUCCESS: Note edited. New details: " + updatedNote);
            System.out.println("After edit (from repository): " + noteRepository.getNoteById("NOTE002").orElse(null));
        } catch (Exception e) {
            System.out.println("ERROR: Failed to edit note: " + e.getMessage());
        }
        System.out.println("---------------------------------------------------\n");

        // Scenario 4: Attempt to edit a non-existent note (Precondition failure - "view details")
        System.out.println("--- Scenario 4: Attempt to edit non-existent note ---");
        try {
            editor.editNote("NONEXISTENT", "Charlie Brown", "Imaginary incident.", "Ms. Frizzle", LocalDate.now());
            System.out.println("ERROR: Should not have been able to edit a non-existent note.");
        } catch (IllegalArgumentException e) {
            System.out.println("SUCCESS: Caught expected exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Caught unexpected exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        System.out.println("-----------------------------------------------------\n");

        // Scenario 5: Attempt to edit with invalid input for a field (e.g., empty student name)
        System.out.println("--- Scenario 5: Attempt to edit with invalid field input ---");
        try {
            editor.editNote("NOTE001", "", "Invalid student name attempt.", null, null);
            System.out.println("ERROR: Should not have been able to edit with empty student name.");
        } catch (IllegalArgumentException e) {
            System.out.println("SUCCESS: Caught expected exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Caught unexpected exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        System.out.println("----------------------------------------------------------\n");

        // Scenario 6: Administrator interrupts the operation
        System.out.println("--- Scenario 6: Administrator interrupts operation ---");
        editor.interruptOperation("NOTE002");
        // Verify that no changes were saved if an interruption occurred before saving
        // (This scenario is more about acknowledging the action than state change)
        System.out.println("Note002 state after interruption simulation: " + noteRepository.getNoteById("NOTE002").orElse(null));
        System.out.println("----------------------------------------------------\n");

        // Scenario 7: Simulate "Connection to the SMOS server interrupted" (Edge Case)
        System.out.println("--- Scenario 7: Simulate SMOS server interruption ---");
        int attempts = 0;
        boolean interrupted = false;
        DisciplinaryNote originalNote = noteRepository.getNoteById("NOTE001").get(); // Get current state
        System.out.println("Original Note001 state: " + originalNote);

        while (!interrupted && attempts < 100) { // Try up to 100 times to hit the random error
            attempts++;
            try {
                System.out.println("Attempt " + attempts + ": Trying to edit Note001 with new date...");
                editor.editNote(
                        "NOTE001",
                        null,
                        null,
                        null,
                        LocalDate.of(2024, 1, attempts % 28 + 1) // Change date to trigger save
                );
                System.out.println("Attempt " + attempts + ": Edit successful. (No interruption)");
            } catch (IllegalStateException e) {
                System.out.println("SUCCESS: Caught expected exception on attempt " + attempts + ": " + e.getMessage());
                interrupted = true;
            } catch (Exception e) {
                System.out.println("ERROR: Caught unexpected exception on attempt " + attempts + ": " + e.getClass().getSimpleName() + " - " + e.getMessage());
                break;
            }
        }

        if (!interrupted) {
            System.out.println("WARNING: SMOS server interruption was not simulated within " + attempts + " attempts. (This is due to random chance).");
        }
        // Verify that the note's state might be unchanged if the interruption happened before save,
        // or changed if it happened after save but before returning.
        // In this simulation, the exception is thrown *before* the save, so the note should revert.
        // However, the editor applies changes to the object *before* saving, so the object itself
        // would be modified, but the repository would not reflect it.
        // For this specific simulation, the exception is thrown *before* `noteRepository.saveNote`.
        // So, the repository's state should remain the same as before the failed edit attempt.
        System.out.println("Note001 state after potential interruption: " + noteRepository.getNoteById("NOTE001").orElse(null));
        System.out.println("---------------------------------------------------\n");

        // Final state of notes
        System.out.println("--- Final Notes in System ---");
        noteRepository.getNoteById("NOTE001").ifPresent(System.out::println);
        noteRepository.getNoteById("NOTE002").ifPresent(System.out::println);
        System.out.println("-----------------------------\n");
    }
}