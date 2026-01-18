package com.example.app.domain;

import com.example.app.Domain;
import java.util.Date;

/**
 * Represents a disciplinary note for a student.
 */
public class DisciplinaryNote implements Domain {
    public String id;
    public String studentId;
    public String registerId;
    public Date date;
    public String description;
    public String type; // e.g., "Verbal Warning", "Formal Warning"

    /**
     * Constructs a new DisciplinaryNote.
     * @param id The unique identifier for the disciplinary note.
     * @param studentId The ID of the student receiving the note.
     * @param registerId The ID of the register this note is associated with.
     * @param date The date the note was issued.
     * @param description A detailed description of the disciplinary action.
     * @param type The type of disciplinary note.
     */
    public DisciplinaryNote(String id, String studentId, String registerId, Date date, String description, String type) {
        this.id = id;
        this.studentId = studentId;
        this.registerId = registerId;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    // Getters and setters for public fields
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}