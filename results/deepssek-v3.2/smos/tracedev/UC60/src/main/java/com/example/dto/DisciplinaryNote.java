package com.example.dto;

import java.util.Date;

/**
 * Represents a disciplinary note for a student.
 */
public class DisciplinaryNote {
    private Date date;
    private String note;
    private String teacher;

    public DisciplinaryNote() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}