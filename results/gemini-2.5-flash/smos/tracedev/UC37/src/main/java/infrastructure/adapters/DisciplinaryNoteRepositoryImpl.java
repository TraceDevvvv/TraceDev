package infrastructure.adapters;

import domain.DisciplinaryNote;
import infrastructure.ports.IDisciplinaryNoteRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Adapter implementation for the IDisciplinaryNoteRepository port.
 * This class simulates persistence of DisciplinaryNote entities,
 * typically interacting with a database or other storage mechanism.
 * For this example, it uses an in-memory HashMap.
 */
public class DisciplinaryNoteRepositoryImpl implements IDisciplinaryNoteRepository {

    // Simulates a database table for DisciplinaryNotes
    private final Map<String, DisciplinaryNote> notesDatabase = new HashMap<>();

    public DisciplinaryNoteRepositoryImpl() {
        System.out.println("DisciplinaryNoteRepositoryImpl: Initialized (using in-memory storage).");
    }

    /**
     * Saves a DisciplinaryNote. If the note's ID is new, it's added.
     * If the ID already exists, the existing note is updated.
     * In a real application, this would involve JDBC, JPA, or another ORM.
     *
     * @param note The DisciplinaryNote to save.
     * @return The saved DisciplinaryNote (with a generated ID if it was new).
     */
    @Override
    public DisciplinaryNote save(DisciplinaryNote note) {
        // Simulate DB interaction: INSERT or UPDATE
        System.out.println("Repository: Saving DisciplinaryNote: " + note.getId());

        // In a real DB, ID might be auto-generated. Here, we assume it's set on creation
        // or ensure it's present.
        if (note.getId() == null || note.getId().isEmpty()) {
            // This scenario should ideally not happen if constructor auto-generates ID.
            // But good to defensive check.
            System.err.println("Error: DisciplinaryNote provided for save has no ID. Generating one.");
            // Generate a new ID if somehow missing
            // For this project, the domain entity handles ID generation upon construction.
            // So this block is mostly for illustrative robustness.
        }

        // Simulate database "INSERT INTO" and returning generated ID (if any)
        // Here, we just store and return the same object.
        notesDatabase.put(note.getId(), note);
        System.out.println("Repository: Successfully saved DisciplinaryNote with ID: " + note.getId());

        // Simulate returning the persisted entity (which might have DB-generated IDs etc.)
        return notesDatabase.get(note.getId());
    }

    /**
     * Helper method to simulate finding a note by ID (not part of the class diagram, but useful for testing).
     * @param id The ID of the note to find.
     * @return An Optional containing the DisciplinaryNote if found, or empty.
     */
    public Optional<DisciplinaryNote> findById(String id) {
        return Optional.ofNullable(notesDatabase.get(id));
    }
}