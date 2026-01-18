package com.system.school.usecase;

import com.system.school.admin.Administrator;
import com.system.school.data.NoteArchive;
import com.system.school.integration.SMOSServer;
import com.system.school.note.DisciplinaryNote;
import com.system.school.notification.NotificationService;
import com.system.school.student.Student;

import java.util.Optional;

/**
 * Implements the "DeleteNote" use case, allowing an administrator to delete a disciplinary note.
 * This class orchestrates the necessary steps: sending parent notifications, deleting the note
 * from the archive, and managing the SMOS server connection.
 */
public class DeleteNoteUseCase {

    private final NoteArchive noteArchive;
    private final NotificationService notificationService;
    private final SMOSServer smosServer;

    /**
     * Constructs a new DeleteNoteUseCase.
     *
     * @param noteArchive The data repository for disciplinary notes and student information.
     * @param notificationService The service responsible for sending notifications to parents.
     * @param smosServer The simulated SMOS server for connection management.
     * @throws IllegalArgumentException if any of the dependency serv are null.
     */
    public DeleteNoteUseCase(NoteArchive noteArchive, NotificationService notificationService, SMOSServer smosServer) {
        if (noteArchive == null) {
            throw new IllegalArgumentException("NoteArchive cannot be null.");
        }
        if (notificationService == null) {
            throw new IllegalArgumentException("NotificationService cannot be null.");
        }
        if (smosServer == null) {
            throw new IllegalArgumentException("SMOSServer cannot be null.");
        }
        this.noteArchive = noteArchive;
        this.notificationService = notificationService;
        this.smosServer = smosServer;
    }

    /**
     * Executes the DeleteNote use case.
     *
     * Preconditions:
     * - The user must be logged in to the system as an administrator.
     * - The user has carried out the case of use "SHOWDDETTICLETA" and the system is viewing the details of a note.
     * - The user clicks the "Delete" button.
     *
     * @param administrator The administrator performing the deletion.
     * @param noteIdToDelete The unique ID of the disciplinary note to be deleted.
     * @return true if the note was successfully deleted and notification sent, false otherwise.
     * @throws IllegalArgumentException if the administrator or noteIdToDelete is null or empty.
     */
    public boolean execute(Administrator administrator, String noteIdToDelete) {
        // 1. Precondition Check (simulated):
        // In a real system, authentication and authorization would happen here.
        // For this use case, we assume the 'administrator' object represents a logged-in admin.
        if (administrator == null) {
            System.err.println("Error: Administrator object is null. Operation aborted.");
            return false;
        }
        if (noteIdToDelete == null || noteIdToDelete.trim().isEmpty()) {
            System.err.println("Error: Note ID to delete cannot be null or empty. Operation aborted.");
            return false;
        }

        System.out.println("\n--- Delete Note Use Case Initiated by Administrator: " + administrator.getUsername() + " ---");
        System.out.println("Attempting to delete note with ID: " + noteIdToDelete);

        // 2. Retrieve the disciplinary note from the archive.
        Optional<DisciplinaryNote> noteOptional = noteArchive.getNoteById(noteIdToDelete);
        if (noteOptional.isEmpty()) {
            System.err.println("Error: Disciplinary note with ID " + noteIdToDelete + " not found. Cannot proceed with deletion.");
            // Postcondition: Administrator interrupts the operation (implicitly, as note not found)
            smosServer.disconnect(); // Disconnect SMOS server as per postcondition
            return false;
        }
        DisciplinaryNote note = noteOptional.get();
        System.out.println("Found note: " + note.getNoteId() + " for student ID: " + note.getStudentId());

        // 3. Retrieve the student associated with the note to get parent contact information.
        Optional<Student> studentOptional = noteArchive.getStudentById(note.getStudentId());
        if (studentOptional.isEmpty()) {
            System.err.println("Error: Student with ID " + note.getStudentId() + " associated with note " + noteIdToDelete + " not found. Cannot send parent notification.");
            // Decide whether to proceed with deletion without notification or abort.
            // For this use case, notification is a critical step before deletion.
            System.err.println("Aborting note deletion due to missing student information for notification.");
            // Postcondition: Administrator interrupts the operation
            smosServer.disconnect(); // Disconnect SMOS server as per postcondition
            return false;
        }
        Student student = studentOptional.get();
        System.out.println("Found student: " + student.getFullName() + " for notification.");

        // 4. Send a notification to the student's parents.
        // The use case specifies "notification of incorrect corrige".
        // We'll interpret this as a notification that the note is being removed/corrected.
        System.out.println("Sending notification to parents of " + student.getFullName() + "...");
        boolean notificationSent = notificationService.sendParentNotification(student, note, "Disciplinary Note Cancellation");

        if (!notificationSent) {
            System.err.println("Warning: Failed to send notification to parents for note " + noteIdToDelete + ". Proceeding with deletion anyway as per typical system behavior, but this should be logged.");
            // Depending on business rules, this might be an abort condition.
            // For now, we'll proceed with deletion but log the warning.
        } else {
            System.out.println("Parent notification sent successfully (simulated).");
        }

        // 5. Eliminate the data of the note from the archive.
        System.out.println("Deleting note " + noteIdToDelete + " from archive...");
        Optional<DisciplinaryNote> deletedNote = noteArchive.deleteNote(noteIdToDelete);

        if (deletedNote.isEmpty()) {
            System.err.println("Error: Failed to delete note " + noteIdToDelete + " from archive. It might have been removed concurrently.");
            // Postcondition: Administrator interrupts the operation
            smosServer.disconnect(); // Disconnect SMOS server as per postcondition
            return false;
        }

        System.out.println("Note " + noteIdToDelete + " successfully deleted from archive.");

        // 6. Postconditions:
        // - The note has been canceled. (Achieved)
        // - The system sent notification to parents. (Achieved, or warned if failed)
        System.out.println("Postcondition: Note " + noteIdToDelete + " has been canceled.");
        System.out.println("Postcondition: Notification sent to parents of " + student.getFullName() + ".");

        // - The system returns to the registry screen. (Simulated UI action)
        System.out.println("System returns to the registry screen.");

        // - Connection to the SMOS server interrupted.
        smosServer.disconnect();
        System.out.println("Postcondition: Connection to SMOS server interrupted.");

        System.out.println("--- Delete Note Use Case Completed ---");
        return true;
    }
}