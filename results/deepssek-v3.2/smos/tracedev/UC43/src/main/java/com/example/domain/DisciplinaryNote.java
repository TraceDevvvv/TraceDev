package com.example.domain;

import java.util.Date;

public class DisciplinaryNote {
    private String noteId;
    private String studentId;
    private String description;
    private Date date;
    private boolean isActive;

    public DisciplinaryNote(String noteId, String studentId, String description, Date date) {
        this.noteId = noteId;
        this.studentId = studentId;
        this.description = description;
        this.date = date;
        this.isActive = true;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Method to get active status
    public boolean isActive() {
        return isActive;
    }

    // Method to delete (deactivate) the note
    public void delete() {
        this.isActive = false;
        System.out.println("Note " + noteId + " marked as deleted (deactivated).");
    }

    // Method to deactivate the note (alternative to delete)
    public void deactivate() {
        this.isActive = false;
        System.out.println("Note " + noteId + " deactivated.");
    }
}