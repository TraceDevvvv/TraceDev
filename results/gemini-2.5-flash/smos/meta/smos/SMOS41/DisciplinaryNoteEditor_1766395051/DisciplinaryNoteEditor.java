package com.example.disciplinarynote;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Interface for user authentication serv.
 * In a real application, this would interact with a security framework.
 */
interface AuthService {
    /**
     * Checks if the current user is logged in as an administrator.
     * @return true if an administrator is logged in, false otherwise.
     */
    boolean isAdminLoggedIn();
}

/**
 * Interface for managing DisciplinaryNote persistence.
 * In a real application, this would interact with a database.
 */
interface NoteRepository {
    /**
     * Retrieves a disciplinary note by its unique ID.
     * @param noteId The unique identifier of the note.
     * @return An Optional containing the DisciplinaryNote if found, or an empty Optional if not found.
     */
    Optional<DisciplinaryNote> getNoteById(String noteId);

    /**
     * Saves or updates a disciplinary note.
     * If a note with the same ID already exists, it will be updated. Otherwise, it will be added.
     * @param note The DisciplinaryNote to save.
     */
    void saveNote(DisciplinaryNote note);

    /**
     * Deletes a disciplinary note by its unique ID.
     * @param noteId The unique identifier of the note to delete.
     * @return true if the note was found and deleted, false otherwise.
     */
    boolean deleteNote(String noteId);
}

/**
 * In-memory implementation of NoteRepository for demonstration purposes.
 * Simulates a database by storing notes in a HashMap.
 */
class InMemoryNoteRepository implements NoteRepository {
    private final Map<String, DisciplinaryNote> notes = new HashMap<>();

    @Override
    public Optional<DisciplinaryNote> getNoteById(String noteId) {
        return Optional.ofNullable(notes.get(noteId));
    }

    @Override
    public void saveNote(DisciplinaryNote note) {
        notes.put(note.getNoteId(), note);
        System.out.println("Repository: Note '" + note.getNoteId() + "' saved/updated.");
    }

    @Override
    public boolean deleteNote(String noteId) {
        return notes.remove(noteId) != null;
    }

    /**
     * Adds a note directly to the repository for initial setup.
     * @param note The note to add.
     */
    public void addNoteForSetup(DisciplinaryNote note) {
        notes.put(note.getNoteId(), note);
    }
}

/**
 * In-memory implementation of AuthService for demonstration purposes.
 * Can be configured to simulate an administrator being logged in or not.
 */
class MockAuthService implements AuthService {
    private boolean adminLoggedIn;

    public MockAuthService(boolean adminLoggedIn) {
        this.adminLoggedIn = adminLoggedIn;
    }

    @Override
    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

    /**
     * Allows changing the login status for testing.
     * @param adminLoggedIn The new login status.
     */
    public void setAdminLoggedIn(boolean adminLoggedIn) {
        this.adminLoggedIn = adminLoggedIn;
    }
}

/**
 * Manages the editing of disciplinary notes.
 * This class encapsulates the business logic for the "EditNote" use case.
 */
public class DisciplinaryNoteEditor {
    private final AuthService authService;
    private final NoteRepository noteRepository;

    /**
     * Constructs a DisciplinaryNoteEditor with the necessary serv.
     * @param authService The authentication service to check administrator status.
     * @param noteRepository The repository for accessing and storing disciplinary notes.
     */
    public DisciplinaryNoteEditor(AuthService authService, NoteRepository noteRepository) {
        this.authService = authService;
        this.noteRepository = noteRepository;
    }

    /**
     * Edits an existing disciplinary note.
     * This method performs the following steps:
     * 1. Checks if an administrator is logged in (precondition).
     * 2. Retrieves the note to be edited.
     * 3. Applies the changes to the note's fields.
     * 4. Saves the updated note (event sequence step 3).
     *
     * @param noteId The unique identifier of the note to edit.
     * @param newStudentName The new student name (can be null or empty if not changing).
     * @param newDescription The new description (can be null or empty if not changing).
     * @param newTeacherName The new teacher name (can be null or empty if not changing).
     * @param newNoteDate The new note date (can be null if not changing).
     * @return The updated DisciplinaryNote object if successful.
     * @throws SecurityException if the user is not logged in as an administrator.
     * @throws IllegalArgumentException if the noteId is invalid or the note is not found,
     *                                  or if any provided new field values are invalid.
     * @throws IllegalStateException if there's an issue with the SMOS server connection (simulated).
     */
    public DisciplinaryNote editNote(String noteId, String newStudentName, String newDescription,
                                     String newTeacherName, LocalDate newNoteDate)
            throws SecurityException, IllegalArgumentException, IllegalStateException {

        // Precondition 1: The user must be logged in to the system as an administrator
        if (!authService.isAdminLoggedIn()) {
            throw new SecurityException("Access Denied: User is not logged in as an administrator.");
        }

        // Precondition 2: The user has held the case of use "view details"
        // This implies the noteId is valid and the note exists.
        if (noteId == null || noteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Note ID cannot be null or empty.");
        }

        Optional<DisciplinaryNote> existingNoteOptional = noteRepository.getNoteById(noteId);

        if (existingNoteOptional.isEmpty()) {
            throw new IllegalArgumentException("Disciplinary note with ID '" + noteId + "' not found.");
        }

        DisciplinaryNote noteToEdit = existingNoteOptional.get();
        System.out.println("Editor: Retrieved note for editing: " + noteToEdit);

        // Event sequence step 1: The user edits one or more fields of the displayed form
        // Apply changes only if new values are provided and are different from current values.
        // Input validation is handled by DisciplinaryNote setters.
        try {
            if (newStudentName != null && !newStudentName.trim().isEmpty() && !newStudentName.equals(noteToEdit.getStudentName())) {
                noteToEdit.setStudentName(newStudentName);
            }
            if (newDescription != null && !newDescription.trim().isEmpty() && !newDescription.equals(noteToEdit.getDescription())) {
                noteToEdit.setDescription(newDescription);
            }
            if (newTeacherName != null && !newTeacherName.trim().isEmpty() && !newTeacherName.equals(noteToEdit.getTeacherName())) {
                noteToEdit.setTeacherName(newTeacherName);
            }
            if (newNoteDate != null && !newNoteDate.equals(noteToEdit.getNoteDate())) {
                noteToEdit.setNoteDate(newNoteDate);
            }
        } catch (IllegalArgumentException e) {
            // Re-throw validation errors from DisciplinaryNote setters
            throw new IllegalArgumentException("Invalid input for note field: " + e.getMessage(), e);
        }


        // Simulate "Connection to the SMOS server interrupted" edge case
        // In a real scenario, this would be a network or database connection error.
        // For simulation, we can randomly throw it or based on a specific condition.
        // Here, we'll simulate it as a potential failure during save.
        if (Math.random() < 0.05) { // 5% chance of connection interruption
            throw new IllegalStateException("Connection to the SMOS server interrupted during save operation.");
        }

        // Event sequence step 3: The system saves the changes
        noteRepository.saveNote(noteToEdit);
        System.out.println("Editor: Note '" + noteId + "' successfully updated.");

        // Postcondition: Note data has been modified.
        // The system returns to the registry screen (simulated by returning the updated note).
        return noteToEdit;
    }

    /**
     * Simulates the administrator interrupting the operation.
     * This method doesn't perform any changes but acknowledges the interruption.
     * @param noteId The ID of the note being edited (for context).
     */
    public void interruptOperation(String noteId) {
        System.out.println("Editor: Administrator interrupted the operation for note ID: " + noteId);
        // Postcondition: The administrator interrupts the operation
        // No changes are saved.
    }
}