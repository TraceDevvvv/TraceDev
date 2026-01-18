package com.example.model;

import java.util.Date;

/**
 * Represents a disciplinary note for a child.
 */
public class DisciplinaryNote {
    private int noteId;
    private int childId;
    private Date date;
    private String description;
    private String teacher;

    public DisciplinaryNote(int noteId, int childId, Date date, String description, String teacher) {
        this.noteId = noteId;
        this.childId = childId;
        this.date = date;
        this.description = description;
        this.teacher = teacher;
    }

    public int getNoteId() {
        return noteId;
    }

    public int getChildId() {
        return childId;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getTeacher() {
        return teacher;
    }
}