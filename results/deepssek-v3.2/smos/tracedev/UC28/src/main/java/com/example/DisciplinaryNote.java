package com.example;

import java.util.Date;

/**
 * Represents a disciplinary note for a student.
 */
public class DisciplinaryNote {
    private String noteId;
    private String studentId;
    private Date recordDate;
    private String description;

    public DisciplinaryNote(String noteId, String studentId, Date recordDate, String description) {
        this.noteId = noteId;
        this.studentId = studentId;
        this.recordDate = recordDate;
        this.description = description;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public String getDescription() {
        return description;
    }

    public void createNote(String studentId, Date recordDate, String description) {
        this.studentId = studentId;
        this.recordDate = recordDate;
        this.description = description;
        // Additional logic to persist note could be added here.
    }

    public void updateNote(String description) {
        this.description = description;
        // Additional logic to update note could be added here.
    }
}