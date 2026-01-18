package com.example.app.infrastructure;

import com.example.app.Infrastructure;
import com.example.app.domain.DisciplinaryNote;
import java.util.List;

/**
 * Interface for repository operations related to DisciplinaryNote.
 */
public interface IDisciplinaryNoteRepository extends Infrastructure {
    /**
     * Finds a list of disciplinary notes for a specific register and date.
     * @param registerId The ID of the register.
     * @param date The date (as a formatted string, e.g., "YYYY-MM-DD") for which to retrieve notes.
     * @return A list of DisciplinaryNote objects.
     */
    List<DisciplinaryNote> findByRegisterIdAndDate(String registerId, String date);

    /**
     * Saves a new disciplinary note.
     * @param disciplinaryNote The DisciplinaryNote object to save.
     * @return The saved DisciplinaryNote object (possibly with an updated ID).
     */
    DisciplinaryNote save(DisciplinaryNote disciplinaryNote);

    /**
     * Updates an existing disciplinary note.
     * @param disciplinaryNoteId The ID of the disciplinary note to update.
     * @param description The new description for the note.
     * @param type The new type for the note.
     */
    void update(String disciplinaryNoteId, String description, String type);
}