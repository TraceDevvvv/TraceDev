package com.example.application.query;

import com.example.application.dto.NoteDetailsDTO;
import com.example.domain.DisciplinaryNote;
import com.example.ports.NoteRepository;
import com.example.adapters.NoteRepositoryImpl;
import java.util.Date;

public class NoteDetailsQuery {
    private String noteId;

    // Constructor for simplicity; in reality, noteId would be passed as parameter
    public NoteDetailsQuery() {
        this.noteId = "sample-note-id"; // default for demonstration
    }

    public NoteDetailsQuery(String noteId) {
        this.noteId = noteId;
    }

    public NoteDetailsDTO execute() {
        // Retrieve note from repository (simulating entry condition: viewing note details)
        NoteRepository noteRepository = new NoteRepositoryImpl(); // Assuming default implementation
        DisciplinaryNote note = noteRepository.findById(noteId);
        
        if (note == null) {
            System.out.println("Note not found for ID: " + noteId);
            return null;
        }
        
        // Create DTO with note details (student name is simulated)
        NoteDetailsDTO dto = new NoteDetailsDTO();
        dto.setNoteId(note.getNoteId());
        dto.setStudentName("Student Name Placeholder"); // In real scenario, fetch from Student
        dto.setDate(note.getDate());
        dto.setDescription(note.getDescription());
        
        return dto;
    }
}