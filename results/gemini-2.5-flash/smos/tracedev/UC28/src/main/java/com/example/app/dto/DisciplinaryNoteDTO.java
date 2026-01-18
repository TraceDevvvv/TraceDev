package com.example.app.dto;

import com.example.app.DTO;

/**
 * DTO for displaying disciplinary note information.
 */
public class DisciplinaryNoteDTO implements DTO {
    public String id;
    public String studentName;
    public String description;
    public String type; // e.g., "Verbal Warning", "Formal Warning"

    /**
     * Constructs a new DisciplinaryNoteDTO.
     * @param id The ID of the disciplinary note.
     * @param studentName The name of the student who received the note.
     * @param description A description of the note.
     * @param type The type of the note.
     */
    public DisciplinaryNoteDTO(String id, String studentName, String description, String type) {
        this.id = id;
        this.studentName = studentName;
        this.description = description;
        this.type = type;
    }

    // Getters for public fields
    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("  Disciplinary Note ID: %s, Student: %s, Type: %s, Description: '%s'",
                id, studentName, type, description);
    }
}