package com.example.infrastructure;

import com.example.domain.Note;
import java.util.List;

/**
 * Repository interface for Note entities.
 */
public interface NoteRepository {
    /**
     * Finds a note by its ID.
     * @param id the note ID
     * @return the Note, or null if not found
     */
    Note findById(String id);

    /**
     * Retrieves all notes.
     * @return a list of all notes
     */
    List<Note> findAll();
}