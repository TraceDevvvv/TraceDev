package com.system.school;

import com.system.school.admin.Administrator;
import com.system.school.data.NoteArchive;
import com.system.school.integration.SMOSServer;
import com.system.school.note.DisciplinaryNote;
import com.system.school.notification.NotificationService;
import com.system.school.student.Student;
import com.system.school.usecase.DeleteNoteUseCase;

import java.time.LocalDate;

/**
 * Main class to demonstrate the DeleteNote use case.
 * This class sets up the necessary components and simulates the execution
 * of the use case under various scenarios.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Initialize core components
        NoteArchive noteArchive = new NoteArchive();
        NotificationService notificationService = new NotificationService();
        SMOSServer smosServer = new SMOSServer();

        // 2. Instantiate the DeleteNoteUseCase with its dependencies
        DeleteNoteUseCase deleteNoteUseCase = new DeleteNoteUseCase(noteArchive, notificationService, smosServer);

        // 3. Create sample data
        Administrator admin = new Administrator("ADM001", "admin.user", "admin@school.com");
        Student student1 = new Student("S001", "Alice", "Smith", 10, "alice.parent@example.com", "123-456-7890");
        Student student2 = new Student("S002", "Bob", "Johnson", 11, "bob.parent@example.com", "098-765-4321");
        Student student3NoParentEmail = new Student("S003", "Charlie", "Brown", 9, "", "555-111-2222");
        Student student4NoParentPhone = new Student("S004", "Diana", "Prince", 12, "diana.parent@example.com", "");
        Student student5NoParentContact = new Student("S005", "Eve", "Adams", 8, "", "");


        DisciplinaryNote note1 = new DisciplinaryNote("DN001", "S001", "Late for class multiple times", LocalDate.of(2023, 10, 26), "ADM001");
        DisciplinaryNote note2 = new DisciplinaryNote("DN002", "S002", "Disruption in library", LocalDate.of(2023, 11, 15), "ADM001");
        DisciplinaryNote note3 = new DisciplinaryNote("DN003", "S003", "Unexcused absence", LocalDate.of(2023, 09, 01), "ADM001");
        DisciplinaryNote note4 = new DisciplinaryNote("DN004", "S004", "Cheating on exam", LocalDate.of(2023, 12, 05), "ADM001");
        DisciplinaryNote note5 = new DisciplinaryNote("DN005", "S005", "Vandalism", LocalDate.of(2023, 10, 10), "ADM001");


        // 4. Populate the NoteArchive
        System.out.println("--- Initializing Note Archive ---");
        noteArchive.addStudent(student1);
        noteArchive.addStudent(student2);
        noteArchive.addStudent(student3NoParentEmail);
        noteArchive.addStudent(student4NoParentPhone);
        noteArchive.addStudent(student5NoParentContact);

        noteArchive.addNote(note1);
        noteArchive.addNote(note2);
        noteArchive.addNote(note3);
        noteArchive.addNote(note4);
        noteArchive.addNote(note5);
        System.out.println("Note Archive initialized with " + noteArchive.getNoteCount() + " notes and " + noteArchive.getStudentCount() + " students.\n");

        // Ensure SMOS server is connected before starting use case simulation
        smosServer.connect();
        System.out.println("SMOS Server connection status: " + smosServer.isConnected() + "\n");

        // --- Scenario 1: Successful Deletion ---
        System.out.println("===== SCENARIO 1: Successful Note Deletion =====");
        boolean success1 = deleteNoteUseCase.execute(admin, "DN001");
        System.out.println("Deletion of DN001 successful: " + success1);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n");
        System.out.println("SMOS Server connection status after scenario 1: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 2: Note Not Found ---
        System.out.println("===== SCENARIO 2: Note Not Found =====");
        boolean success2 = deleteNoteUseCase.execute(admin, "DN999");
        System.out.println("Deletion of DN999 successful: " + success2);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n");
        System.out.println("SMOS Server connection status after scenario 2: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 3: Student associated with note not found (edge case) ---
        // To simulate this, we'll add a note whose student ID doesn't exist in the archive
        DisciplinaryNote noteWithMissingStudent = new DisciplinaryNote("DN006", "S999", "Student does not exist", LocalDate.of(2023, 12, 10), "ADM001");
        noteArchive.addNote(noteWithMissingStudent);
        System.out.println("===== SCENARIO 3: Student for Note Not Found =====");
        boolean success3 = deleteNoteUseCase.execute(admin, "DN006");
        System.out.println("Deletion of DN006 successful: " + success3);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n"); // DN006 should still be there if deletion aborted
        System.out.println("SMOS Server connection status after scenario 3: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 4: Note with student having no parent email, but phone number exists ---
        System.out.println("===== SCENARIO 4: Note for Student with No Parent Email =====");
        boolean success4 = deleteNoteUseCase.execute(admin, "DN003"); // Student S003 has no parent email
        System.out.println("Deletion of DN003 successful: " + success4);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n");
        System.out.println("SMOS Server connection status after scenario 4: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 5: Note with student having no parent phone, but email exists ---
        System.out.println("===== SCENARIO 5: Note for Student with No Parent Phone =====");
        boolean success5 = deleteNoteUseCase.execute(admin, "DN004"); // Student S004 has no parent phone
        System.out.println("Deletion of DN004 successful: " + success5);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n");
        System.out.println("SMOS Server connection status after scenario 5: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 6: Note with student having no parent contact info ---
        System.out.println("===== SCENARIO 6: Note for Student with No Parent Contact Info =====");
        boolean success6 = deleteNoteUseCase.execute(admin, "DN005"); // Student S005 has no parent contact
        System.out.println("Deletion of DN005 successful: " + success6);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n");
        System.out.println("SMOS Server connection status after scenario 6: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 7: Invalid input (null note ID) ---
        System.out.println("===== SCENARIO 7: Invalid Input (Null Note ID) =====");
        boolean success7 = deleteNoteUseCase.execute(admin, null);
        System.out.println("Deletion with null note ID successful: " + success7);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n");
        System.out.println("SMOS Server connection status after scenario 7: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 8: Invalid input (empty note ID) ---
        System.out.println("===== SCENARIO 8: Invalid Input (Empty Note ID) =====");
        boolean success8 = deleteNoteUseCase.execute(admin, "   ");
        System.out.println("Deletion with empty note ID successful: " + success8);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n");
        System.out.println("SMOS Server connection status after scenario 8: " + smosServer.isConnected() + "\n"); // Should be false

        // Reconnect SMOS for next scenario
        smosServer.connect();

        // --- Scenario 9: Invalid input (null administrator) ---
        System.out.println("===== SCENARIO 9: Invalid Input (Null Administrator) =====");
        boolean success9 = deleteNoteUseCase.execute(null, "DN002"); // Try to delete note2 again
        System.out.println("Deletion with null administrator successful: " + success9);
        System.out.println("Remaining notes in archive: " + noteArchive.getNoteCount() + "\n"); // Note2 should still be there
        System.out.println("SMOS Server connection status after scenario 9: " + smosServer.isConnected() + "\n"); // Should be false

        // Final state check
        System.out.println("--- Final State ---");
        System.out.println("Total notes remaining: " + noteArchive.getNoteCount());
        System.out.println("Total students remaining: " + noteArchive.getStudentCount());
        System.out.println("SMOS Server final connection status: " + smosServer.isConnected());
    }
}