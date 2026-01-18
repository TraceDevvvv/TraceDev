'''
Manages the storage and retrieval of disciplinary notes.
It simulates an archive using an in-memory list of `DisciplinaryNote` objects.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Service for managing a collection of Disciplinary Notes.
 * Simulates a data archive using an in-memory list.
 */
class ArchiveService {
    private List<DisciplinaryNote> notes;
    private int nextId = 1; // Simple ID generation
    /**
     * Constructs an ArchiveService and initializes the notes list.
     */
    public ArchiveService() {
        this.notes = new ArrayList<>();
    }
    /**
     * Adds a new disciplinary note to the archive.
     *
     * @param note The DisciplinaryNote object to add.
     */
    public void addNote(DisciplinaryNote note) {
        // Assign a unique ID if the note doesn't have one or if it's 0 (indicating new)
        if (note.getId() == 0) { // Assuming 0 means it's a new note to be assigned an ID
            this.notes.add(new DisciplinaryNote(nextId++, note.getStudentId(), note.getParentContactInfo(), note.getNoteDetails()));
        } else {
            // If ID is provided, ensure it's unique, or just add
            if (!notes.stream().anyMatch(n -> n.getId() == note.getId())) {
                this.notes.add(note);
                if (note.getId() >= nextId) { // Adjust nextId if a higher ID was externally provided
                    nextId = note.getId() + 1;
                }
            } else {
                System.err.println("Error: Note with ID " + note.getId() + " already exists. Not adding.");
            }
        }
        System.out.println("Note added: " + note.toString());
    }
    /**
     * Retrieves a disciplinary note by its unique ID.
     *
     * @param noteId The ID of the note to retrieve.
     * @return An Optional containing the DisciplinaryNote if found, or an empty Optional otherwise.
     */
    public Optional<DisciplinaryNote> getNoteById(int noteId) {
        return notes.stream()
                .filter(note -> note.getId() == noteId)
                .findFirst();
    }
    /**
     * Retrieves all disciplinary notes currently in the archive.
     *
     * @return A list of all DisciplinaryNote objects.
     */
    public List<DisciplinaryNote> getAllNotes() {
        return new ArrayList<>(notes); // Return a copy to prevent external modification
    }
    /**
     * Deletes a disciplinary note from the archive by its ID.
     *
     * @param noteId The ID of the note to delete.
     * @return True if the note was found and deleted, false otherwise.
     */
    public boolean deleteNote(int noteId) {
        boolean removed = notes.removeIf(note -> note.getId() == noteId);
        if (removed) {
            System.out.println("Note with ID " + noteId + " successfully deleted from archive.");
        } else {
            System.err.println("Note with ID " + noteId + " not found in archive for deletion.");
        }
        return removed;
    }
}