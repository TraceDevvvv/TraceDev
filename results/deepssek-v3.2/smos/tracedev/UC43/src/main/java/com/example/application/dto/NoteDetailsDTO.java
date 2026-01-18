package com.example.application.dto;

import java.util.Date;

public class NoteDetailsDTO {
    private String noteId;
    private String studentName;
    private Date date;
    private String description;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NoteDetailsDTO{" +
                "noteId='" + noteId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}