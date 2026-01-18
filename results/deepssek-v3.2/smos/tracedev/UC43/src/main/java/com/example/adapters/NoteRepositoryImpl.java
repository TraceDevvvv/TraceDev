package com.example.adapters;

import com.example.domain.DisciplinaryNote;
import com.example.ports.NoteRepository;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class NoteRepositoryImpl implements NoteRepository {
    private DataSource database;
    // Simulating a database with an in-memory map for demonstration
    private Map<String, DisciplinaryNote> noteStore = new HashMap<>();

    public NoteRepositoryImpl() {
        // Initialize with some sample data for demonstration
        noteStore.put("note1", new DisciplinaryNote("note1", "student1", "Sample note 1", new Date()));
        noteStore.put("note2", new DisciplinaryNote("note2", "student2", "Sample note 2", new Date()));
    }

    @Override
    public DisciplinaryNote findById(String noteId) {
        System.out.println("Looking up note with ID: " + noteId);
        return noteStore.get(noteId);
    }

    @Override
    public void delete(String noteId) {
        System.out.println("Deleting note with ID: " + noteId);
        noteStore.remove(noteId);
        System.out.println("Note deleted from repository.");
    }

    @Override
    public void save(DisciplinaryNote note) {
        if (note != null) {
            System.out.println("Saving note with ID: " + note.getNoteId());
            noteStore.put(note.getNoteId(), note);
        } else {
            System.out.println("Saving null note (placeholder for state saving).");
            // For interruption handling, we might save a state object; here we just log
        }
    }
}