package com.example.model;

import java.util.Date;

/**
 * Represents a disciplinary note for a student.
 */
public class DisciplinaryNote {
    private String studentName;
    private String note;
    private Date date;

    public DisciplinaryNote(String studentName, String note, Date date) {
        this.studentName = studentName;
        this.note = note;
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}