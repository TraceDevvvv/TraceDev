package infrastructure.ports;

import domain.DisciplinaryNote;

/**
 * Port interface for persistence operations related to DisciplinaryNote entities.
 * This defines the contract for how DisciplinaryNote entities are stored and retrieved.
 */
public interface IDisciplinaryNoteRepository {

    /**
     * Saves a DisciplinaryNote entity to the persistence layer.
     * If the note is new, it typically assigns an ID and stores it.
     * If the note already exists (e.g., has an ID), it updates the existing record.
     *
     * @param note The DisciplinaryNote to be saved.
     * @return The saved DisciplinaryNote, potentially with an updated ID or other generated fields.
     */
    DisciplinaryNote save(DisciplinaryNote note);
}