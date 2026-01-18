package com.example.application;

import java.util.Date;

/**
 * Data Transfer Object for note details.
 * Used to pass data between layers without exposing domain objects.
 */
public class NoteDetailsDTO {
    private String studentName;
    private String description;
    private String teacherName;
    private Date date;

    public NoteDetailsDTO(String studentName, String description, String teacherName, Date date) {
        this.studentName = studentName;
        this.description = description;
        this.teacherName = teacherName;
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDescription() {
        return description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public Date getDate() {
        return date;
    }
}